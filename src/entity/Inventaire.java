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
	public boolean aff;
	KeyHandler keyH;
	
	public Inventaire(GamePanel gp) {
		super(gp);
		aff=false;
		inventaire=new ArrayList<objets>();
		setDefaultValues();
	}
	
	void push_back(objets Obj) {
		Obj.x=this.x+62*inventaire.size();			// Quand on obtient un objet lui donne les coordonnees de l'inventaire pour ça place
		Obj.y=this.y;
		if (inventaire.size() >= 4) {
			Obj.y=this.y+gp.tileSize+10;
		}
		// Si la case précédente est occupée alors on le mets dans la case suivante
		inventaire.add(Obj);
	}
	
	public void setDefaultValues() {
		// Donne les coordonnées en bas au centre pour l'inventaire
		x=264;
		y=232;
	}	
	
	public void draw(Graphics2D g2) {
		// affiche l'inventaire avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
		if (aff) {
			for(int i=0;i<2;i++) {
				for(int j=0;j<4;j++) {
					g2.setColor(Color.GRAY);
					g2.fill3DRect(x+j*62, y+i*58, gp.tileSize, gp.tileSize,true);
				};
			};
			for(int i=0;i<inventaire.size();i++) {
				inventaire.get(i).draw(g2);
			};
		}
	}
	
	
	
	
}
