import java.awt.*;

/**
 * The Door class represents a door on the GameMap. All doors are locked at the start of the game and 
 * can be unlocked by their corresponding colored keys.
 * 
 * @author Joseph Degullado, James Guinto
 */
public abstract class Door extends MapElement
{
    protected boolean locked = true; // Always locked at first (must be unlocked by key)

    /**
     * Abstract method that returns the sprite image used to represent this door.
     *
     * @return the image of the door sprite
     */
    @Override
    public abstract Image getSprite();

    /**
     * This method checks if the door is currently locked.
     *
     * @return true if the door is locked, false if door has been unlocked
     */
    public boolean isLocked() // double check this parameter
    {
        return locked;
    }

    /**
     * This method determines the condition/behavior when Chip tries to enter. This is an abstract method
     * so subclasses must implement this method for their own behaviors.
     *
     * @param chip - Chip who is entering the door
     */
    public abstract void enterDoor(ChipPlayer chip);
}
