package entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Sword extends objets{
		public Sword(GamePanel gp){
			super(gp);
			caract[0]=10;
		}
		
		public void getObjetImage() {
			try {
	            idleImage = ImageIO.read(new File("res/player/superhero.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		public void interaction(Inventaire stuff) {
			stuff.inventaire.add(this);	
		}
}
