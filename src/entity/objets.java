package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import entity.Inventaire;
import main.GamePanel;

public abstract class objets extends Entity{
	//Variable de la fonction
	public boolean aff;
	public int[] caract;
	public String urlImage;
	
	//Constructeur objets contenant les caractéristiques que rajoute l'objets
	public objets(GamePanel gp, String urlImage) {
		super(gp);
		this.urlImage=urlImage;
		aff=true;
		setDefaultValues();
		getObjetImage();
		caract=new int[4];
	}

	public void setDefaultValues(int a,int b) {
		x=a;
		y=b;
		speed=0;
	}
	
	public void setDefaultValues() {
		setDefaultValues(120,120);
	}
	
	public abstract void interaction(Player pl);
	

	public void getObjetImage() {
		try {
            idleImage = ImageIO.read(new File(urlImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public void draw(Graphics2D g2) {
		// récupère l'image de l'objet
		BufferedImage image = idleImage;
		// affiche l'objet avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

	
	
	
}
