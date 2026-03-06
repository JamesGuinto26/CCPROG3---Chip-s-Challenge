/**
 * The FireTile class represents a tile covered by flames/fire in the game. If Chip steps on it without 
 * fireboots, Chip will burn and die.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class FireTile extends Tile
{
    private static final Image fireTileSprite = new ImageIcon("images/fire.png").getImage();

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the fire tile sprite
     */
    @Override
    public Image getSprite()
    {
        return fireTileSprite;
    }

    /**
     *  This method etermines if Chip can walk on a FireTile. They are walkable, but deadly.
     *
     *  @return true since Chip can still walk on it (but will end up burning)
     */
    @Override
    public boolean isWalkable()
    {
        return true;
    }

    /**
     * This method handles the logic/condition when Chip steps on a FireTile.
     *
     * @param chip - Chip trying to enter/step on a FireTile
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        if (chip.canWalkOnFire())
        {
            chip.setChipAlive(true);
        }
        else
        {
            chip.setChipAlive(false);
        }
    }
}
