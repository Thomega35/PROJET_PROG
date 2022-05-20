package entityItem;


import main.GamePanel;

public abstract class Key extends Items{
	public int color;
	
	public Key(GamePanel gp, String urlImage,int color) {
		super(gp,urlImage);
		this.color=color;
	}
	
	

	
	
}
