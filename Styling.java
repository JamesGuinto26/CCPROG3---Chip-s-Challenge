import javax.swing.*;
import java.awt.*;

/**
 * The {@code Styling} class centralizes all UI styling, formatting, and popup window creation
 * for Chip's Challenge. It provides helper methods for creating styled panels, inventory windows,
 * confirmation dialogs, menu screens, and buttons used throughout the game.
 */
public class Styling {

    /**
     * Displays the player's inventory in a styled pop-up window.
     *
     * @param chip - the player character whose inventory is shown
     */
    public void showInventory(ChipPlayer chip)
    {
        JFrame inventoryFrame = new JFrame();
        inventoryFrame.setSize(300, 300);
        inventoryFrame.setLocationRelativeTo(null);
        inventoryFrame.setUndecorated(true);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBackground(Color.BLACK);
        inventoryPanel.setBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4));
        inventoryPanel.setLayout(new BorderLayout(0, 10));

        JPanel itemsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        itemsPanel.setBackground(Color.BLACK);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JLabel microchipLabel = new JLabel("Microchips: " + chip.getMicrochipCount());
        JLabel redKeyLabel = new JLabel("Red Keys: " + chip.getRedKeyCount());
        JLabel blueKeyLabel = new JLabel("Blue Keys: " + chip.getBlueKeyCount());
        JLabel flippersLabel = new JLabel("Flippers: " + (chip.getFlippersStatus() ? "YES" : "NO"));
        JLabel fireBootsLabel = new JLabel("Fire Boots: " + (chip.getFirebootsStatus() ? "YES" : "NO"));

        JLabel[] labels = {microchipLabel, redKeyLabel, blueKeyLabel, flippersLabel, fireBootsLabel};

        for (JLabel label : labels)
        {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Consolas", Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            itemsPanel.add(label);
        }

        JButton returnButton = new JButton("Return to Game");
        returnButton.setBackground(new Color(102, 179, 255));
        returnButton.setFont(new Font("Consolas", Font.BOLD, 16));
        returnButton.setPreferredSize(new Dimension(180, 40));
        returnButton.setFocusPainted(false);
        returnButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        returnButton.addChangeListener(e -> {
            ButtonModel model = returnButton.getModel();
            returnButton.setBackground(model.isRollover() ? Color.WHITE : new Color(102, 179, 255));
        });

        returnButton.addActionListener(e -> inventoryFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        buttonPanel.add(returnButton);

        inventoryPanel.add(itemsPanel, BorderLayout.CENTER);
        inventoryPanel.add(buttonPanel, BorderLayout.SOUTH);

        inventoryFrame.add(inventoryPanel);
        inventoryFrame.setVisible(true);
    }

    /**
     * Displays a confirmation popup asking the user whether they want to exit the current game.
     *
     * @param frame - the parent window of the game
     * @param game - the game system instance used to reset back to the main menu
     * @param textField - the movement input field, which is temporarily disabled
     */
    public void styleExitCurrent(JFrame frame, GameSystem game, JTextField textField)
    {
        textField.setEnabled(false);

        JFrame exitFrame = new JFrame();
        exitFrame.setUndecorated(true);
        exitFrame.setSize(250, 180);
        exitFrame.setLocationRelativeTo(frame);
        exitFrame.setAlwaysOnTop(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4));

        JLabel exitLabel = new JLabel("Exit Game?", SwingConstants.CENTER);
        exitLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        styleButton(yesButton);
        styleButton(noButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        mainPanel.add(exitLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        exitFrame.setContentPane(mainPanel);
        exitFrame.setVisible(true);

        yesButton.addActionListener(x -> {
            exitFrame.dispose();
            frame.dispose();
            game.resetToMainMenu(); // use method from GameSystem
        });

        noButton.addActionListener(x -> {
            exitFrame.dispose();
            textField.setEnabled(true);
            textField.requestFocus();
        });
    }

    /**
     * Creates and styles the top information panel that displays the level and microchip count.
     *
     * @param levelLabel - label showing the current level number
     * @param microchipLabel - label showing collected microchips
     * @param currentLevel - the active level number
     * @return the constructed and styled panel
     */
    public JPanel createTopPanel(JLabel levelLabel, JLabel microchipLabel, int currentLevel)
    {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 2));
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4), BorderFactory.createEmptyBorder(12, 0, 12, 0)));

        ImageIcon logoIcon = new ImageIcon("images/ChipTitle.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        levelLabel.setForeground(Color.WHITE);
        microchipLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        microchipLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        topPanel.add(logoLabel);
        topPanel.add(levelLabel);
        topPanel.add(microchipLabel);

        return topPanel;
    }

    /**
     * Creates the bottom panel containing movement instructions, input field,
     * and inventory button.
     *
     * @param enterMoveLabel - label displaying move instructions
     * @param textField - player movement input box
     * @param chip - the player character whose inventory can be opened
     * @return the fully styled bottom panel
     */
    public JPanel createBottomPanel(JLabel enterMoveLabel, JTextField textField, ChipPlayer chip)
    {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4), BorderFactory.createEmptyBorder(12, 0, 12, 0)));

        enterMoveLabel.setText("Enter move (WASD, E to exit):");
        enterMoveLabel.setForeground(Color.WHITE);
        enterMoveLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        textField.setColumns(10);
        textField.setFont(new Font("Consolas", Font.BOLD, 16));

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setFocusPainted(false);
        inventoryButton.setFont(new Font("Consolas", Font.BOLD, 16));
        inventoryButton.setBackground(new Color(102, 179, 255));
        inventoryButton.setForeground(Color.BLACK);
        inventoryButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        inventoryButton.setPreferredSize(new Dimension(120, 35));

        inventoryButton.addChangeListener(e -> {
            ButtonModel model = inventoryButton.getModel();
            inventoryButton.setBackground(model.isRollover() ? Color.WHITE : new Color(102, 179, 255));
        });

        inventoryButton.addActionListener(e -> showInventory(chip));

        // Add components
        bottomPanel.add(enterMoveLabel);
        bottomPanel.add(textField);
        bottomPanel.add(inventoryButton);

        return bottomPanel;
    }

    /**
     * Updates the text of the top panel labels based on the current level
     * and microchip progress.
     *
     * @param levelLabel - label showing the current map number
     * @param microchipLabel - label showing collected/required microchips
     * @param currentLevel - the active level number
     * @param microchipCount - collected microchips
     * @param requiredMicrochip - total microchips needed to exit
     */
    public void topPanelText(JLabel levelLabel, JLabel microchipLabel, int currentLevel, int microchipCount, int requiredMicrochip)
    {
        levelLabel.setText("Map: " + currentLevel);
        microchipLabel.setText("Microchips: " + microchipCount + " / " + requiredMicrochip);
    }

    /**
     * Wraps the game map panel inside a styled container with borders.
     *
     * @param panel - the map panel to wrap
     * @return a bordered container for the map
     */
    public JPanel createMapContainer(JPanel panel)
    {
        JPanel mapContainer = new JPanel(new GridBagLayout());
        mapContainer.setBackground(Color.black);
        mapContainer.setBorder(BorderFactory.createLineBorder(new Color(102, 179, 255), 4));
        mapContainer.add(panel);

        return mapContainer;
    }

    /**
     * Applies standard formatting for the main game window.
     *
     * @param frame - the window to format and display
     */
    public void styleGameSystemFormat(JFrame frame)
    {
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Shows the game's main menu screen with title and play/exit buttons.
     */
    public void showMainMenu()
    {
        // for game icon
        ImageIcon icon = new ImageIcon("images/chipLogo.jpg");

        // for main game frame
        JFrame frame = new JFrame("Chip's Challenge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 480);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());

        // for panel where all components will be added
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // for top panel to add space
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));

        // for the game title image
        ImageIcon titleIcon = new ImageIcon("images/ChipTitle.png");
        Image titleImage = titleIcon.getImage().getScaledInstance(450, 180, Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(titleImage); // assign scaled image

        // for the label where image will be added
        JLabel title = new JLabel(titleIcon);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);

        // for the panel where all title components will be added
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(title);

        // for the play button and styling
        JButton playButton = new JButton("Play Chip's Challenge");
        styleButton(playButton);

        // for the exit button and styling
        JButton exitButton = new JButton("Exit Chip's Challenge");
        styleButton(exitButton);

        // for panel where all buttons will be added
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 40, 200));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        buttonPanel.add(exitButton);

        // add and combine all components
        topPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // handle button actions/event
        playButton.addActionListener(e -> Main.startGame(frame));
        exitButton.addActionListener(e -> Main.exitGame(frame));
    }

    /**
     * Styles a button consistently with the game's design:
     * hover color change, border, sizing, and hand cursor.
     *
     * @param button - the button to style
     */
    public void styleButton(JButton button)
    {
        // for button design and format
        button.setBackground(new Color(102, 179, 255));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 60));
        button.setFont(new Font("Consolas", Font.BOLD, 17));

        // handle button display when hovered
        button.getModel().addChangeListener(e -> {
            ButtonModel model = button.getModel();
            button.setBackground(model.isRollover() ? Color.WHITE : new Color(102, 179, 255));
        });

        // change to hand cursor when hovered
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
