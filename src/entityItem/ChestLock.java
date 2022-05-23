package entityItem;
import main.GamePanel;
import entity.Player;
public class ChestLock extends Chest{
	Key key;
	Items obj;
	boolean status;
	
	public ChestLock(GamePanel gp,Key key,Items item) {
		super(gp,"res/objets/chestlock.png",item,true);
		this.key=key;
	}
	
	public ChestLock(GamePanel gp,Key key,Items item,int a,int b) {
		super(gp,"res/objets/chestlock.png",item,true,a,b);
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
