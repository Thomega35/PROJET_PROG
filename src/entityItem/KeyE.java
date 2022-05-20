package entityItem;

import entity.Player;
import main.GamePanel;

public class KeyE extends Key{
	
	public Player pl;
	
	public KeyE(GamePanel gp, String urlImage,int color) {
		super(gp,urlImage,color);
		}
	
	public void interaction(Player pl) {
		pl.stuff.push_back(this);
		gp.listeObjects.remove(this);
		for (int i =0;i < gp.itemMaps[gp.tileM.numMap].length; i++) {
			if (gp.itemMaps[gp.tileM.numMap][i] == this) {
				gp.itemMaps[gp.tileM.numMap][i]=null;
			}
		}
	}
	
	
	

}
