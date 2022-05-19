package entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
 
public class Inventaire extends Entity{
	public ArrayList<objets> inventaire;
	GamePanel gp;
	
	
	public Inventaire(GamePanel gp) {
		this.gp = gp;
		inventaire=new ArrayList<objets>();
		setDefaultValues();
		getObjetImage();
	}
	
	void push_back(objets Obj) {
		inventaire.add(Obj);
	}
	
	public void setDefaultValues() {
		// Donne les coordonnées en bas au centre pour l'inventaire
		x=31;
		y=339;
	}
	
	public void getObjetImage() {
		try {
			idleImage = ImageIO.read(new File("res/objets/inventaire.png"));
			}
		catch (IOException e) {
			e.printStackTrace();
			}
		}
	
	public void draw(Graphics2D g2) {
		// récupère l'image de l'inventaire
		BufferedImage image = idleImage;
		// affiche l'inventaire avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		for(int i=0;i<inventaire.size();i++) {
			inventaire.get(i).draw(g2);
		};
	}
	
	
	
	
}
