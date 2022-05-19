package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	KeyHandler keyH;
	ArrayList<BufferedImage> idle;
	ArrayList<BufferedImage> moving;
	ArrayList<BufferedImage> hiting;
	ArrayList<BufferedImage> winning;
	
	int timetodisplay;
	public int hp;
	int attack;
	int defence;
	public Inventaire stuff;
	Boolean ismoving;
	int display6fightFrame;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImages();
		this.hp = 11;
		this.attack = 1;
		this.defence = 1;
		this.display6fightFrame = 200;
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par défaut
		x = 100;
		y = 100;
		speed = 4;
		timetodisplay = 0;
		idle = new ArrayList<BufferedImage>();
		moving = new ArrayList<BufferedImage>();
		hiting = new ArrayList<BufferedImage>();
		winning = new ArrayList<BufferedImage>();
		ismoving = false;
		stuff = new Inventaire(gp);
	}
	
	public void getPlayerImages() {
		try {
			idleImage = ImageIO.read(new File("res/player/SteamMan1.png"));
			for (int i=1; i<=6;i++) {
				moving.add(ImageIO.read(new File("res/player/SteamManRun"+i+".png")));
				hiting.add(ImageIO.read(new File("res/player/SteamManHit"+i+".png")));
				
				if (i<=4) {idle.add(ImageIO.read(new File("res/player/SteamMan"+i+".png")));}
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Change le numÃ©ro de la map
	public void changeMap() {
		if(gp.tileM.numMap == 1) {
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 2;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 2) {
			if(x < gp.tileSize && x > 0) {
				gp.tileM.numMap = 1;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 2) {
			if(y < gp.tileSize  && y > 0) {
				gp.tileM.numMap = 3;
				y = gp.screenHeight-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 3) {
			if(y+10 < gp.screenHeight && y+10 > gp.screenHeight-gp.tileSize) {
				gp.tileM.numMap = 2;
				y = gp.tileSize;
			}
		}
	}

	public void update() {
		changeMap();
		move();
		hit();
		inventory();
		pick();
		
	}
	private void pick() {
		if (keyH.wantToPick) {
			objets obj = giveMeFirst(gp.listeObjects);
			if (obj != null) {
				obj.interaction(this);
			}
			keyH.wantToPick = false;
		}
	}
	
	private objets giveMeFirst(ArrayList<objets> listeObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	private void inventory() {
		if (keyH.openInventory) {
			stuff.aff = !stuff.aff;
			keyH.openInventory = false;
		}
	}

	private void hit() {
		if (keyH.wantToHit && display6fightFrame >= 30) {
			display6fightFrame = 0;
			keyH.wantToHit = false;
		}
	}

	private void move() {
		int x_temp = x;
		int y_temp = y;
		ismoving = true;
		
		//MOVE one direction
		if (keyH.DKey && !keyH.QKey && !keyH.ZKey && !keyH.SKey) {
			x_temp += speed;
		} else if (!keyH.DKey && keyH.QKey && !keyH.ZKey && !keyH.SKey) {
			x_temp -= speed;
		} else if (!keyH.DKey && !keyH.QKey && keyH.ZKey && !keyH.SKey) {
			y_temp -= speed;
		} else if (!keyH.DKey && !keyH.QKey && !keyH.ZKey && keyH.SKey) {
			y_temp += speed;
		}
		//MOVE diagonale
		else if (keyH.DKey && !keyH.QKey && !keyH.ZKey && keyH.SKey) {
			y_temp += speed / 2;
			x_temp += speed / 2;
		} else if (keyH.DKey && !keyH.QKey && keyH.ZKey && !keyH.SKey) {
			y_temp -= speed / 2;
			x_temp += speed / 2;
		} else if (!keyH.DKey && keyH.QKey && !keyH.ZKey && keyH.SKey) {
			y_temp += speed / 2;
			x_temp -= speed / 2;
		} else if (!keyH.DKey && keyH.QKey && keyH.ZKey && !keyH.SKey) {
			y_temp -= speed / 2;
			x_temp -= speed / 2;
		}else {
			ismoving = false;
		}
		whereIgo(x_temp,y_temp);
	}

	public void draw(Graphics2D g2) {
		// récupére l'image du joueur
		BufferedImage image;
		if (display6fightFrame < 30) {
			image = hiting.get((timetodisplay/5)%6);
			display6fightFrame++;
		}else if (!ismoving) {
			image = idle.get((timetodisplay/10)%4);
		}else {
			image = moving.get((timetodisplay/10)%6);
		}
		
		if (keyH.lookLeft) {
			image = flip(image);
		}
		
		// affiche le personnage avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		timetodisplay++;
	}
	
	private BufferedImage flip(BufferedImage img) {
		BufferedImage res = new BufferedImage(200, 200,
		        BufferedImage.TYPE_BYTE_INDEXED);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getHeight(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		res = op.filter(img, null);
		return res;
	}
}