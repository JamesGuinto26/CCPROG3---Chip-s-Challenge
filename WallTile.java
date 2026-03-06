/**
 * The WallTile class represents a wall tile on the map. This tile shapes the whole layout and foundation of the map, such
 * as creating borders, rooms, dividers, and more. Just like in real life, Chip cannot walk through a WallTile.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class WallTile extends Tile
{
    private static final Image wallTileSprite = new ImageIcon("images/wall.png").getImage();

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the wall tile sprite
     */
    @Override
    public Image getSprite()
    {
        return wallTileSprite;
    }

    /**
     * This method checks if the tile is walkable.
     * 
     * @return false since wall tiles are always inaccesible and closed
     */
    @Override
    public boolean isWalkable()
    {
        return false;
    }

    /**
     * This method handles the logic when Chip enters or walks through a tile
     * 
     * @param chip - Chip trying to pass through the wall tile
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        System.out.println("Ouch! There is a wall here.");
    }
}
