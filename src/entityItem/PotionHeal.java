package entityItem;
import static java.lang.Math.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class PotionHeal extends Items{
	public PotionHeal(GamePanel gp) {
		super(gp,"res/screen/hearth.png");
		caract[3]=5;
	}
	
	public PotionHeal(GamePanel gp,int a, int b) {
		super(gp,"res/screen/hearth.png",a,b);
		caract[3]=5;
	}
	
	// Fonction permettant l'int�raction avec l'�p�e et le joueur.
		// Si le joueur appuye pour r�cup�rer l'objet, elle arrive dans son inventaire et disparait de la carte
	public void interaction(Player pl) {
		pl.stuff.push_back(this);
		gp.listeObjects.remove(this);
		pl.lifePoint=pl.lifePoint+caract[3];
		for (int i =0;i < gp.itemMaps[gp.tileM.numMap].length; i++) {
			if (gp.itemMaps[gp.tileM.numMap][i] != null && gp.itemMaps[gp.tileM.numMap][i].getClass() == this.getClass()) {
				gp.itemMaps[gp.tileM.numMap][i] = null;
			}
		}
		gp.listeObjects.clear();
			
	}

}
