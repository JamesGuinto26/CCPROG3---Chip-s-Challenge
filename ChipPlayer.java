/**
 * The ChipPlayer class represents the playable character "Chip" in Chip's Challenge. Chip can collect 
 * items, keys, and microchips, and interact with different tiles in the map.
 * 
 * @author Joseph Degullado, James Guinto
 */
import javax.swing.*;
import java.awt.*;

public class ChipPlayer
{
    private int xPosition; // Chip's current x position
    private int yPosition; // Chip's current y position
    private boolean hasFlippers; // check if Chip has flippers
    private boolean hasFireboots; // check if Chip has fireboots
    private boolean hasRedKey; // check if Chip has red key
    private boolean hasBlueKey; // check if Chip has blue key
    private int redKeys; // Chip's red key count
    private int blueKeys; // Chip's blue key count
    private boolean isAlive; // check if Chip is alive
    private int microchips; // Chip's microchip count
    private boolean atExit; // check if Chip is stepping on exit tile
    private Image chipSprite;

    /**
     * Constructor for ChipPlayer given the starting position in the map.
     * 
     * @param xPosition - the initial x position of Chip
     * @param yPosition - the initial y position of Chip
     */
    public ChipPlayer(int xPosition, int yPosition)
    {
        setXPosition(xPosition);
        setYPosition(yPosition);
        this.hasFlippers = false;
        this.hasFireboots = false;
        this.hasRedKey = false;
        this.hasBlueKey = false;
        this.isAlive = true;
        this.blueKeys = 0;
        this.redKeys = 0;
        this.microchips = 0;
        this.atExit = false;
        this.chipSprite = new ImageIcon("images/Player.png").getImage();
    }

    /**
     * Returns the sprite image used to represent chip.
     *
     * @return the image of chip's sprite
     */
    public Image getSprite()
    {
        return chipSprite;
    }

    /**
     * This method sets hasFireboots to true.
     */
    public void giveFireboots()
    {
        this.hasFireboots = true;
    }

    /**
     * This method sets hasFlippers to true.
     */
    public void giveFlippers()
    {
        this.hasFlippers = true;
    }
    
    /**
     * This method adds one red key count to Chip's inventory.
     */
    public void addRedKey()
    {
        this.redKeys++;
    }

    /**
     * This method checks if Chip has more than 0 red key.
     * 
     * @return true if Chip has more than 0 red keys, otherwise false
     */
    public boolean hasRedKey()
    {
        return this.redKeys > 0;
    }

    /**
     * This method decrements Chip's red key count by one after usage.
     */
    public void useRedKey() 
    {
        if (this.redKeys > 0) 
        {
            this.redKeys--;
        }
    }

    /**
     * This method adds one blue key count to Chip's inventory.
     */
    public void addBlueKey()
    {
        this.blueKeys++;
    }

    /**
     * This method checks if Chip has more than 0 blue key.
     * 
     * @return true if Chip has more than 0 blue keys, otherwise false
     */
    public boolean hasBlueKey()
    {
        return this.blueKeys > 0;
    }

    /**
     * This method decrements Chip's blue key count by one after usage.
     */
    public void useBlueKey() 
    {
        if (this.blueKeys > 0) 
        {
            this.blueKeys--;
        }
    }

    public int getRedKeyCount()
    {
        return this.redKeys;
    }

    public int getBlueKeyCount()
    {
        return this.blueKeys;
    }

    public boolean getFlippersStatus()
    {
        return this.hasFlippers;
    }

    public boolean getFirebootsStatus()
    {
        return this.hasFireboots;
    }

    /**
     * This method adds one microchip count to Chip's inventory.
     */
    public void addMicrochip()
    {
        this.microchips++;
    }

    /**
     * This method returns Chip's current microchip count.
     * 
     * @return Chips's microchip count
     */
    public int getMicrochipCount()
    {
        return this.microchips;
    }

    /**
     * This method checks if Chip can swim (has flippers)
     * 
     * @return true if Chip has flippers, otherwise false
     */
    public boolean canSwim()
    {
        return this.hasFlippers;
    }

    /**
     * This method checks if Chip can walk on fire (has fireboots)
     * 
     * @return true if Chip has fireboots, otherwise false
     */
    public boolean canWalkOnFire()
    {
        return this.hasFireboots;
    }

    /**
     * This method sets Chip's alive status.
     * 
     * @param chipisAlive - boolean value about Chip's life status
     */
    public void setChipAlive(boolean chipisAlive)
    {
        this.isAlive = chipisAlive;
    }

    /**
     * This method returns Chip's current life status
     * 
     * @return true if Chip is alive, otherwise false
     */
    public boolean isChipAlive()
    {
        return this.isAlive;
    }

    /**  
     * This method sets Chip's x position to the value passed in
     * 
     * @param xPosition - Chip's new x position
     */
    public void setXPosition(int xPosition)
    {
        this.xPosition = xPosition;
    }

    /**  
     * This method sets Chip's y position to the value passed in
     * 
     * @param yPosition - Chip's new y position
     */
    public void setYPosition(int yPosition)
    {
        this.yPosition = yPosition;
    }

    /**
     * This method returns Chip's current x position
     * 
     * @return int value of Chip's x position
     */
    public int getXPosition()
    {
        return this.xPosition;
    }

    /**
     * This method returns Chip's current y position
     * 
     * @return int value of Chip's y position
     */
    public int getYPosition()
    {
        return this.yPosition;
    }

    /**
     * This method sets Chip's atExit status to the boolean value passed in.
     * 
     * @param atExit - boolean value of Chip's exit status
     */
    public void setAtExit(boolean atExit) 
    {
        this.atExit = atExit;
    }

    /**
     * This method checks if Chip is already at exit.
     * 
     * @return true if Chip is on an exit tile, otherwise false.
     */
    public boolean isAtExit() 
    {
        return atExit;
    }
}
