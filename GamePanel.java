import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code GamePanel} class is responsible for rendering the entire game world,
 * including the map tiles, Chip, and all enemy entities.
 *
 * This panel uses Swing's painting system to draw sprites for different
 * map elements based on their type and displays the current state of the game.
 */
public class GamePanel extends JPanel {
    private GameMap map;
    private ChipPlayer chip;
    private List<NaiveEnemy> enemies;
    private final int tileSize = 30; // each box will be 32x32 pixels

    /**
     * Constructs a new {@code GamePanel} that displays the given map,
     * the player character, and all active enemies.
     *
     * @param map - the game map containing all tiles to be drawn
     * @param chip - the player character to be rendered
     * @param enemies - the list of enemies that will appear in the game
     */
    public GamePanel(GameMap map, ChipPlayer chip, List<NaiveEnemy> enemies) {
        this.map = map;
        this.chip = chip;
        this.enemies = enemies;
        setPreferredSize(new Dimension(20 * tileSize, 20 * tileSize));
        setBackground(Color.BLACK);
    }

    /**
     * Renders the entire game screen, drawing each map tile, items on tiles,
     * the Chip player, and all enemies. This method is automatically called by
     * Swing whenever the panel needs to be refreshed or updated.
     *
     * @param g - the graphics context used to draw sprites on the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        int i, j, drawX, drawY;

        super.paintComponent(g);

        for (i = 0; i < map.getMapLength(); i++) {
            for (j = 0; j < map.getMapWidth(); j++) {
                drawX = j * tileSize;
                drawY = i * tileSize;

                MapElement element = map.getMap()[i][j];

                if (element instanceof WallTile) {
                    g.drawImage(((WallTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof FireTile) {
                    g.drawImage(((FireTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof WaterTile) {
                    g.drawImage(((WaterTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof ForceFloor) {
                    g.drawImage(((ForceFloor) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof BlueDoor) {
                    g.drawImage(((BlueDoor) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof RedDoor) {
                    g.drawImage(((RedDoor) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof BlankTile) {
                    BlankTile blank = (BlankTile) element;
                    g.drawImage(blank.getSprite(), drawX, drawY, tileSize, tileSize, null);

                    if (blank.getItem() != null) {
                        Item item = blank.getItem();

                        if (item instanceof BlueKey) {
                            g.drawImage(((BlueKey) item).getSprite(), drawX, drawY, tileSize, tileSize, null);
                        } else if (item instanceof RedKey) {
                            g.drawImage(((RedKey) item).getSprite(), drawX, drawY, tileSize, tileSize, null);
                        } else if (item instanceof FireBoots) {
                            g.drawImage(((FireBoots) item).getSprite(), drawX, drawY, tileSize, tileSize, null);
                        } else if (item instanceof Flippers) {
                            g.drawImage(((Flippers) item).getSprite(), drawX, drawY, tileSize, tileSize, null);
                        } else if (item instanceof Microchip) {
                            g.drawImage(((Microchip) item).getSprite(), drawX, drawY, tileSize, tileSize, null);
                        }
                    }
                } else if (element instanceof ExitTile) {
                    g.drawImage(((ExitTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof IceTile) {
                    g.drawImage(((IceTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                } else if (element instanceof TeleportTile) {
                    g.drawImage(((TeleportTile) element).getSprite(), drawX, drawY, tileSize, tileSize, null);
                }

                if (i == chip.getXPosition() && j == chip.getYPosition()) {
                    g.drawImage(chip.getSprite(), drawX, drawY, tileSize, tileSize, null);
                }
            }
        }

        // Paint enemies with null safety
        for (NaiveEnemy enemy : enemies) {
            int enemyX = enemy.getXPosition();
            int enemyY = enemy.getYPosition();
            Image enemyImage = enemy.getSprite();
            if (enemyImage != null) {
                g.drawImage(enemyImage, enemyY * tileSize, enemyX * tileSize,
                        tileSize, tileSize, null);
            }
        }
    }
}
