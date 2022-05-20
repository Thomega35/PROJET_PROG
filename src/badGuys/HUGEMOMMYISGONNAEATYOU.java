package badGuys;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class HUGEMOMMYISGONNAEATYOU extends SimpleMonster {

	public HUGEMOMMYISGONNAEATYOU(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		attackPoint = 3;
		lifePoint = 20;
		speed = 2;
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
        g2.drawImage(image, x, y,(int) (gp.tileSize*3),(int) (gp.tileSize*3), null);
        timetodisplay++;
    }
}
