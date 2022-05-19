package entityItem;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Monsters;
import entity.Player;
import main.GamePanel;
import tile.TileManager;

public class Projectile extends Items{
	Boolean sens;
	int degat = 5;
	
	public Projectile(GamePanel gp, Player shooter) {
		super(gp, "res/monsters/Pioupiou.png");
		// TODO Auto-generated constructor stub
		speed = 5;
		sens = shooter.keyH.lookLeft;
		
	}
	
	@Override
	public void update() {
		if (sens) {
			x-=speed;
		}else {
			x+=speed;
		}
		Monsters cible = TileManager.giveMeFirstMonster(gp.listeMonsters, this, 25);
		if(cible != null) {
			cible.lifePoint -= degat;
			dying = true;
		}else if (x <= 0 || x >= gp.screenWidth){
			dying = true;
		}
		
	}

	@Override
	public void setDefaultValues(int a,int b) {
		x=a;
		y=b;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// r�cup�re l'image de l'objet
		BufferedImage image = idleImage;
		// affiche l'objet avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, 10, 3, null);
	}
	
	@Override
	public void interaction(Player pl) {
		// TODO Auto-generated method stub
	}
}
