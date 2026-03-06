/**
 * The BlueDoor class represents a door on the GameMap that can be only opened when Chip has a blue key.
 *
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class BlueDoor extends Door
{
    private Image blueDoorSprite = new ImageIcon("images/blueDoor.png").getImage();

    /**
     * Returns the sprite image used to represent this door.
     *
     * @return the image of the blue door sprite
     */
    @Override
    public Image getSprite()
    {
        return blueDoorSprite;
    }

    /**
     * This method checks if the current blue door is currently locked
     *
     * @return true if door is locked, otherwise false.
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * Checks if Chip have a blue key. If yes, the current BlueDoor will be unlocked, otherwise, it will remain locked.
     *
     * @param chip - Chip who is trying to enter the blue door (will be check for blue keys)
     */
    public void enterDoor(ChipPlayer chip)
    {
        if (locked) 
        {
            if (chip.hasBlueKey()) 
            {
                chip.useBlueKey();
                locked = false;
                blueDoorSprite = new ImageIcon("images/floor.png").getImage();
            } 
            else 
            {
                locked = true;
            }
        }
    }
}
