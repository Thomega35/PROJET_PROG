package badGuys;

import java.awt.Graphics2D;
import java.util.Random;

import entity.Entity;
import main.GamePanel;


public abstract class Monsters extends Entity {
    public Boolean dying;
    public boolean dead;
    Boolean sens;
    int xLag;
    int yLag;
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
        int r=new Random().nextInt(max-20-positionPlayer-5) + positionPlayer+5;
        return r;
    }
	
	public void setDefaultValues(int x, int y) {
        // monsters pop position may to be random and far of the player
        this.x = x;
        this.y = y;
    }

	public abstract void hurt();


    public void orientedPosition(){
        int x_temp;
        int y_temp;
        int aimx = gp.player.x;
        int aimy = gp.player.y-20;
        // Choix du x
        if(aimx+2<x) {
            x_temp = x-speed/2;
        }else if(aimx-2>x){
            x_temp=x+speed/2;
        }else{
            x_temp=x;
        }

        // Choix du y
        if(aimy+2<y) {
            y_temp = y-speed/2;
        }else if(aimy-2>y){
            y_temp=y+speed/2;
        }else{
            y_temp=y;
        }
        // Si on peut y aller on y va :z
        whereIgo(x_temp,y_temp);
        }

}