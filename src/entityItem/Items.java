package entityItem;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Inventaire;
import entity.Player;
import main.GamePanel;

public abstract class Items extends Entity{
	//Variable de la fonction
	public boolean aff;
	public int[] caract;
	public String urlImage;
	public Boolean dying;
	
	//Constructeur objets contenant les caract�ristiques que rajoute l'objets
	public Items(GamePanel gp, String urlImage) {
		super(gp);
		this.urlImage=urlImage;
		aff=true;
		setDefaultValues();
		getObjetImage();
		caract=new int[4];
		dying = false;
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
	
	public void update() {}
	
	public void draw(Graphics2D g2) {
		// r�cup�re l'image de l'objet
		BufferedImage image = idleImage;
		// affiche l'objet avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

	
	
	
}
