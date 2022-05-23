package entityItem;

import entity.Player;
import main.GamePanel;

public class ChestUnlock extends Chest{
	Key key;
	public Items obj;
	boolean status;
	
	public ChestUnlock(GamePanel gp,Items item) {
		super(gp,"/res/objets/chest1.png",item,true);
	}
	
	public void interaction(Player pl) {
		if(lock) {
			urlImage = "/res/objets/chestunlock.png";
			this.getObjetImage();
			obj.interaction(pl);;
		}
	}
	

}