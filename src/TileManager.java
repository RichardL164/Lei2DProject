import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.colTiles][gp.rowTiles];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/regular block.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/left_lava.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.colTiles && row < gp.rowTiles) {
            g.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
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

