package badGuys;

import java.awt.Graphics2D;
import java.util.Random;

import entity.Entity;
import main.GamePanel;


public abstract class Monsters extends Entity {
    public Boolean dying;
    public boolean dead;
    Boolean sens;
    public Monsters(GamePanel gp) {
    	super(gp);
    	dying = false;
    	setDefaultValuesAlea();
    	sens = gp.player.x < gp.player.x;
    }
	public abstract void draw(Graphics2D g2);
    
	public abstract void update();
	
	public abstract void getMonsterImage();
	
	public void setDefaultValuesAlea() {
        // monsters pop position may to be random and far of the player
        x = positionMonster(gp.player.x, gp.screenWidth);
        y = positionMonster(gp.player.y, gp.screenHeight);
    }
	
	public int positionMonster(int positionPlayer, int max){
        int r=new Random().nextInt(max-positionPlayer-5) + positionPlayer+5;
        return r;
    }
	
	public void setDefaultValues(int x, int y) {
        // monsters pop position may to be random and far of the player
        this.x = x;
        this.y = y;
    }

	public abstract void hurt();
}
