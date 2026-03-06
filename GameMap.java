/**
 * The GameMap class represents the 2D map of Chip's Challenge. This abstract class also determines/creates 
 * the structure of the map where different MapElements are placed. Each subclass of GameMap must also define
 * their own layout and placement of different MapElement
 * 
 * @author Joseph Degullado, James Guinto
 */
public abstract class GameMap
{
    private MapElement[][] map; // 2D grid containing the items and tiles
    private int mapLength; // number of rows in the map
    private int mapWidth; // number of columns in the map
    private int spawnX; // Chip's starting x position
    private int spawnY; // Chip's starting y position
    private int requiredMicrochips; // number of microchips required to complete the level

    /**
     * The constructor that initializes the map to a 20x20 grid (toned-down version), fills it with BlankTiles,
     * and prepares the map elements that are specific for the different subclasses/maps.
     */
    public GameMap()
    {
        this.mapLength = 20;
        this.mapWidth = 20;
        map = new MapElement[mapLength][mapWidth];
        fillMap();
        prepareMapElements();
    }

    /**
     * This method fills the grid/map with blank tiles and called during map initialization.
     */
    public void fillMap()
    {
        int i;
        int j;

        for (i = 0 ; i < mapLength ; i++)
        {
            for (j = 0 ; j < mapWidth ; j++)
            {
                map[i][j] = new BlankTile();
            }
        }
    }

    /**
     * This abstract method that must be implemented by subclasses/specific maps to define
     * specific map layouts, item placement, etc.
     */
    public abstract void prepareMapElements(); // subclasses must implement

    /**
     * This method sets Chip's starting/spawn position in the map
     *
     * @param xPosition - x coordinate where Chip spawns
     * @param yPosition - y coordinate where Chip spawns
     */
    public void setPlayerSpawn(int xPosition, int yPosition)
    {
        this.spawnX = xPosition;
        this.spawnY = yPosition;
    }

    /**
     * This method returns Chip's spawn x coordinte
     * 
     * @return int of x coordinate of the Chip's spawn position
     */
    public int getSpawnX()
    {
        return this.spawnX;
    }

    /**
     * This method returns Chip's spawn y coordinte
     * 
     * @return int of y coordinate of the Chip's spawn position
     */
    public int getSpawnY()
    {
        return this.spawnY;
    }

    /**
     * This method returns the 2D map of Chip's challenge.
     * 
     * @return 2D array representing the game map/layout
     */
    public MapElement[][] getMap()
    {
        return this.map;
    }

    /**
     * This method returns the length of the game map.
     * 
     * @return number of rows in the 2D map
     */
    public int getMapLength()
    {
        return this.mapLength;
    }

    /**
     * This method returns the width of the game map.
     * 
     * @return number of columns in the 2D map
     */
    public int getMapWidth()
    {
        return this.mapWidth;
    }

    /**
     * This method sets the required number of microchips for Chip to win the current level
     *
     * @param requiredMicrochips - number of microchips needed in order to exit/win
     */
    public void setRequiredMicrochips(int requiredMicrochips)
    {
        this.requiredMicrochips = requiredMicrochips;
    }

    /**
     * This method returns the required amount of microchips in the map
     * 
     * @return number of required microchips to complete/exit the level
     */
    public int getRequiredMicrochips()
    {
        return this.requiredMicrochips;
    }

    /**
     * THis method creates a vertical wall by filling a column with WallTile objects
     *
     * @param col - column index where vertical wall should be placed
     * @param startRow - starting row index of wall
     * @param endRow - ending row index of wall
     */
    protected void createVerticalWall(int col, int startRow, int endRow)
    {
        int i;

        for (i = startRow; i <= endRow; i++)
        {
            map[i][col] = new WallTile();
        }
    }

    /**
     * This method creates a horizontal wall by filling a row with WallTile objects
     *
     * @param row - row index where horizontal wall should be placed
     * @param startCol - starting col index of wall
     * @param endCol - ending col index of wall
     */
    protected void createHorizontalWall(int row, int startCol, int endCol)
    {
        int j;

        for (j = startCol; j <= endCol; j++)
        {
            map[row][j] = new WallTile();
        }
    }
}