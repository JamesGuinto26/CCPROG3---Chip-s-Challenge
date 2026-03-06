/**
 * The ForceFloor class represents a special tile that automatically pushes Chip in a specific direction. 
 * Each ForceFloor has a direction symbol that determines the direction where Chip will be moved.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class ForceFloor extends Tile
{
    private char directionSymbol; // character representing the direction
    private int xDirection; // x-coordinate of movement
    private int yDirection; // y-coordinate of movement
    private Image sprite;

    /**
     * Constructor for ForceFloor with a specific direction. This determines the way where Chip will be pushed.
     *
     * @param direction - the symbol for the directions (^, v, <, >)
     */
    public ForceFloor(char direction)
    {
        this.directionSymbol = direction;

        switch(direction)
        {
            case '^': { // up
                xDirection = -1;
                yDirection = 0;
                sprite = new ImageIcon("images/forceUp.png").getImage();
                break;
            }
            case 'v': { // down
                xDirection = 1;
                yDirection = 0;
                sprite = new ImageIcon("images/forceDown.png").getImage();
                break;
            }
            case '<': { // left
                xDirection = 0;
                yDirection = -1;
                sprite = new ImageIcon("images/forceLeft.png").getImage();
                break;
            }
            case '>': { // right
                xDirection = 0;
                yDirection = 1;
                sprite = new ImageIcon("images/forceRight.png").getImage();
                break;
            }
        }
    }

    /**
     * Returns the sprite image used to represent this tile.
     *
     * @return the image of the force floor sprite
     */
    @Override
    public Image getSprite()
    {
        return sprite;
    }

    /**
     * This method determines if Chip can walk/pass through a ForceFloor
     *
     * @return true, since the ForceFloor can be walked/stepped on.
     */
    @Override
    public boolean isWalkable() 
    {
        return true; 
    }

    /**
     * This method handles the prompt when Chip steps onto the ForceFloor
     *
     * @param chip - Chip who steps on the ForceFloor
     */
    @Override
    public void enterTile(ChipPlayer chip) 
    {
        System.out.println("You stepped on a force floor!");
    }

    /**
     * This method returns the x coordinate direction of the ForceFloor. It also determines the vertical 
     * direction of Chip (Array-based)
     *
     * @return int value of Chip's x direction
     */
    public int getXDirection() 
    { 
        return xDirection; 
    }

    /**
     * This method returns the y coordinate direction of the ForceFloor. Determines the horizontal direction 
     * of Chip (Array-based)
     *
     * @return int value of Chip's x direction
     */
    public int getYDirection() 
    { 
        return yDirection; 
    }
}
