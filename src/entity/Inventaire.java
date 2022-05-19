package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
 
public class Inventaire extends Entity{
	public ArrayList<objets> inventaire;
	GamePanel gp;
	public boolean aff;
	KeyHandler keyH;
	
	public Inventaire(GamePanel gp) {
		this.gp = gp;
		aff=false;
		inventaire=new ArrayList<objets>();
		setDefaultValues();
		getObjetImage();
	}
	
	void push_back(objets Obj) {
		Obj.x=Obj.x+69*inventaire.size();			// Quand on obtient un objet lui donne les coordonnees de l'inventaire pour �a place
		Obj.y=this.y;								// Si la case pr�c�dente est occup�e alors on le mets dans la case suivante
		inventaire.add(Obj);
	}
	
	public void setDefaultValues() {
		// Donne les coordonn�es en bas au centre pour l'inventaire
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
		// r�cup�re l'image de l'inventaire
		BufferedImage image = idleImage;
		// affiche l'inventaire avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.setColor(Color.BLACK);
		g2.fill3DRect(x, y, 64, 64,true);
		for(int i=0;i<inventaire.size();i++) {
			inventaire.get(i).draw(g2);
		};
	}
	
	
	
	
}
