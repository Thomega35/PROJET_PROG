package entityItem;
import entity.Player;
import main.GamePanel;

public class Sword extends Items{
	//Objet �p�e ajoutant 10 � l'attaque du joueur
		public Sword(GamePanel gp){
			super(gp,"res/objets/epeecaillou.png");
			caract[0]=10;
		}

		// Fonction permettant l'int�raction avec l'�p�e et le joueur.
		// Si le joueur appuye pour r�cup�rer l'objet, elle arrive dans son inventaire et disparait de la carte
		public void interaction(Player pl) {
			urlImage = "res/objets/epee.png";
			this.getObjetImage();
			pl.stuff.push_back(this);
			gp.listeObjects.remove(this);
			pl.attackPoint=pl.attackPoint+caract[0];
			for (int i =0;i < gp.itemMaps[gp.tileM.numMap].length; i++) {
				if (gp.itemMaps[gp.tileM.numMap][i] == this) {
					gp.itemMaps[gp.tileM.numMap][i]=null;
				}
			}
			gp.listeObjects.clear();
		}
}
