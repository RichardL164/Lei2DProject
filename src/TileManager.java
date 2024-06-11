import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;
    List<Rectangle> hitboxes;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[12];
        mapTileNum = new int[gp.colTiles][gp.rowTiles];
        hitboxes = new ArrayList<>();
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/Images/regular block.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/Images/black_tile.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/Images/crate.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/Images/floor_button.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/Images/green_lava.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/Images/left_lava.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/Images/ramp_left.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResource("/Images/ramp_right.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResource("/Images/wall.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResource("/Images/water_lava.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResource("/Images/blue_door.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResource("/Images/red_door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.colTiles && row < gp.rowTiles) {
                String line = br.readLine();
                while (col < gp.colTiles) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;

                    // Create hitbox for solid tiles (0 and 7)
                    if (num == 0 || num == 7) {
                        int x = col * gp.tileSize;
                        int y = row * gp.tileSize;
                        hitboxes.add(new Rectangle(x, y, gp.tileSize, gp.tileSize));
                    }

                    col++;
                }
                if (col == gp.colTiles) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Rectangle> getHitboxes() {
        return hitboxes;
    }
    public int getTileType(int x, int y) {
        int col = x / gp.tileSize;
        int row = y / gp.tileSize;
        return mapTileNum[col][row];
    }

    public void draw(Graphics g) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.colTiles && row < gp.rowTiles) {
            int tileNum = mapTileNum[col][row];
            g.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.colTiles) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
