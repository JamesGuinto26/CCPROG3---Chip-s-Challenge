/**
 * The ExitTile class represents an exit tile/point in the game. To exit, Chip must acquire a certain number 
 * of microchips in a map to pass through the ExitTile.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class ExitTile extends Tile
{
    private static final Image exitTileSprite = new ImageIcon("images/exit.png").getImage();

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the exit tile sprite
     */
    @Override
    public Image getSprite()
    {
        return exitTileSprite;
    }

    /**
     * This method determines if Chip can walk/pass through the ExitTile
     *
     * @return  true since exit tile is always walkable
     */
    @Override
    public boolean isWalkable()
    {
        return true;
    }

    /**
     * This method marks Chip as having entered/reached the ExitTile.
     *
     * @param chip - Chip who is trying to exit the map
     */
    @Override
    public void enterTile(ChipPlayer chip)
    {
        chip.setAtExit(true);
    }    
}
