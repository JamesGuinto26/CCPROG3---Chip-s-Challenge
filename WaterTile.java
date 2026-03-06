import javax.swing.*;
import java.awt.*;

/**
 * The WaterTile class represents a tile covered by water in the game. If Chip steps on it without flippers, 
 * Chip will drown and die.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class WaterTile extends Tile
{
    private static final Image waterTileSprite = new ImageIcon("images/water.png").getImage();

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the water tile sprite
     */
    @Override
    public Image getSprite()
    {
        return waterTileSprite;
    }

    /**
     * This method etermines if Chip can walk/pass through a WaterTile. They are walkable/passable, but dangerous.
     *
     * @return true since Chip can still pass through it (but will end up drowning)
     */
    @Override
    public boolean isWalkable()
    {
        return true;
    }

    /**
     * This method handles the logic/condition when Chip enters a WaterTile.
     *
     * @param chip - Chip trying to enter a WaterTile
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        if (chip.canSwim())
        {
            chip.setChipAlive(true);
        }
        else
        {
            System.out.println("Oh no! Chip drowned.");
            chip.setChipAlive(false);
        }
    }
}
