/**
 * The Item class represents objects that Chip can pick up around the map during the game Each type/kind of item 
 * (keys, boots, microchips) must define their own symbol and logic when used/picked up.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.Image;

public abstract class Item extends MapElement
{
    /**
     * This is an abstract method that returns the sprite image used to represent this item.
     *
     * @return the image of the item sprite
     */
    @Override
    public abstract Image getSprite();

    /**
     * This is an abstract method that determines what happens when a certain item is collected by Chip 
     * (keys to unlock doors, boots to allow walking over specific tiles, etc.)
     *
     * @param chip - player who picks up the item and updates their inventory/status
     */
    public abstract void onPickup(ChipPlayer chip);
}
