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

	GamePanel gp;
	KeyHandler keyH;
	ArrayList<BufferedImage> idle;
	ArrayList<BufferedImage> moving;
	int timetodisplay;
	Boolean ismoving;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImages();
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par d�faut
		x = 100;
		y = 100;
		speed = 4;
		timetodisplay = 0;
		idle = new ArrayList<BufferedImage>();
		moving = new ArrayList<BufferedImage>();
		ismoving = false;
	}
	
	public void getPlayerImages() {
		try {
			idleImage = ImageIO.read(new File("res/player/SteamMan.png"));
			idle.add(ImageIO.read(new File("res/player/SteamMan.png")));
			idle.add(ImageIO.read(new File("res/player/SteamMan1.png")));
			idle.add(ImageIO.read(new File("res/player/SteamMan2.png")));
			idle.add(ImageIO.read(new File("res/player/SteamMan3.png")));
			
			moving.add(ImageIO.read(new File("res/player/SteamManRun1.png")));
			moving.add(ImageIO.read(new File("res/player/SteamManRun2.png")));
			moving.add(ImageIO.read(new File("res/player/SteamManRun3.png")));
			moving.add(ImageIO.read(new File("res/player/SteamManRun4.png")));
			moving.add(ImageIO.read(new File("res/player/SteamManRun5.png")));
			moving.add(ImageIO.read(new File("res/player/SteamManRun6.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		gp.tileM.changeMap(x, y);
		move();
		
		
	}
	
	public void move() {
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
		if(gp.tileM.canMove(x_temp+gp.tileSize, y_temp+ gp.tileSize) && gp.tileM.canMove(x_temp, y_temp) && gp.tileM.canMove(x_temp, y_temp+ gp.tileSize) && gp.tileM.canMove(x_temp+gp.tileSize, y_temp)) {
			x = x_temp;
			y = y_temp;
		}
	}

	public void draw(Graphics2D g2) {
		// r�cup�re l'image du joueur
		BufferedImage image;
		if (!ismoving) {
			image = idle.get((timetodisplay/15)%4);
		}else {
			image = moving.get((timetodisplay/15)%6);
			image = flip(image);
		}
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		timetodisplay++;
	}
	
	private BufferedImage flip(BufferedImage img) {
		BufferedImage res = new BufferedImage(
	            img.getWidth(), img.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		res = op.filter(img, null);
		return res;
	}
	
	
}
