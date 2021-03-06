package badGuys;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import entityItem.Projectile;
import main.GamePanel;
			
public class ShootingMonster extends Monsters {
	int display6fightFrame;
	int timetodisplay;
	public int display6fHurtFrame;
	ArrayList<BufferedImage> moving;
	ArrayList<BufferedImage> hiting;
	ArrayList<BufferedImage> hurting;
	ArrayList<BufferedImage> Dyiing;
			
	public ShootingMonster(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		lifePoint = 8;
		attackPoint=1;
        speed = 3;
        timetodisplay = 0;
        display6fightFrame = 91;
        display6fHurtFrame = 11;
        hiting = new ArrayList<BufferedImage>();
        moving = new ArrayList<BufferedImage>();
        hurting = new ArrayList<BufferedImage>();
        Dyiing = new ArrayList<BufferedImage>();
    	getMonsterImage();
	}		
			
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage image = null;
		if (dead) {
			if (timetodisplay < 60) {
				image = Dyiing.get((timetodisplay/30)%3);
			}else {
				image = Dyiing.get(3);
			}
		}else if (display6fHurtFrame <= 10) {
			image = hurting.get((timetodisplay/5)%2);
			display6fHurtFrame++;
		}else if(display6fightFrame<= 90) {
			image = hiting.get(((timetodisplay+45)/15)%6);
			display6fightFrame++;
		}else {
			image = moving.get((timetodisplay/15)%4);
		}	
		if (sens) {
			image = gp.player.flip(image);
		}	
		g2.drawImage(image, x, y, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5), null);
		timetodisplay++;
	}		
			
	private void hit() {
		if (display6fightFrame >= 90) {
			Projectile p = new Projectile(gp, this);
			if (sens) {
				p.setDefaultValues(x+30,y+20);
			}else {
				p.setDefaultValues(x,y+20);
			}
			gp.listeObjects.add(p);
			display6fightFrame = 0;
		}	
	}		
			
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (lifePoint <=0 && !dead) {
			dead=true;
			timetodisplay = 0;
    	}else if (!dead){
			if (gp.player.y+40 > y && gp.player.y-40 < y) {
				hit();
			}
			sens = gp.player.x > x;
			orientedPosition();
    	}	
			
		if (dead && timetodisplay > 90) {
			dying = true;
		}	
			
	}		
			
	public void hurt() {
		display6fHurtFrame = 0;
	}		
			
	public void getMonsterImage() {
		try{
			
			//idleImage = ImageIO.read(new File("res/player/superhero.png"));
			idleImage = ImageIO.read(ShootingMonster.class.getResource("/res/player/superhero.png"));
			
            for (int i=1; i<=6;i++) {
				if (i<=4) moving.add(ImageIO.read(ShootingMonster.class.getResource("/res/monsters/Shooter_walking"+i+".png")));
				hiting.add(ImageIO.read(ShootingMonster.class.getResource("/res/monsters/Shooter_hitting"+i+".png")));
				if (i<=2) hurting.add(ImageIO.read(ShootingMonster.class.getResource("/res/monsters/Shooter_hurt"+i+".png")));
				if (i<=4) Dyiing.add(ImageIO.read(ShootingMonster.class.getResource("/res/monsters/Shooter_dead"+i+".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }	
			
	}		
}			
			