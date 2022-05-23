package entityItem;

import main.GamePanel;

public abstract class Chest extends Items{
	public Items obj;
	public boolean lock;
		
	public Chest(GamePanel gp, String urlImage,Items item,boolean lock) {
		super(gp,urlImage);
		this.obj=item;
		this.lock=lock;
		}
	
	public Chest(GamePanel gp, String urlImage,Items item,boolean lock,int a,int b) {
		super(gp,urlImage,a,b);
		this.obj=item;
		this.lock=lock;
		}

}


