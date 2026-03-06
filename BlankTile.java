/**
 * The BlankTile class represents a tile on the game map that is represented by '-'. Chip can freely 
 * move on this tile, and it can also optionally hold an Item that Chip can pick up when stepping on a certain tile.
 * 
 * @author Joseph Degullado, James Guinto 
 */
import javax.swing.*;
import java.awt.*;

public class BlankTile extends Tile
{
    private static final Image blankTileSprite = new ImageIcon("images/floor.png").getImage(); // ADDED
    private Item newItem; // item attribute

    /**
     * Default constructor for BlankTile with no item. Null if empty.
     */
    public BlankTile()
    {
        this.newItem = null;
    }

    /**
     * Constructor for a BlankTile that holds an Item.
     *
     * @param item - the item placed on the BlankTile
     */
    public BlankTile(Item item) // a blank tile may have an item on it
    {
        this.newItem = item;
    }

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the blank tile sprite
     */
    @Override
    public Image getSprite()
    {
        return blankTileSprite;
    }

    /**
     * This method handles the condition when Chip enters the BlankTile. If an item is present, it will be picked up by Chip.
     *
     * @param chip - Chip who is entering the tile or may pick an item when present on tile.
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        if (newItem != null)
        {
            newItem.onPickup(chip);
            removeItem();
        }
    }

    /**
     * This method returns the item on top of the BlankTile.
     *
     * @return item on the BlankTile (microchip, key, boots, etc.)
     */
    public Item getItem() 
    {
        return this.newItem;
    }

    public void removeItem() {
        this.newItem = null;
    }
}
