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

import badGuys.HUGEMOMMYISGONNAEATYOU;
import badGuys.SimpleMonster;
import badGuys.ShootingMonster;
import badGuys.Monsters;
import entityItem.ChestUnlock;
import entityItem.Items;
import entityItem.PotionHeal;
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
	ArrayList<BufferedImage> Dyiing;
	
	int timetodisplay;
	public int defence;
	public double grabDistance;
	public Inventaire stuff;
	Boolean ismoving;
	public Boolean win;
	int display6fightFrame;
	int display6fightFrameFar;
	int display6fHurtFrame;
	public boolean dead;

	int numOldMap;
	int numNewMap;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImages();
		this.lifePoint = 20;
		this.attackPoint = 3;
		this.defence = 1;
		this.grabDistance=30;
		this.display6fightFrame = 30;
		this.display6fightFrameFar = 30;
		this.display6fHurtFrame = 10;
		this.win = false;
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par d�faut
		x = 290;
		y = 270;
		speed = 4;
		timetodisplay = 0;
		idle = new ArrayList<BufferedImage>();
		moving = new ArrayList<BufferedImage>();
		hiting = new ArrayList<BufferedImage>();
		hitingProjectile = new ArrayList<BufferedImage>();
		jumping = new ArrayList<BufferedImage>();
		hurting = new ArrayList<BufferedImage>();
		Dyiing = new ArrayList<BufferedImage>();
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
				Dyiing.add(ImageIO.read(new File("res/player/SteamMan_Dyiing"+i+".png")));
				if (i<=4) {idle.add(ImageIO.read(new File("res/player/SteamMan"+i+".png")));}
				if (i<=3) {hurting.add(ImageIO.read(new File("res/player/SteamManHitHurt_"+i+".png")));}
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void loadMonsters(int num){
		if(num==2){
			gp.listeMonsters.clear();
			ShootingMonster shootingMonster1=new ShootingMonster(gp);
			gp.listeMonsters.add(shootingMonster1);
			gp.listeMonsters.get(0).setDefaultValues(400,400);
		} else if (num == 3) {
			gp.listeMonsters.clear();
			ShootingMonster shootingMonster1 = new ShootingMonster(gp);
			ShootingMonster shootingMonster2 = new ShootingMonster(gp);
			gp.listeMonsters.add(shootingMonster1);
			gp.listeMonsters.add(shootingMonster2);
			gp.listeMonsters.get(0).setDefaultValues(200, 100);
			gp.listeMonsters.get(1).setDefaultValues(400, 300);

		} else if (num == 4) {
			gp.listeMonsters.clear();
			ShootingMonster shootingMonster1 = new ShootingMonster(gp);
			ShootingMonster shootingMonster2 = new ShootingMonster(gp);
			ShootingMonster shootingMonster3 = new ShootingMonster(gp);
			//ShootingMonster shootingMonster4 = new ShootingMonster(gp);
			gp.listeMonsters.add(shootingMonster1);
			gp.listeMonsters.add(shootingMonster2);
			gp.listeMonsters.add(shootingMonster3);
			//gp.listeMonsters.add(shootingMonster4);
			gp.listeMonsters.get(0).setDefaultValues(300, 100);
			gp.listeMonsters.get(1).setDefaultValues(200, 100);
			gp.listeMonsters.get(2).setDefaultValues(150, 400);
			//gp.listeMonsters.get(3).setDefaultValues(300, 500);

		} else if (num == 5) {
			gp.listeMonsters.clear();
			ShootingMonster shootingMonster1 = new ShootingMonster(gp);
			ShootingMonster shootingMonster2 = new ShootingMonster(gp);
			gp.listeMonsters.add(shootingMonster1);
			gp.listeMonsters.add(shootingMonster2);
			gp.listeMonsters.get(0).setDefaultValues(90, 80);
			gp.listeMonsters.get(1).setDefaultValues(60, 450);

		} else if (num == 6) {
			gp.listeMonsters.clear();
			SimpleMonster simpleMonster1=new SimpleMonster(gp);
			gp.listeMonsters.add(simpleMonster1);
			gp.listeMonsters.get(0).setDefaultValues(60,200);

		} else if (num == 7) {
			// épée
			gp.listeMonsters.clear();

		} else if (num == 8) {
			gp.listeMonsters.clear();
			SimpleMonster simpleMonster1=new SimpleMonster(gp);
			SimpleMonster simpleMonster2=new SimpleMonster(gp);
			gp.listeMonsters.add(simpleMonster1);
			gp.listeMonsters.add(simpleMonster2);
			gp.listeMonsters.get(0).setDefaultValues(60,70);
			gp.listeMonsters.get(1).setDefaultValues(500,350);

		} else if (num == 9) {
			gp.listeMonsters.clear();

		} else if (num == 10) {
			gp.listeMonsters.clear();
			SimpleMonster simpleMonster1=new SimpleMonster(gp);
			SimpleMonster simpleMonster2=new SimpleMonster(gp);
			SimpleMonster simpleMonster3=new SimpleMonster(gp);
			ShootingMonster simpleMonster4=new ShootingMonster(gp);
			gp.listeMonsters.add(simpleMonster1);
			gp.listeMonsters.add(simpleMonster2);
			gp.listeMonsters.add(simpleMonster3);
			gp.listeMonsters.add(simpleMonster4);
			gp.listeMonsters.get(0).setDefaultValues(100,100);
			gp.listeMonsters.get(1).setDefaultValues(100,250);
			gp.listeMonsters.get(2).setDefaultValues(100,400);
			gp.listeMonsters.get(3).setDefaultValues(500,250);

		}else if (num == 11) {
			// Chest
			gp.listeMonsters.clear();
		}else if (num == 12) {
			gp.listeMonsters.clear();
			ShootingMonster simpleMonster1=new ShootingMonster(gp);
			ShootingMonster simpleMonster2=new ShootingMonster(gp);
			gp.listeMonsters.add(simpleMonster1);
			gp.listeMonsters.add(simpleMonster2);
			gp.listeMonsters.get(0).setDefaultValues(400,100);
			gp.listeMonsters.get(1).setDefaultValues(400,400);
			HUGEMOMMYISGONNAEATYOU hugeMommy = new HUGEMOMMYISGONNAEATYOU(gp);
			gp.listeMonsters.add(hugeMommy);
			gp.listeMonsters.get(2).setDefaultValues(180,180);

		}
	}
	

	// Change le numéro de la map
	public void changeMap() {
		numOldMap=numNewMap;
		
		gp.listeObjects.clear();
		System.out.println();
		for (int i=0; i < gp.itemMaps[gp.tileM.numMap].length;i++){
			if (gp.itemMaps[gp.tileM.numMap][i] != null)
				gp.listeObjects.add(gp.itemMaps[gp.tileM.numMap][i]);
		}
		
		
		if(gp.tileM.numMap == 1) {
			numNewMap=1;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 2;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 2) {
			numNewMap=2;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 1;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 2) {
			numNewMap=2;
			if(y < gp.tileSize  && y > 0) {
				gp.tileM.numMap = 3;
				y = gp.screenHeight-2*gp.tileSize;

			}
		}
		if(gp.tileM.numMap == 3) {
			numNewMap=3;
			if(y+10 < gp.screenHeight && y+10 > gp.screenHeight-gp.tileSize) {
				gp.tileM.numMap = 2;
				y = gp.tileSize;

			}

		}
		if(gp.tileM.numMap == 2) {
			numNewMap=2;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 4;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 4) {
			numNewMap=4;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 2;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 4) {
			numNewMap=4;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 5;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 5) {
			numNewMap=5;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 4;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 5) {
			numNewMap=5;
			if(y < 10  && y > -gp.tileSize + 10) {
				gp.tileM.numMap = 6;
				y = gp.screenHeight-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 6) {
			numNewMap=6;
			if (y + 10 < gp.screenHeight && y + 10 > gp.screenHeight - gp.tileSize) {
				gp.tileM.numMap = 5;
				y = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 6) {
			numNewMap=6;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 7;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 7) {
			numNewMap=7;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 6;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 6) {
			numNewMap=6;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 8;
				x = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 8) {
			numNewMap=8;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 6;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 8) {
			numNewMap=8;
			if(y < 10  && y > -gp.tileSize + 10) {
				gp.tileM.numMap = 9;
				y = gp.screenHeight-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 9) {
			numNewMap=9;
			if (y + 10 < gp.screenHeight && y + 10 > gp.screenHeight - gp.tileSize) {
				gp.tileM.numMap = 8;
				y = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 10) {
			numNewMap=10;
			if(y < 10  && y > -gp.tileSize + 10) {
				gp.tileM.numMap = 8;
				y = gp.screenHeight-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 8) {
			numNewMap=8;
			if (y + 10 < gp.screenHeight && y + 10 > gp.screenHeight - gp.tileSize) {
				gp.tileM.numMap = 10;
				y = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 11) {
			numNewMap=11;
			if(y < 10  && y > -gp.tileSize + 10) {
				gp.tileM.numMap = 10;
				y = gp.screenHeight-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 10) {
			numNewMap=10;
			if (y + 10 < gp.screenHeight && y + 10 > gp.screenHeight - gp.tileSize) {
				gp.tileM.numMap = 11;
				y = gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 10) {
			numNewMap=10;
			if(x < 10  && x > -gp.tileSize + 10) {
				gp.tileM.numMap = 12;
				x = gp.screenWidth-2*gp.tileSize;
			}
		}
		if(gp.tileM.numMap == 12) {
			numNewMap=12;
			if(x+10 < gp.screenWidth && x+10 > gp.screenWidth-gp.tileSize) {
				gp.tileM.numMap = 10;
				x = gp.tileSize;
			}
		}
	}

	public void update() {
		if(!dead) {
			changeMap();
			if(numOldMap!=numNewMap){
				loadMonsters(numNewMap);
			}
			move();
			hit();
			hit_far();
			inventory();
			pick();
			dead();
		}
		//hurt(); set display6fHurtFrame to ten is need to hurt display
	}

	private void dead() {
		// TODO Auto-generated method stub
		if (lifePoint <= 0) {
			dead = true;
			timetodisplay = 0;
		}
		
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
			Monsters cible = Functions.giveMeFirstMonster(gp.listeMonsters,this,55);
			if (cible != null) {
				cible.lifePoint -= attackPoint;
	    		cible.hurt();
			}
			display6fightFrame = 0;
			keyH.wantToHit = false;
		}
	}
	
	public void hurt() {
			display6fHurtFrame = 0;
	}
	
	public void hit_far() {
		if (keyH.wantToHitFar && display6fightFrameFar >= 30) {
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
		}else if (dead) {
			if (timetodisplay < 200) {
				image = Dyiing.get((timetodisplay/35)%6);
			}else {
				image = Dyiing.get(5);
				gp.gameOver.end = true;
			}
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
		if (!dead) {
			if (keyH.lookLeft) {
				image = flip(image);
			}
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