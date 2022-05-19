package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;


public class Entity {
	public int x,y;
	public int speed;
	public BufferedImage idleImage;
	public GamePanel gp;

	// Entities attributs
	public Integer lifePoint;
	public Integer attackPoint;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void whereIgo(int x_temp, int y_temp){
		if(gp.tileM.canMove(x_temp+gp.tileSize, y_temp+ gp.tileSize) && gp.tileM.canMove(x_temp, y_temp) && gp.tileM.canMove(x_temp, y_temp+ gp.tileSize) && gp.tileM.canMove(x_temp+gp.tileSize, y_temp)) {
			x = x_temp;
			y = y_temp;
		}
		if(gp.tileM.canMove(x+gp.tileSize, y_temp+ gp.tileSize) && gp.tileM.canMove(x, y_temp) && gp.tileM.canMove(x, y_temp+ gp.tileSize) && gp.tileM.canMove(x+gp.tileSize, y_temp)) {
			y = y_temp;
		}
		if(gp.tileM.canMove(x_temp+gp.tileSize, y+ gp.tileSize) && gp.tileM.canMove(x_temp, y) && gp.tileM.canMove(x_temp, y+ gp.tileSize) && gp.tileM.canMove(x_temp+gp.tileSize, y)) {
			x = x_temp;
		}
	}
}
