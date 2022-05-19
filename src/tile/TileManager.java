package tile;

import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import entity.Entity;
import entity.Monsters;
import entityItem.Items;
import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int maxTiles = 10;
	int mapTileNum[][];
	String tabMaps[];
	public int numMap;
	
	public TileManager(GamePanel gp, int numMap) {
		this.gp =  gp;
		
		tile = new Tile[maxTiles];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		tabMaps = new String[4];
		tabMaps[0] = "res/maps/map.txt";
		tabMaps[1] = "res/maps/map2.txt";
		tabMaps[2] = "res/maps/map3.txt";
		tabMaps[3] = "res/maps/map4.txt";
		this.numMap = numMap;
		loadMap();
	}
	
	public void getTileImage() {
		// Charge les diff�rentes tuiles dans le vecteur tile[]
	
		try {
			tile[0] = new Tile(true);
			tile[0].image = ImageIO.read(new File("res/tiles/GRASS.png"));
			
			tile[1] = new Tile(false);
			tile[1].image = ImageIO.read(new File("res/tiles/BRICK.png"));
			
			tile[2] = new Tile(false);
			tile[2].image = ImageIO.read(new File("res/tiles/WATER.png"));
			
			tile[3] = new Tile(false);
			tile[3].image = ImageIO.read(new File("res/tiles/LAVA.png"));
			
			tile[4] = new Tile(true);
			tile[4].image = ImageIO.read(new File("res/tiles/SAND.png"));
			
			tile[5] = new Tile(true);
			tile[5].image = ImageIO.read(new File("res/tiles/SNOW.png"));
			
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
	
	public static Items giveMeFirstItem(ArrayList<Items> listeObjects, Entity me, double distance) {
		Items res = null;
		for (Items obj : listeObjects) {
			if ((res == null || me.distanceWidth(obj) < me.distanceWidth(res)) && me.distanceWidth(obj) < distance) {
				res = obj;
			}
		}
		return res;
	}
	
	public static Monsters giveMeFirstMonster(ArrayList<Monsters> listeMonster, Entity me, double distance) {
		Monsters res = null;
		for (Monsters obj : listeMonster) {
			if ((res == null || me.distanceWidth(obj) < me.distanceWidth(res)) && me.distanceWidth(obj) < distance) {
				res = obj;
			}
		}
		return res;
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
