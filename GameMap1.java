/**
 * The GameMap1 class represents the subclass of GameMap. It initializes and sets up its own item placement and map 
 * environment.
 *
 * @author Joseph Degullado, James Guinto
 */
public class GameMap1 extends GameMap
{
    /**
     * This method prepares the map elements, item and tile distribution for map 1 of Chip's Challenge
     */
    @Override
    public void prepareMapElements()
    {
        int i;
        int j;

        setPlayerSpawn(1, 1);

        for (i = 0 ; i < getMapLength() ; i++)
        {
            for (j = 0 ; j < getMapWidth() ; j++)
            {
                if (i == 0 || i == getMapLength() - 1 || j == 0 || j == getMapWidth() - 1)
                {
                    getMap()[i][j] = new WallTile();
                }
            }
        }

        // Walls
        createVerticalWall(2, 2, 7);
        createHorizontalWall(8, 2, 5);
        createVerticalWall(6, 8, 17);
        createHorizontalWall(17, 7, 18);
        createHorizontalWall(12, 2, 5);
        createHorizontalWall(6, 5, 7);
        createVerticalWall(5, 1, 5);
        createHorizontalWall(6, 5, 7);
        createVerticalWall(7, 2, 4);
        createVerticalWall(9, 4, 14);
        createHorizontalWall(7, 14, 18);
        createHorizontalWall(9, 13, 17);
        createVerticalWall(17, 10, 14);
        createHorizontalWall(15, 14, 17);
        createVerticalWall(14, 11, 14);
        createVerticalWall(12, 8, 16);
        createHorizontalWall(14, 1, 4);
        createVerticalWall(4, 15, 17);
        createHorizontalWall(2, 8, 16);
        createVerticalWall(17, 2, 4);
        createHorizontalWall(5, 11, 17);

        // Fire/Water Tiles
        getMap()[15][9] = new WaterTile();
        getMap()[16][9] = new WaterTile();
        getMap()[18][8] = new FireTile();
        getMap()[18][9] = new WaterTile();
        getMap()[18][10] = new FireTile();
        getMap()[18][11] = new WaterTile();
        getMap()[18][12] = new FireTile();
        getMap()[18][13] = new WaterTile();
        getMap()[18][14] = new FireTile();
        getMap()[18][15] = new WaterTile();
        getMap()[18][16] = new FireTile();
        getMap()[13][15] = new FireTile();
        getMap()[13][16] = new FireTile();
        getMap()[14][16] = new FireTile();
        getMap()[18][4] = new FireTile();
        getMap()[3][12] = new WaterTile();
        getMap()[4][12] = new WaterTile();
        getMap()[3][14] = new FireTile();
        getMap()[4][14] = new FireTile();

        // Force Floors
        getMap()[18][6] = new ForceFloor('>');
        getMap()[1][6] = new ForceFloor('>');
        getMap()[1][18] = new ForceFloor('v');
        getMap()[17][18] = new ForceFloor('^');

        // NEW: Ice Tiles
        getMap()[10][10] = new IceTile();
        getMap()[10][11] = new IceTile();
        getMap()[10][12] = new IceTile();
        getMap()[11][12] = new IceTile();
        getMap()[12][12] = new IceTile();

        // NEW: Teleport Tiles (linked pair)
        getMap()[5][5] = new TeleportTile(15, 15, "A");
        getMap()[15][15] = new TeleportTile(5, 5, "A");

        // Other Walls
        getMap()[9][2] = new WallTile();
        getMap()[11][2] = new WallTile();
        getMap()[4][8] = new WallTile();
        getMap()[14][8] = new WallTile();
        getMap()[14][10] = new WallTile();
        getMap()[7][12] = new WallTile();

        // Doors
        getMap()[10][2] = new BlueDoor();
        getMap()[14][7] = new BlueDoor();
        getMap()[14][11] = new RedDoor();
        getMap()[7][13] = new BlueDoor();
        getMap()[5][10] = new RedDoor();

        // Items
        getMap()[16][7] = new BlankTile(new FireBoots());
        getMap()[16][11] = new BlankTile(new BlueKey());
        getMap()[10][4] = new BlankTile(new Microchip());
        getMap()[10][5] = new BlankTile(new RedKey());
        getMap()[14][15] = new BlankTile(new Microchip());
        getMap()[16][2] = new BlankTile(new BlueKey());
        getMap()[1][11] = new BlankTile(new RedKey());
        getMap()[6][18] = new BlankTile(new Flippers());
        getMap()[3][15] = new BlankTile(new Microchip());
        getMap()[3][8] = new BlankTile(new BlueKey());

        // Exit Tiles
        getMap()[18][18] = new ExitTile();

        setRequiredMicrochips(3);
    }
}