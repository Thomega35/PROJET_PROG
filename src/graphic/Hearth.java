package graphic;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hearth {

    private int health;
    private int x;
    private int y;
    private GamePanel gp;

    public Hearth(Player player, GamePanel gp) {
        this.health = player.lifePoint;
        this.x = 10;
        this.y = 10;
        this.gp = gp;
    }

    void update() {

    }

    // Affiche la barre de vie
    public void draw(Graphics2D g2) throws IOException {
        // récupère l'image du joueur
        BufferedImage hearth = ImageIO.read(new File("res/screen/hearth.png"));
        BufferedImage half_hearth = ImageIO.read(new File("res/screen/half_hearth.png"));
        // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
        int i = 0;
        while(i < health-1) {
            g2.drawImage(hearth, x+i*18, y, 30, 30, null);
            i += 2;
        }
        if(health%2!=0) {
            g2.drawImage(half_hearth, x+i*18, y, 30, 30, null);
        }
    }

}
