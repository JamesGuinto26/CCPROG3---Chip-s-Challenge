/**
 * The Fireboots class represents an item that Chip can equip, allowing them to pass/walk through FireTiles without dying.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class FireBoots extends Item
{
    private static final Image firebootsSprite = new ImageIcon("images/fireBoots.png").getImage();

    /**
     * Returns the sprite image used to represent this item.
     *
     * @return the image of the fireboot sprite
     */
    @Override
    public Image getSprite()
    {
        return firebootsSprite;
    }

    /**
     * This method handles the behavior/logic when Chip picks up fireboots
     * 
     * @param chip - player who picks up the fireboots
     */
    @Override
    public void onPickup(ChipPlayer chip)
    {
        chip.giveFireboots();
    }
}
