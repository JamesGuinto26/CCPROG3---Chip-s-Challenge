/**
 * Represents a RedKey item in the game. When picked up by Chip, it gets added to their inventory.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class RedKey extends Item
{
    private static final Image redKeySprite = new ImageIcon("images/redKey.png").getImage();

    /**
     * Returns the sprite image used to represent this item.
     *
     * @return the image of the red key sprite
     */
    @Override
    public Image getSprite()
    {
        return redKeySprite;
    }

    /**
     * This method handles operation when Chip picks up this red key. When picked up, it gets added to their inventory.
     *
     * @param chip - player who picks up the red key
     */
    @Override
    public void onPickup(ChipPlayer chip)
    {
        chip.addRedKey();
    }    
}
