
/**
 * The MapElement class is the abstract class/parent for all objects in the map, such as tiles, items, and doors. 
 * Every MapElement has a visual representation/special characters that represents them upon display.
 * 
 * @author Joseph Degullado, James Guinto
 */
import java.awt.*;

public abstract class MapElement
{
    /**
     * This is an abstract method that returns the sprite image used to represent different map elements.
     *
     * @return the image of the map element sprite
     */
    public abstract Image getSprite();
}
