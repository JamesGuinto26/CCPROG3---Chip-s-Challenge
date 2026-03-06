/**
 * The GameMap2 class represents the subclass of GameMap. It initializes and sets up its own item placement and map 
 * environment.
 *
 * @author Joseph Degullado, James Guinto
 */
public class GameMap2 extends GameMap {

    /**
     * This method prepares the map elements, item and tile distribution for map 2 of Chip's Challenge
     */
    @Override
    public void prepareMapElements()
    {
        int i;
        int j;

        setPlayerSpawn(1, 1);
        setRequiredMicrochips(5); // More microchips needed for level 2

        // Create border walls
        for (i = 0; i < getMapLength(); i++)
        {
            for (j = 0; j < getMapWidth(); j++)
            {
                if (i == 0 || i == getMapLength() - 1 || j == 0 || j == getMapWidth() - 1) {
                    getMap()[i][j] = new WallTile();
                }
            }
        }

        createVerticalWall(2, 1, 3);
        createVerticalWall(15, 2, 4);
        createHorizontalWall(4, 16, 18);
        createHorizontalWall(2, 6, 14);
        createVerticalWall(11, 3, 6);
        createVerticalWall(9, 5, 8);
        createVerticalWall(15, 15, 18);
        createVerticalWall(17, 6, 15);
        createHorizontalWall(8, 10, 16); //
        createHorizontalWall(13, 14, 16);
        createHorizontalWall(13, 19, 12);
        createHorizontalWall(13, 9, 12);
        createVerticalWall(9, 9, 12);
        createHorizontalWall(15, 10, 14);
        createVerticalWall(8, 13, 15);
        createVerticalWall(2, 13, 15);
        createVerticalWall(8, 17, 18);
        createVerticalWall(2, 17, 18);
        createHorizontalWall(11, 3, 8);
        createHorizontalWall(7, 3, 7);
        createVerticalWall(8, 6, 10);
        createHorizontalWall(13, 4, 7);

        getMap()[2][5] = new WallTile();
        getMap()[3][5] = new WallTile();
        getMap()[5][15] = new WallTile();
        getMap()[6][15] = new WallTile();
        getMap()[6][14] = new WallTile();
        getMap()[6][12] = new WallTile();
        getMap()[5][1] = new WallTile();
        getMap()[5][2] = new WallTile();
        getMap()[5][8] = new WallTile();
        getMap()[5][7] = new WallTile();
        getMap()[16][10] = new WallTile();
        getMap()[18][10] = new WallTile();
        getMap()[13][1] = new WallTile();
        getMap()[12][8] = new WallTile();
        getMap()[8][3] = new WallTile();
        getMap()[10][3] = new WallTile();
        getMap()[9][10] = new FireTile();
        getMap()[10][10] = new FireTile();
        getMap()[10][11] = new WaterTile();
        getMap()[10][12] = new WaterTile();
        getMap()[11][10] = new FireTile();
        getMap()[11][11] = new WaterTile();
        getMap()[11][12] = new WaterTile();
        getMap()[10][14] = new WaterTile();
        getMap()[10][15] = new WaterTile();
        getMap()[11][14] = new WaterTile();
        getMap()[11][15] = new WaterTile();
        getMap()[9][11] = new FireTile();
        getMap()[9][12] = new FireTile();
        getMap()[9][13] = new FireTile();
        getMap()[9][14] = new FireTile();
        getMap()[9][15] = new FireTile();
        getMap()[9][16] = new FireTile();
        getMap()[10][16] = new FireTile();
        getMap()[11][16] = new FireTile();
        getMap()[12][10] = new FireTile();
        getMap()[12][11] = new FireTile();
        getMap()[12][12] = new FireTile();
        getMap()[12][13] = new FireTile();
        getMap()[12][14] = new FireTile();
        getMap()[12][15] = new FireTile();
        getMap()[12][16] = new FireTile();

        getMap()[8][7] = new WaterTile();
        getMap()[9][7] = new WaterTile();
        getMap()[8][6] = new WaterTile();
        getMap()[8][5] = new WaterTile();
        getMap()[9][5] = new WaterTile();
        getMap()[10][7] = new WaterTile();
        getMap()[10][6] = new WaterTile();
        getMap()[10][5] = new WaterTile();
        getMap()[7][1] = new WaterTile();
        getMap()[8][1] = new WaterTile();
        getMap()[9][1] = new WaterTile();
        getMap()[10][1] = new WaterTile();
        getMap()[11][1] = new WaterTile();

        getMap()[3][3] = new WaterTile();
        getMap()[3][4] = new WaterTile();
        getMap()[2][3] = new WaterTile();
        getMap()[2][4] = new WaterTile();
        getMap()[1][16] = new FireTile();
        getMap()[2][16] = new FireTile();
        getMap()[3][16] = new FireTile();
        getMap()[3][17] = new FireTile();
        getMap()[3][18] = new FireTile();
        getMap()[1][17] = new FireTile();
        getMap()[1][18] = new FireTile();
        getMap()[2][18] = new FireTile();
        getMap()[3][18] = new FireTile();
        getMap()[3][14] = new WaterTile();
        getMap()[3][13] = new WaterTile();
        getMap()[3][12] = new WaterTile();
        getMap()[4][12] = new WaterTile();
        getMap()[5][12] = new WaterTile();
        getMap()[5][13] = new WaterTile();
        getMap()[5][14] = new WaterTile();
        getMap()[4][14] = new WaterTile();
        getMap()[5][3] = new FireTile();
        getMap()[5][4] = new FireTile();
        getMap()[5][5] = new FireTile();
        getMap()[5][6] = new FireTile();
        getMap()[15][4] = new FireTile();
        getMap()[16][4] = new FireTile();
        getMap()[15][5] = new FireTile();
        getMap()[15][6] = new FireTile();
        getMap()[16][6] = new FireTile();
        getMap()[17][6] = new FireTile();
        getMap()[17][5] = new FireTile();
        getMap()[17][4] = new FireTile();

        // NEW: Ice Tiles
        getMap()[14][3] = new IceTile();
        getMap()[14][4] = new IceTile();
        getMap()[14][5] = new IceTile();
        getMap()[15][3] = new IceTile();
        getMap()[16][3] = new IceTile();

        // NEW: Teleport Tiles (two linked pairs)
        getMap()[2][2] = new TeleportTile(17, 17, "A");
        getMap()[17][17] = new TeleportTile(2, 2, "A");
        getMap()[8][15] = new TeleportTile(12, 5, "B");
        getMap()[12][5] = new TeleportTile(8, 15, "B");

        // NEW: Naive Enemies
        // These will be added to the GameSystem's enemy list when the map is loaded
        // Horizontal moving enemy
        // Vertical moving enemy

        getMap()[16][5] = new BlankTile(new RedKey());
        getMap()[1][10] = new BlankTile(new RedKey());
        getMap()[10][18] = new BlankTile(new RedKey());
        getMap()[11][18] = new BlankTile(new BlueKey());
        getMap()[2][17] = new BlankTile(new Microchip());
        getMap()[18][18] = new BlankTile(new Microchip());
        getMap()[17][18] = new BlankTile(new FireBoots());
        getMap()[18][17] = new BlankTile(new BlueKey());
        getMap()[10][13] = new BlankTile(new Microchip());
        getMap()[14][1] = new BlankTile(new Microchip());
        getMap()[18][1] = new BlankTile(new RedKey());
        getMap()[11][13] = new BlankTile(new BlueKey());
        getMap()[17][14] = new BlankTile(new Flippers());
        getMap()[9][6] = new BlankTile(new Microchip());

        getMap()[1][3] = new ForceFloor('>');
        getMap()[7][16] = new ForceFloor('^');
        getMap()[5][16] = new ForceFloor('>');
        getMap()[5][18] = new ForceFloor('v');
        getMap()[16][8] = new ForceFloor('<');
        getMap()[16][2] = new ForceFloor('<');

        getMap()[15][2] = new RedDoor();
        getMap()[6][13] = new RedDoor();
        getMap()[13][13] = new RedDoor();
        getMap()[1][15] = new BlueDoor();
        getMap()[9][3] = new RedDoor();
        getMap()[15][16] = new BlueDoor();
        getMap()[17][10] = new BlueDoor();

        getMap()[4][13] = new ExitTile();
    }

    /**
     * This method is called after the map is loaded to add enemies to the game system.
     *
     * @param gameSystem - the game system to add enemies to
     */
    public void addEnemiesToGame(GameSystem gameSystem) {
        // Add a horizontally moving enemy
        gameSystem.getEnemies().add(new NaiveEnemy(8, 8, true, this));
        // Add a vertically moving enemy
        gameSystem.getEnemies().add(new NaiveEnemy(12, 12, false, this));
        // Add another horizontally moving enemy in a different area
        gameSystem.getEnemies().add(new NaiveEnemy(15, 7, true, this));
    }
}