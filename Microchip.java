/**
 * The Microchip class represents an item that Chip must collect to complete a level or exit through the ExitTile. 
 * Collecting all required microchips per map may unlock the ExitTile or move on to the next level/map.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class Microchip extends Item
{
    private static final Image microchipSprite = new ImageIcon("images/microchip.png").getImage();

    /**
     * Returns the sprite image used to represent this item.
     *
     * @return the image of the microchip sprite
     */
    @Override
    public Image getSprite()
    {
        return microchipSprite;
    }

    /**
     * This method determines what happens when Chip picks up a microchip. When collected, it gets added to Chip's inventory.
     *
     * @param chip - player who picks up the microchip
     */
    @Override
    public void onPickup(ChipPlayer chip)
    {
        chip.addMicrochip();
    } 
}
