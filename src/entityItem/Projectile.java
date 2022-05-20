package entityItem;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import badGuys.Monsters;
import entity.Entity;
import entity.Player;
import main.Functions;
import main.GamePanel;
import tile.TileManager;

public class Projectile extends Items{
	Boolean sens;
	Boolean fromPlayer;
	
	public Projectile(GamePanel gp, Entity shooter) {
		super(gp, "res/projectiles/Pioupiou.png");
		// TODO Auto-generated constructor stub
		speed = 5;
		attackPoint = shooter.attackPoint;
		fromPlayer = shooter.getClass() == Player.class;
		if (fromPlayer) {
			sens = ((Player) shooter).keyH.lookLeft;
		}else {
			sens = gp.player.x < shooter.x;
		}
		
	}
	
	@Override
	public void update() {
		if (sens) {
			x-=speed;
		}else {
			x+=speed;
		}
		Entity cible;
		if (fromPlayer) {
			cible = Functions.giveMeFirstMonster(gp.listeMonsters, this, 35);
		}else {
			cible = gp.f.giveMeFirstPlayer(this, 35);
		}
		if(cible != null) {
			if (cible.lifePoint != 0) {
				if (attackPoint-cible.defence >= 0)
					cible.lifePoint -= (attackPoint-cible.defence);
			}
			cible.hurt();
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
	
	public void interaction(Player pl) {
		// TODO Auto-generated method stub
	}
}
