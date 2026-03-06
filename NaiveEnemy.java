/**
 * The NaiveEnemy class represents an enemy that moves in straight lines and reverses direction when blocked.
 * If Chip touches the enemy, Chip loses a life.
 *
 * @author Joseph Degullado, James Guinto
 */
import java.awt.*;
import javax.swing.*;

public class NaiveEnemy extends MapElement
{
    private int xPosition;
    private int yPosition;
    private int directionX; // -1, 0, or 1 for movement
    private int directionY; // -1, 0, or 1 for movement
    private boolean isMovingHorizontal; // true for left-right, false for up-down
    private static final Image enemySprite = new ImageIcon("images/Enemy.png").getImage();

    /**
     * Constructor for NaiveEnemy.
     *
     * @param xPosition - starting x position
     * @param yPosition - starting y position
     * @param isMovingHorizontal - true for horizontal movement, false for vertical
     * @param map - the game map for position validation
     */
    public NaiveEnemy(int xPosition, int yPosition, boolean isMovingHorizontal, GameMap map)
    {
        // Validate spawn position
        if (xPosition < 0 || xPosition >= map.getMapLength() ||
                yPosition < 0 || yPosition >= map.getMapWidth() ||
                map.getMap()[xPosition][yPosition] instanceof WallTile) {
            throw new IllegalArgumentException("Invalid enemy spawn position at (" + xPosition + ", " + yPosition + ")");
        }

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isMovingHorizontal = isMovingHorizontal;

        // Set initial direction based on movement type
        if (isMovingHorizontal) {
            this.directionX = 0;
            this.directionY = 1; // Start moving right
        } else {
            this.directionX = 1; // Start moving down
            this.directionY = 0;
        }
    }

    @Override
    public Image getSprite()
    {
        return enemySprite;
    }

    /**
     * Moves the enemy and handles direction reversal when blocked.
     *
     * @param map - the game map to check for obstacles
     */
    public void move(GameMap map)
    {
        int newX = xPosition + directionX;
        int newY = yPosition + directionY;

        // Check if next position is valid
        if (newX < 0 || newX >= map.getMapLength() ||
                newY < 0 || newY >= map.getMapWidth() ||
                map.getMap()[newX][newY] instanceof WallTile) {
            // Reverse direction
            directionX = -directionX;
            directionY = -directionY;
        } else {
            // Move to new position
            xPosition = newX;
            yPosition = newY;
        }
    }

    /**
     * Checks if enemy collides with Chip.
     *
     * @param chip - the player to check collision with
     * @return true if collision occurred, false otherwise
     */
    public boolean checkCollision(ChipPlayer chip)
    {
        return (xPosition == chip.getXPosition() && yPosition == chip.getYPosition());
    }

    public int getXPosition()
    {
        return xPosition;
    }

    public int getYPosition()
    {
        return yPosition;
    }

    public void setXPosition(int xPosition)
    {
        this.xPosition = xPosition;
    }

    public void setYPosition(int yPosition)
    {
        this.yPosition = yPosition;
    }
}