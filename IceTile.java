/**
 * The IceTile class represents a slippery surface that makes Chip slide in his current movement direction.
 * Chip continues sliding until he hits a non-ice tile, wall, or obstacle.
 *
 * @author Joseph Degullado, James Guinto
 */
import java.awt.*;
import javax.swing.*;

public class IceTile extends Tile
{
    private static final Image iceTileSprite = new ImageIcon("images/ice.png").getImage();

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the ice tile sprite
     */
    @Override
    public Image getSprite()
    {
        return iceTileSprite;
    }

    /**
     * This method determines if Chip can walk on an IceTile.
     *
     * @return true since IceTiles are walkable
     */
    @Override
    public boolean isWalkable()
    {
        return true;
    }

    /**
     * This method handles the logic when Chip steps on an IceTile.
     * The actual sliding is handled in GameSystem.
     *
     * @param chip - Chip who stepped on the ice
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        System.out.println("You stepped on ice! You'll slide until you hit something.");
    }
}