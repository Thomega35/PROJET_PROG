package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import entity.Inventaire;
import main.GamePanel;

public class objets extends Entity{

	public int life;
	public boolean aff;
	public int[] caract;
	
	public objets(GamePanel gp) {
		super(gp);
		aff=true;
		setDefaultValues();
		getObjetImage();
		caract=new int[3];
	}

	public void setDefaultValues(int a,int b) {
		x=a;
		y=b;
		speed=0;
	}
	
	public void setDefaultValues() {
		setDefaultValues(120,120);
	}
	
	public void interaction() {}
	

	public void getObjetImage() {}
	
	
	public void draw(Graphics2D g2) {
		// récupère l'image de l'objet
		BufferedImage image = idleImage;
		// affiche l'objet avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

	
	
	
}
