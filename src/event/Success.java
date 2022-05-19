package event;

import entity.Player;
import main.GamePanel;

import java.awt.*;

public class Success {
    GamePanel gp;
    Player player;
    public boolean end;

    public Success(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.end = false;
    }

    public void endOrNot() {
        if(gp.tileM.numMap == 2 && player.x > gp.screenWidth-gp.tileSize-5 && player.x < gp.screenWidth) {
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
        g2.drawString("Vous avez gagné !", 140, 140);
    }

}
