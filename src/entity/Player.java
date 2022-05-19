package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par défaut
		x = 100;
		y = 100;
		speed = 4;
	}
	
	public void getPlayerImage() {
		try {
			
			idleImage = ImageIO.read(new File("res/player/superhero.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		move();
		
		
	}
	
	public void move() {
		//MOVE one direction
		     if(keyH.DKey && !keyH.QKey && !keyH.ZKey && !keyH.SKey) {x+=speed;}
		else if(!keyH.DKey && keyH.QKey && !keyH.ZKey && !keyH.SKey) {x-=speed;}
		else if(!keyH.DKey && !keyH.QKey && keyH.ZKey && !keyH.SKey) {y-=speed;}
		else if(!keyH.DKey && !keyH.QKey && !keyH.ZKey && keyH.SKey) {y+=speed;}
		//MOVE diagonale
		else if(keyH.DKey && !keyH.QKey && !keyH.ZKey && keyH.SKey) {y+=speed/2;x+=speed/2;}
		else if(keyH.DKey && !keyH.QKey && keyH.ZKey && !keyH.SKey) {y-=speed/2;x+=speed/2;}
		else if(!keyH.DKey && keyH.QKey && !keyH.ZKey && keyH.SKey) {y+=speed/2;x-=speed/2;}
		else if(!keyH.DKey && keyH.QKey && keyH.ZKey && !keyH.SKey) {y-=speed/2;x-=speed/2;}
		     
	}
	
	public void draw(Graphics2D g2) {
		// récupère l'image du joueur
		BufferedImage image = idleImage;
		// affiche le personnage avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	
}
