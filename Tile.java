/**
 * The Tile class represents all the elements in the map that can be stepped on. There are different kinds of tiles
 * in the game, each having their own set of conditions and interaction with Chip.
 * 
 * @author Joseph Degullado, James Guinto
 */
import java.awt.Image;

public abstract class Tile extends MapElement
{
    /**
     * This is an abstract method that returns the sprite image used to represent this tile.
     *
     * @return the image of the tile sprite
     */
    public abstract Image getSprite();

    /**
     * This method checks whether the current tile is walkable or not.
     * 
     * @return true if the tile is walkable, otherwise false
     */
    public boolean isWalkable()
    {
        return true;
    }

    /**
     * This is an abstract method that must be implemented by its subclasses. Each subclass has their own set of logic and conditions
     * depending on the tile type. Chip's interaction with different tile types will also vary.
     */
    public abstract void enterTile(ChipPlayer chip);
}
