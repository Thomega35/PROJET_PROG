package entity;

import java.awt.Graphics2D;

import main.GamePanel;


public abstract class Monsters extends Entity {
    Player player;
    public Boolean dying;
    public Monsters(GamePanel gp) {
    	super(gp);
    	dying = false;
    }
	public abstract void draw(Graphics2D g2);
    
	public abstract void update();
}
