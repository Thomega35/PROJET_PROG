package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import entityItem.Items;
import main.GamePanel;
import main.KeyHandler;
 
public class Inventaire extends Entity{
	public ArrayList<Items> inventaire;
	public boolean aff;
	KeyHandler keyH;
	
	public Inventaire(GamePanel gp) {
		super(gp);
		aff=false;
		inventaire=new ArrayList<Items>();
		setDefaultValues();
	}
	
	public void push_back(Items Obj) {
		Obj.x=this.x+62*inventaire.size();			// Quand on obtient un objet lui donne les coordonnees de l'inventaire pour �a place
		Obj.y=this.y;
		if (inventaire.size() >= 4) {
			Obj.y=this.y+gp.tileSize+10;
			Obj.x=this.x+62*(inventaire.size()-4);
		}
		// Si la case pr�c�dente est occup�e alors on le mets dans la case suivante
		inventaire.add(Obj);
	}
	
	public void setDefaultValues() {
		// Donne les coordonn�es en bas au centre pour l'inventaire
		x=264;
		y=232;
	}	
	
	public void draw(Graphics2D g2) {
		// affiche l'inventaire avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		if (aff && !gp.success.end && !gp.gameOver.end) {
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
