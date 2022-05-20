package badGuys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Player;
import entityItem.Projectile;
import main.GamePanel;
import main.KeyHandler;

import java.util.ArrayList;
import java.util.Random;

public class SimpleMonster extends Monsters{

	int display6fightFrame;
	int timetodisplay;
	protected int display6fHurtFrame;
	ArrayList<BufferedImage> moving;
	ArrayList<BufferedImage> hiting;
	ArrayList<BufferedImage> hurting;
	ArrayList<BufferedImage> Dyiing;

    public SimpleMonster(GamePanel gp) {
        super(gp);
        lifePoint = 8;
		attackPoint=2;
        speed = 3;
        timetodisplay = 0;
        display6fightFrame = 121;
        display6fHurtFrame = 11;
        hiting = new ArrayList<BufferedImage>();
        hurting = new ArrayList<BufferedImage>();
        Dyiing = new ArrayList<BufferedImage>();
        moving = new ArrayList<BufferedImage>();
    	getMonsterImage();
    }


    public void getMonsterImage() {
        try {
            idleImage = ImageIO.read(new File("res/monsters/Mummy_idle1.png"));
            for (int i=1; i<=6;i++) {
            	hiting.add(ImageIO.read(new File("res/monsters/Mummy_Slash"+i+".png")));
            	if (i<=2) {hurting.add(ImageIO.read(new File("res/monsters/Mummy_Hurt"+i+".png")));}
            	Dyiing.add(ImageIO.read(new File("res/monsters/Mummy_dead"+i+".png")));
            	if (i<=4) {moving.add(ImageIO.read(new File("res/monsters/Mummy_idle"+i+".png")));}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hit() {
    	if (display6fightFrame >= 120) {
    		if (gp.player.lifePoint != 0) {
    			if (gp.player.lifePoint > attackPoint) {
    				gp.player.lifePoint -= attackPoint;
    			}else {
    				gp.player.lifePoint = 0;
    			}
    		}
    		gp.player.hurt();
    		display6fightFrame = 0;
		}
	}

    public void update() {
    	if (lifePoint <=0 && !dead) {
			dead=true;
			timetodisplay = 0;
    	}else if (!dead){
			if (gp.player.y+35 > y && gp.player.y-35 < y && gp.player.x+35 > x && gp.player.x-35 < x) {
				hit();
			}
			sens = gp.player.x > x;
			orientedPosition();
    	}

		if (dead && timetodisplay > 200) {
			loot();
			dying = true;
		}
    }

    public void draw(Graphics2D g2) {
        // r�cup�re l'image du joueur
        BufferedImage image = null;
        if (dead) {
			if (timetodisplay < 100) {
				image = Dyiing.get((timetodisplay/50)%3);
			}else {
				image = Dyiing.get(3);
			}
		}else if (display6fHurtFrame <= 10) {
			image = hurting.get((timetodisplay/5)%2);
			display6fHurtFrame++;
		}else if(display6fightFrame<= 120) {
			image = hiting.get((timetodisplay/20)%6);
			display6fightFrame++;
		}else {
			image = moving.get((timetodisplay/15)%4);
		}
		if (sens) {
			image = gp.player.flip(image);
		}
        // affiche le personnage avec l'image "image", avec les coordonn?es x et y, et de taille tileSize (16x16) sans ?chelle, et 48x48 avec ?chelle)
        g2.drawImage(image, x, y,(int) (gp.tileSize*1.5),(int) (gp.tileSize*1.5), null);
        timetodisplay++;
    }


	@Override
	public void hurt() {
		// TODO Auto-generated method stub
		display6fHurtFrame = 0;
	}
	
	public void loot() {
		
	}
}