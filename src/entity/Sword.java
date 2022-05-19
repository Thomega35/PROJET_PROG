package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Sword extends objets{
	//Objet �p�e ajoutant 10 � l'attaque du joueur
		public Sword(GamePanel gp){
			super(gp);
			caract[0]=10;
		}
		
		// Fonction permettant d'obtenir l'image de la sword
		public void getObjetImage() {
			try {
	            idleImage = ImageIO.read(new File("res/objets/epee.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		// Fonction permettant l'int�raction avec l'�p�e et le joueur.
		// Si le joueur appuye pour r�cup�rer l'objet, elle arrive dans son inventaire et disparait de la carte
		public void interaction(Player pl) {
			pl.stuff.push_back(this);
			gp.listeObjects.remove(this);
			pl.attack=pl.attack+caract[0];
		}
}
