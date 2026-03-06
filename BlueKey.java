/**
 * Represents a BlueKey item in the game. When picked up by Chip, it gets added to their inventory.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class BlueKey extends Item
{
    private static final Image blueKeySprite = new ImageIcon("images/blueKey.png").getImage();

    /**
     * Returns the sprite image used to represent this item.
     *
     * @return the image of the blue key sprite
     */
    @Override
    public Image getSprite()
    {
        return blueKeySprite;
    }

    /**
     * This method handles operation when Chip picks up this blue key. When picked up, it gets added to their inventory.
     *
     * @param chip - Chip who picks up the blue key
     */
    @Override
    public void onPickup(ChipPlayer chip)
    {
        chip.addBlueKey();;
    }
}
