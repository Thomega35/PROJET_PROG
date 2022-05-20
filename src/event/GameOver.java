package event;

import entity.Player;
import main.GamePanel;

import java.awt.*;

public class GameOver {
	GamePanel gp;
    Player player;
    public boolean end;
    
	public GameOver(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = new Player(gp, null);
        this.end = false;
	}
	public void endOrNot() {
        if(gp.tileM.numMap == 100 && player.x > gp.screenWidth-gp.tileSize-5 && player.x < gp.screenWidth) {
            end = true;
        }
    }

    public void update() {
        endOrNot();
    }

    public void draw(Graphics2D g2) {
        g2.setBackground(Color.black);
        g2.setColor(Color.WHITE);
        g2.scale(2, 2);
        g2.drawString("GAME OVER", 140, 140);
        player.x = 100;
        player.y = 100;
        player.dead = true;
        player.draw(g2);
    }

}
