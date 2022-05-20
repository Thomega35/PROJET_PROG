package tile;

import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import badGuys.Monsters;
import entity.Entity;
import entity.Player;
import entityItem.Items;
import main.GamePanel;
import entityItem.ChestUnlock;
import entityItem.PotionHeal;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int maxTiles = 10;
	int mapTileNum[][];
	String tabMaps[];
	public Items itemMaps[][];
	public int numMap;
	
	public TileManager(GamePanel gp, int numMap) {
		this.gp =  gp;
		
		tile = new Tile[maxTiles];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		tabMaps = new String[10];
		tabMaps[0] = "res/maps/map.txt";
		tabMaps[1] = "res/maps/map2.txt";
		tabMaps[2] = "res/maps/map3.txt";
		tabMaps[3] = "res/maps/map4.txt";
		tabMaps[4] = "res/maps/map5.txt";
		tabMaps[5] = "res/maps/map6.txt";
		tabMaps[6] = "res/maps/map7.txt";
		tabMaps[7] = "res/maps/map8.txt";
		tabMaps[8] = "res/maps/map9.txt";
		tabMaps[9] = "res/maps/map10.txt";
		itemMaps=new Items[4][6];
		itemMaps[3][0]=new ChestUnlock(gp,new PotionHeal(gp));
		this.numMap = numMap;
		loadMap();
	}
	
	public void getTileImage() {
		// Charge les diff�rentes tuiles dans le vecteur tile[]
	
		try {
			tile[0] = new Tile(true);
			tile[0].image = ImageIO.read(new File("res/new_tiles/grass.png"));
			
			tile[1] = new Tile(false);
			tile[1].image = ImageIO.read(new File("res/new_tiles/rock.png"));
			
			tile[2] = new Tile(false);
			tile[2].image = ImageIO.read(new File("res/new_tiles/water.png"));
			
			tile[3] = new Tile(true);
			tile[3].image = ImageIO.read(new File("res/new_tiles/dirt.png"));
			
			tile[4] = new Tile(true);
			tile[4].image = ImageIO.read(new File("res/new_tiles/sand.png"));
			
			tile[5] = new Tile(true);
			tile[5].image = ImageIO.read(new File("res/new_tiles/red_sand.png"));

			tile[6] = new Tile(false);
			tile[6].image = ImageIO.read(new File("res/new_tiles/tree.png"));

			tile[7] = new Tile(true);
			tile[7].image = ImageIO.read(new File("res/new_tiles/dirt_with_grass.png"));

			tile[8] = new Tile(true);
			tile[8].image = ImageIO.read(new File("res/new_tiles/grass_to_sand.png"));

			tile[9] = new Tile(true);
			tile[9].image = ImageIO.read(new File("res/new_tiles/palm_tree.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Retourne le type de case qui est aux coordonn�es x, y
	public Tile whichBox(int x, int y) {
		int tileNumber = mapTileNum[x/gp.tileSize][y/gp.tileSize];
		return tile[tileNumber];
	}

	// Retourne si le joueur peut bouger sur cette case
	public boolean canMove(int x, int y) {
		boolean inMap = x < gp.screenWidth && x > 0 && y < gp.screenHeight && y > 0;
		return inMap && whichBox(x, y).collision;
	}

	// Cette m�thode charge la map 
	public void loadMap() {
		//charger le fichier txt de la map
		try {


			InputStream is = new FileInputStream(tabMaps[numMap]);
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
