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

import entityItem.Items;
import entityItem.Projectile;
import main.Functions;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{

	public KeyHandler keyH;
	ArrayList<BufferedImage> idle;
	ArrayList<BufferedImage> moving;
	ArrayList<BufferedImage> hiting;
	ArrayList<BufferedImage> hitingProjectile;
	ArrayList<BufferedImage> jumping;
	ArrayList<BufferedImage> hurting;
	
	int timetodisplay;
	public int defence;
	public double grabDistance;
	public Inventaire stuff;
	Boolean ismoving;
	public Boolean win;
	int display6fightFrame;
	int display6fightFrameFar;
	int display6fHurtFrame;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImages();
		this.lifePoint = 11;
		this.attackPoint = 5;
		this.defence = 1;
		this.grabDistance=30;
		this.display6fightFrame = 30;
		this.display6fightFrameFar = 30;
		this.display6fHurtFrame = 10;
		this.win = false;
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par d�faut
		x = 100;
		y = 100;
		speed = 4;
		timetodisplay = 0;
		idle = new ArrayList<BufferedImage>();
		moving = new ArrayList<BufferedImage>();
		hiting = new ArrayList<BufferedImage>();
		hitingProjectile = new ArrayList<BufferedImage>();
		jumping = new ArrayList<BufferedImage>();
		hurting = new ArrayList<BufferedImage>();
		ismoving = false;
		stuff = new Inventaire(gp);
	}
	
	public void getPlayerImages() {
		try {
			idleImage = ImageIO.read(new File("res/player/SteamMan1.png"));
			for (int i=1; i<=6;i++) {
				moving.add(ImageIO.read(new File("res/player/SteamManRun"+i+".png")));
				hiting.add(ImageIO.read(new File("res/player/SteamManHit"+i+".png")));
				jumping.add(ImageIO.read(new File("res/player/SteamManHit_Jump"+i+".png")));
				hitingProjectile.add(ImageIO.read(new File("res/player/SteamManHitNum2_"+i+".png")));
				if (i<=4) {idle.add(ImageIO.read(new File("res/player/SteamMan"+i+".png")));}
				if (i<=3) {hurting.add(ImageIO.read(new File("res/player/SteamManHitHurt_"+i+".png")));}
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Change le numéro de la map
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
		hit_far();
		inventory();
		pick();
		//hurt(); set display6fHurtFrame to ten is need to hurt display
	}

	private void pick() {
		if (keyH.wantToPick) {
			Items obj = Functions.giveMeFirstItem(gp.listeObjects,this, grabDistance);
			if (obj != null) {
				obj.interaction(this);
			}
			keyH.wantToPick = false;
		}
	}

	private void inventory() {
		if (keyH.openInventory) {
			stuff.aff = !stuff.aff;
			keyH.openInventory = false;
		}
	}

	private void hit() {
		if (keyH.wantToHit && display6fightFrame >= 30) {
			//TODO frapper le monstre
			display6fightFrame = 0;
			keyH.wantToHit = false;
		}
	}
	
	public void hurt() {
			display6fHurtFrame = 0;
	}
	
	public void hit_far() {
		if (keyH.wantToHitFar && display6fightFrameFar >= 30) {
			//TODO frapper le monstre
			Projectile p = new Projectile(gp, this);
			gp.listeObjects.add(p);
			p.setDefaultValues(x+gp.tileSize/2,y+gp.tileSize/2);
			display6fightFrameFar = 0;
			keyH.wantToHitFar = false;
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
		// r�cup�re l'image du joueur
		BufferedImage image;
		if(win) {
			image = jumping.get((timetodisplay/15)%6);
		}else if (display6fHurtFrame <= 10) {
			image = hurting.get((timetodisplay/5)%3);
			display6fHurtFrame++;
		}else if (display6fightFrame < 30) {
			image = hiting.get((timetodisplay/5)%6);
			display6fightFrame++;
		}else if (display6fightFrameFar < 30) {
			image = hitingProjectile.get(((timetodisplay+20)/5)%6);
			display6fightFrameFar++;
		}else if (!ismoving) {
			image = idle.get((timetodisplay/10)%4);
		}else {
			image = moving.get((timetodisplay/10)%6);
		}
		
		if (keyH.lookLeft) {
			image = flip(image);
		}
		
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		stuff.draw(g2);
		timetodisplay++;
	}
	
	public BufferedImage flip(BufferedImage img) {
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