package entityItem;
import main.GamePanel;
import java.awt.*;
import entityItem.Key;
import entity.Player;
import entityItem.Key;
import java.util.ArrayList;
import java.util.List;

public class ChestLock extends Chest{
	Key key;
	Items obj;
	boolean status;
	
	public ChestLock(GamePanel gp,Key key,Items item) {
		super(gp,"res/objets/chestlock.png",item,true);
		this.key=key;
	}
	
	public void interaction(Player pl) {
		if(pl.stuff.inventaire.contains(key) && lock) {
			urlImage = "res/objets/chestunlock.png";
			this.getObjetImage();
			pl.stuff.push_back(obj);
		}
	}
	

}