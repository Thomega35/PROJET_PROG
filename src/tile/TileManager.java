package tile;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int maxTiles = 10;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp =  gp;
		
		tile = new Tile[maxTiles];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		// Charge les diff�rentes tuiles dans le vecteur tile[]
	
		try {
			tile[0] = new Tile(false);
			tile[0].image = ImageIO.read(new File("res/tiles/GRASS.png"));
			
			tile[1] = new Tile(true);
			tile[1].image = ImageIO.read(new File("res/tiles/BRICK.png"));
			
			tile[2] = new Tile(true);
			tile[2].image = ImageIO.read(new File("res/tiles/WATER.png"));
			
			tile[3] = new Tile(true);
			tile[3].image = ImageIO.read(new File("res/tiles/LAVA.png"));
			
			tile[4] = new Tile(false);
			tile[4].image = ImageIO.read(new File("res/tiles/SAND.png"));
			
			tile[5] = new Tile(false);
			tile[5].image = ImageIO.read(new File("res/tiles/SNOW.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Cette m�thode charge la map 
	public void loadMap() {
		//charger le fichier txt de la map
		try {
			
			InputStream is = new FileInputStream("res/maps/map2.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			// Parcourir le fichier txt pour r�cup�rer les valeurs
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum [col][row] = num;
					col++;
					}
					if (col == gp.maxScreenCol) {
						col = 0;
						row ++;
					}
				
			}
			
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col ++;
			x += gp.tileSize;
			if (col == gp.maxScreenCol) {
				col = 0;
				row ++;
				x = 0;
				y += gp.tileSize;
			}
		}
		
	}
}
