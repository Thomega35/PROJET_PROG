package entityItem;

import entity.Player;
import main.GamePanel;

public class Boots extends Items{
	public Boots(GamePanel gp) {
		super(gp,"res/objets/boots.png");
		caract[2]=4;
	}
	
	// Fonction permettant l'int�raction avec l'�p�e et le joueur.
	// Si le joueur appuye pour r�cup�rer l'objet, elle arrive dans son inventaire et disparait de la carte
	public void interaction(Player pl) {
		pl.stuff.push_back(this);
		gp.listeObjects.remove(this);
		pl.speed=pl.speed+caract[2];
	}
}


