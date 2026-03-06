/**
 * The TeleportTile class represents a teleportation tile that transports Chip to another linked teleport tile.
 *
 * @author Joseph Degullado, James Guinto
 */
import java.awt.*;
import javax.swing.*;

public class TeleportTile extends Tile {
    private static final Image teleportTileSprite = new ImageIcon("images/teleport.png").getImage();
    private int targetX;
    private int targetY;
    private String teleportId;

    /**
     * Constructor for TeleportTile with target coordinates.
     *
     * @param targetX    - x coordinate of destination teleport
     * @param targetY    - y coordinate of destination teleport
     * @param teleportId - identifier to link teleport pairs
     */
    public TeleportTile(int targetX, int targetY, String teleportId) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.teleportId = teleportId;
    }

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the teleport tile sprite
     */
    @Override
    public Image getSprite() {
        return teleportTileSprite;
    }

    /**
     * This method determines if Chip can walk on a TeleportTile.
     *
     * @return true since TeleportTiles are walkable
     */
    @Override
    public boolean isWalkable() {
        return true;
    }

    /**
     * This method handles teleportation when Chip steps on the tile.
     *
     * @param chip - Chip who stepped on the teleport
     */
    @Override
    public void enterTile(ChipPlayer chip) {
        System.out.println("Whoosh! You've been teleported!");
        // Actual teleportation logic is handled in GameSystem
    }

    /**
     * Gets the target X coordinate for teleportation.
     *
     * @return target X coordinate
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Gets the target Y coordinate for teleportation.
     *
     * @return target Y coordinate
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Gets the teleport identifier for linking pairs.
     *
     * @return teleport identifier
     */
    public String getTeleportId() {
        return teleportId;
    }
}
