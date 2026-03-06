/**
 * The RedDoor class represents a door on the GameMap that can be only
 * opened when Chip has a red key.
 * 
 * @author Joseph Degullado, James Guinto
 *
 */
import javax.swing.*;
import java.awt.*;

public class RedDoor extends Door
{
    private Image redDoorSprite = new ImageIcon("images/redDoor.png").getImage();

    /**
     * Returns the sprite image used to represent this door.
     *
     * @return the image of the red door sprite
     */
    @Override
    public Image getSprite()
    {
        return redDoorSprite;
    }

    /**
     * This method checks if current red door is currently locked
     *
     * @return true if door is locked, otherwise false.
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * This method checks if Chip have a red key. If yes, the current red door will be unlocked, otherwise, it will remain locked.
     *
     * @param chip - if Chip has a red key, then Chip can unlock the red door
     */
    public void enterDoor(ChipPlayer chip)
    {
        if (locked) 
        {
            if (chip.hasRedKey()) 
            {
                chip.useRedKey();
                locked = false;
                redDoorSprite = new ImageIcon("images/floor.png").getImage();
            } 
            else 
            {
                locked = true;
            }
        }
    }
}
