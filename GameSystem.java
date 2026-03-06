/**
 * The class manages the overall flow and operations in the Chip's Challenge game. It also handles the loading of 
 * game maps, player movement, and transition between levels.The system also tracks the current level and allows 
 * interaction with tiles, doors, and other objects found in the map.
 *
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameSystem
{
    private ChipPlayer chip; // character controlled by the user
    private GameMap map; // current map being played
    private int currentLevel; // current level being played
    private boolean allMapsCompleted; // flag to determine completion status
    private GamePanel panel;
    private JFrame frame;
    private JTextField textField;
    private JLabel enterMoveLabel;
    private JLabel levelLabel;
    private JLabel microchipLabel;
    private Styling style;
    private List<NaiveEnemy> enemies;
    private Timer enemyTimer;
    private char lastMoveDirection; // Track last move for ice sliding
    private boolean isTeleporting = false; // Prevent teleport loops

    /**
     * Constructor that initializes the first level and sets up user input handling
     */
    public GameSystem()
    {
        style = new Styling();
        levelLabel = new JLabel();
        microchipLabel = new JLabel();
        enterMoveLabel = new JLabel();
        textField = new JTextField();
        this.enemies = new ArrayList<>();
        this.currentLevel = 1;
        loadCurrentLevel(currentLevel);
        this.allMapsCompleted = false;
    }

    /**
     * This method loads the game map based on the current level. If all maps are completed, there will be
     * a prompt and allows the user to return to the menu.
     *
     * @param currentLevel - current level being played/to load
     */
    public void loadCurrentLevel(int currentLevel)
    {
        enemies.clear(); // Clear enemies from previous level

        boolean checkWin = false;

        if (currentLevel == 1) {
            this.map = new GameMap1(); // load map 1
            // Add enemies for map 1
            addEnemySafely(8, 8, true);
            addEnemySafely(12, 12, false);
        } else if (currentLevel == 2) {
            this.map = new GameMap2(); // load map 2
            // Add enemies for map 2
            addEnemySafely(8, 8, true);
            addEnemySafely(12, 12, false);
            addEnemySafely(15, 7, true);
        } else {
            JOptionPane.showMessageDialog(null, "All maps completed!");
            checkWin = true;
            this.allMapsCompleted = true;
        }

        if (!checkWin) {
            this.chip = new ChipPlayer(this.map.getSpawnX(), this.map.getSpawnY());
        }
    }

    /**
     * Safely adds an enemy to the game, validating its position first.
     *
     * @param x - x coordinate for enemy
     * @param y - y coordinate for enemy
     * @param isHorizontal - whether enemy moves horizontally
     */
    private void addEnemySafely(int x, int y, boolean isHorizontal) {
        try {
            NaiveEnemy enemy = new NaiveEnemy(x, y, isHorizontal, map);
            enemies.add(enemy);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add enemy at position (" + x + ", " + y + "): " + e.getMessage());
        }
    }

    /**
     * This method starts the game loop where the player can move around, view inventory, or exit their current game.
     */
    public void startGame() {
        loadCurrentLevel(currentLevel);

        if (!allMapsCompleted) {
            // for main frame
            ImageIcon icon = new ImageIcon("images/chipLogo.jpg");
            frame = new JFrame("Chip's Challenge - Map " + currentLevel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setIconImage(icon.getImage());

            // for top panel
            style.topPanelText(levelLabel, microchipLabel, currentLevel, chip.getMicrochipCount(), map.getRequiredMicrochips());
            JPanel topPanel = style.createTopPanel(levelLabel, microchipLabel, currentLevel);

            // for game map
            panel = new GamePanel(map, chip, enemies);
            frame.add(style.createMapContainer(panel), BorderLayout.CENTER);

            // for bottom pannel (to be fixed)
            JPanel bottomPanel = style.createBottomPanel(enterMoveLabel, textField, chip);

            // add all components together
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            // Initialize enemy movement timer
            enemyTimer = new Timer(500, e -> {
                for (NaiveEnemy enemy : enemies) {
                    enemy.move(map);
                    if (enemy.checkCollision(chip)) {
                        chip.setChipAlive(false);
                        enemyTimer.stop();

                        JFrame gameOverFrame = new JFrame();
                        gameOverFrame.setUndecorated(true);
                        gameOverFrame.setSize(260, 180);
                        gameOverFrame.setLocationRelativeTo(frame);
                        gameOverFrame.setAlwaysOnTop(true);

                        JPanel mainPanel = new JPanel(new BorderLayout());
                        mainPanel.setBackground(Color.BLACK);
                        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4));

                        JLabel msg = new JLabel("Game Over!", SwingConstants.CENTER);
                        msg.setFont(new Font("Consolas", Font.BOLD, 22));
                        msg.setForeground(Color.WHITE);
                        msg.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));

                        JButton okButton = new JButton("OK");
                        style.styleButton(okButton);

                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setBackground(Color.BLACK);
                        buttonPanel.add(okButton);

                        mainPanel.add(msg, BorderLayout.CENTER);
                        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

                        gameOverFrame.setContentPane(mainPanel);
                        gameOverFrame.setVisible(true);

                        okButton.addActionListener(x -> {
                            gameOverFrame.dispose();
                            frame.dispose();
                            resetToMainMenu();
                        });

                        break;
                    }
                }
                panel.repaint();
            });
            enemyTimer.start();

            textField.addActionListener(e -> {
                String text = textField.getText().trim().toLowerCase();

                if (text.length() > 0) {
                    char move = text.charAt(0);

                    if (move == 'e') {
                        style.styleExitCurrent(frame, this, textField);
                    } else if (chip.isChipAlive()) {
                        lastMoveDirection = move; // Store move for ice sliding
                        chipMovement(move);
                        style.topPanelText(levelLabel, microchipLabel, currentLevel, chip.getMicrochipCount(), map.getRequiredMicrochips());
                        panel.repaint();

                        if (chip.getMicrochipCount() >= map.getRequiredMicrochips() && chip.isAtExit()) {
                            JOptionPane.showMessageDialog(frame, "Level Complete!");
                            enemyTimer.stop();
                            loadNextLevel();
                        }
                    }
                }
                textField.setText("");
            });
            style.styleGameSystemFormat(frame);
        } else {
            resetToMainMenu();
        }
    }

    /**
     * Loads the next level in the game by disposing the current frame, stopping enemy movement timers,
     * and incrementing the level counter. If additional levels remain, the game continues with the next map.
     * If all maps have been completed, the game resets to level 1 and returns to the main menu.
     */
    private void loadNextLevel()
    {
        frame.dispose();
        if (enemyTimer != null) {
            enemyTimer.stop();
        }
        currentLevel++;
        loadCurrentLevel(currentLevel);

        if (!allMapsCompleted) {
            startGame();
        } else {
            allMapsCompleted = false;
            currentLevel = 1;
            style.showMainMenu();
        }
    }

    /**
     * This method determines the new position of Chip based on the direction or input.It also checks
     * whether the input/move is valid or not.
     *
     * @param moveChip - movement entered by player (w, a, s, d)
     */
    public void chipMovement(char moveChip)
    {
        int tempX = chip.getXPosition();
        int tempY = chip.getYPosition();

        if (moveChip == 'w') {
            tempX--;
        } else if (moveChip == 's') {
            tempX++;
        } else if (moveChip == 'a') {
            tempY--;
        } else if (moveChip == 'd') {
            tempY++;
        }

        // change position if the move is valid
        if (checkMoveCondition(tempX, tempY)) {
            changeChipPosition(tempX, tempY, moveChip);
        }
    }

    /**
     * This method checks whether Chip can move to the given coordinates based on the movement input. It prevents Chip
     * from walking or passing through inaccessible areas or outside of the map.
     *
     * @param x - x coordinate to be checked
     * @param y - y coordinate to be checked
     * @return true if the move if allowed, false if not
     */
    public boolean checkMoveCondition(int x, int y)
    {
        if (x < 0 || x >= map.getMapLength() || y < 0 || y >= map.getMapWidth()) {
            return false;
        }

        MapElement nextElement = map.getMap()[x][y];

        return !(nextElement instanceof WallTile);
    }

    /**
     * This method updates Chip's position and handles interaction with different map elements such as doors, tiles, exits, and more.
     *
     * @param x - the new x coordinate for Chip
     * @param y - the new y coordinate for Chip
     * @param moveDirection - the direction Chip moved in
     */
    public void changeChipPosition(int x, int y, char moveDirection)
    {
        boolean chipDied = false;

        MapElement currentElement = map.getMap()[x][y];

        if (currentElement instanceof Door) {
            Door door = (Door)currentElement;

            if (!door.isLocked() || door instanceof BlueDoor && chip.hasBlueKey() || door instanceof RedDoor && chip.hasRedKey()) {
                chip.setXPosition(x);
                chip.setYPosition(y);
                door.enterDoor(chip);
            }
        } else if (currentElement instanceof Tile) {
            Tile tile = (Tile)currentElement;

            if (tile.isWalkable()) {
                chip.setXPosition(x);
                chip.setYPosition(y);
                tile.enterTile(chip);

                if (!chip.isChipAlive()) {
                    chipDied = true; // mark death
                }

                if (!chipDied) {
                    if (tile instanceof ExitTile) {
                        if (chip.getMicrochipCount() >= map.getRequiredMicrochips()) {
                            System.out.println("You collected enough microchips! Exiting door...");
                            chip.setAtExit(true);
                        } else {
                            int currentMicrochips = map.getRequiredMicrochips() - chip.getMicrochipCount();
                            System.out.println("You still need " + currentMicrochips + " microchips!");
                            chip.setAtExit(false);
                        }
                    } else if (tile instanceof ForceFloor) {
                        chip.setXPosition(x);
                        chip.setYPosition(y);

                        panel.repaint();
                        Timer delaySlide = new Timer(100, e -> {
                            slideOnForceFloor((ForceFloor) tile);
                        });
                        delaySlide.setRepeats(false);
                        delaySlide.start();
                    } else if (tile instanceof IceTile) {
                        // Start ice sliding
                        Timer delaySlide = new Timer(100, e -> {
                            slideOnIce(moveDirection);
                        });
                        delaySlide.setRepeats(false);
                        delaySlide.start();
                    } else if (tile instanceof TeleportTile && !isTeleporting) {
                        handleTeleportation((TeleportTile) tile);
                    }
                }
            } else {
                tile.enterTile(chip);
            }
        }

        panel.repaint();

        if (!chip.isChipAlive()) {
            if (enemyTimer != null) {
                enemyTimer.stop();
            }
            JOptionPane.showMessageDialog(frame, "Game Over! Chip died.");
            frame.dispose();
            allMapsCompleted = false;
            currentLevel = 1;
            style.showMainMenu();
        }
    }

    /**
     * This method handles Chip's sliding when stepping on a Force Floor on the map. Chip will continue moving in
     * the direction until it reaches a non-walkable or accessible object in the map.
     *
     * @param floor - the force floor tile Chip is currently stepping on
     */
    public void slideOnForceFloor(ForceFloor floor)
    {
        int newX, newY;
        int dirX = floor.getXDirection();
        int dirY = floor.getYDirection();
        boolean keepSliding = true;

        while (keepSliding && chip.isChipAlive()) {
            newX = chip.getXPosition() + dirX;
            newY = chip.getYPosition() + dirY;

            if (!checkMoveCondition(newX, newY)) {
                keepSliding = false;
            } else {
                changeChipPosition(newX, newY, lastMoveDirection); // updates chip position + handles tile interactions

                MapElement currentElement = map.getMap()[newX][newY];

                if (currentElement instanceof ForceFloor) {
                    ForceFloor newFloor = (ForceFloor) currentElement;
                    dirX = newFloor.getXDirection();
                    dirY = newFloor.getYDirection();
                }

                panel.repaint();
                panel.paintImmediately(panel.getBounds());

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handles Chip's sliding on ice tiles.
     *
     * @param moveDirection - the direction Chip was moving when he entered the ice
     */
    public void slideOnIce(char moveDirection)
    {
        int dirX = 0, dirY = 0;

        // Convert move character to direction vectors
        switch(moveDirection) {
            case 'w': dirX = -1; break;
            case 's': dirX = 1; break;
            case 'a': dirY = -1; break;
            case 'd': dirY = 1; break;
        }

        boolean keepSliding = true;

        while (keepSliding && chip.isChipAlive()) {
            int newX = chip.getXPosition() + dirX;
            int newY = chip.getYPosition() + dirY;

            // Check if next position is valid and is ice
            if (checkMoveCondition(newX, newY) &&
                    map.getMap()[newX][newY] instanceof IceTile) {
                chip.setXPosition(newX);
                chip.setYPosition(newY);
                panel.repaint();

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                keepSliding = false;
                // Move one more step if possible (to exit ice)
                if (checkMoveCondition(newX, newY)) {
                    chip.setXPosition(newX);
                    chip.setYPosition(newY);
                    panel.repaint();
                }
            }
        }
    }

    /**
     * Handles teleportation between linked teleport tiles.
     *
     * @param teleportTile - the teleport tile Chip stepped on
     */
    public void handleTeleportation(TeleportTile teleportTile)
    {
        isTeleporting = true;
        chip.setXPosition(teleportTile.getTargetX());
        chip.setYPosition(teleportTile.getTargetY());
        panel.repaint();

        // Reset teleport flag after a short delay to prevent immediate re-teleportation
        Timer cooldownTimer = new Timer(100, e -> {
            isTeleporting = false;
        });
        cooldownTimer.setRepeats(false);
        cooldownTimer.start();
    }

    public void resetToMainMenu() {
        allMapsCompleted = false;
        currentLevel = 1;
        if (enemyTimer != null) {
            enemyTimer.stop();
        }
        style.showMainMenu();
    }

    /**
     * Gets the list of enemies in the current level.
     *
     * @return list of NaiveEnemy objects
     */
    public List<NaiveEnemy> getEnemies() {
        return enemies;
    }
}