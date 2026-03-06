/**
 * The Flippers class represents an item that Chip can equip, allowing them to pass/swim through WaterTiles 
 * without drowning.
 *
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class Flippers extends Item
{
    private static final Image flippersSprite = new ImageIcon("images/flippers.png").getImage();

    /**
     * Returns the sprite image used to represent this item.
     *
     * @return the image of the flippers sprite
     */
    @Override
    public Image getSprite()
    {
        return flippersSprite;
    }

    /**
     * This method handles the behavior/logic when Chip picks up flippers.
     *
     *  @param chip - Chip who picks up the flippers.
     */
    @Override
    public void onPickup(ChipPlayer chip)
    {
        chip.giveFlippers();
        System.out.println("You equipped Flippers!");
    }
}
