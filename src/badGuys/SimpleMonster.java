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
	private int display6fHurtFrame;
	Boolean sefight = true;
	ArrayList<BufferedImage> moving;
	ArrayList<BufferedImage> hiting;
	ArrayList<BufferedImage> hurting;
	ArrayList<BufferedImage> Dyiing;
	ArrayList<BufferedImage> idle;
	
    public SimpleMonster(GamePanel gp) {
        super(gp);
        lifePoint = 8;
		attackPoint=1;
        speed = 4;
        timetodisplay = 0;
        display6fightFrame = 91;
        hiting = new ArrayList<BufferedImage>();
        moving = new ArrayList<BufferedImage>();
        hurting = new ArrayList<BufferedImage>();
        Dyiing = new ArrayList<BufferedImage>();
        idle = new ArrayList<BufferedImage>();
    	getMonsterImage();
    }


    public void getMonsterImage() {
        try {
            idleImage = ImageIO.read(new File("res/player/Mummy_idle1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void hit() {
    	if (display6fightFrame >= 120) {
    		sefight = true;
    		display6fightFrame = 0;
		}
	}

    public void update() {
    	if (lifePoint <=0 && !dead) {
			dead=true;
			timetodisplay = 0;
			System.out.println("yo");
    	}else if (!dead){
			if (gp.player.y+20 > y && gp.player.y-20 < y) {
				hit();
			}
			sens = gp.player.x > x;
    	}
		
		if (dead && timetodisplay > 300) {
			dying = true;
		}
    }

    public void draw(Graphics2D g2) {
        // récupère l'image du joueur
        BufferedImage image = idleImage;
        if (sens) {
			image = gp.player.flip(image);
		}
        // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }


	@Override
	public void hurt() {
		// TODO Auto-generated method stub
		
	}
}