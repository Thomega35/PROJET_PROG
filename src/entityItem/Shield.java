package entityItem;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class Shield extends Items{
	public Shield(GamePanel gp) {
		super(gp,"res/objets/shield.png");
		caract[1]=10;
	}
	
	// Fonction permettant l'int�raction avec l'�p�e et le joueur.
	// Si le joueur appuye pour r�cup�rer l'objet, elle arrive dans son inventaire et disparait de la carte
	public void interaction(Player pl) {
		pl.stuff.push_back(this);
		gp.listeObjects.remove(this);
		pl.defence=pl.defence+caract[1];
		for (int i =0;i < gp.itemMaps[gp.tileM.numMap].length; i++) {
			if (gp.itemMaps[gp.tileM.numMap][i].getClass() == this.getClass()) {
				gp.itemMaps[gp.tileM.numMap][i]=null;
			}
		}
		gp.listeObjects.clear();
	}
}
