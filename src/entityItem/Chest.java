package entityItem;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import entity.Inventaire;
import javax.imageio.ImageIO;
import entity.Entity;
import entity.Player;
import main.GamePanel;

public abstract class Chest extends Items{
	public Items obj;
	public boolean lock;
		
	public Chest(GamePanel gp, String urlImage,Items item,boolean lock) {
		super(gp,urlImage);
		this.obj=item;
		this.lock=lock;
		}

}


