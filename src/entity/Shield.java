package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Shield extends objets{
	public Shield(GamePanel gp) {
		super(gp,"res/objets/shield.png");
		caract[1]=10;
	}
	
	// Fonction permettant l'intéraction avec l'épée et le joueur.
	// Si le joueur appuye pour récupérer l'objet, elle arrive dans son inventaire et disparait de la carte
	public void interaction(Player pl) {
		pl.stuff.push_back(this);
		gp.listeObjects.remove(this);
		pl.defence=pl.defence+caract[1];
	}
}
