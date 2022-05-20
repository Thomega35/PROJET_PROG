package badGuys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class SimpleMonster extends Monsters{

    public SimpleMonster(GamePanel gp) {
        super(gp);
        lifePoint = 20;
        speed = 4;
    	getMonsterImage();
    }


    public void getMonsterImage() {
        try {
            idleImage = ImageIO.read(new File("res/player/superhero.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    	if (lifePoint <=0) {
    		dying=true;
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