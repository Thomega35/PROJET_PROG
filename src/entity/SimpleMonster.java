package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class SimpleMonster extends Monsters{

    public SimpleMonster(GamePanel gp, KeyHandler keyH, Player player) {
        this.gp = gp;
        this.keyH = keyH;
        this.player=player;
        setDefaultValues();
        getSimpleMonsterImage();
    }

    public void setDefaultValues() {
        // monsters pop position may to be random and far of the player

        x = positionSimpleMonster(player.x, gp.screenWidth);
        y = positionSimpleMonster(player.y, gp.screenHeight);
        speed = 4;
    }

    public int positionSimpleMonster(int positionPlayer, int max){
        int r=new Random().nextInt(max-positionPlayer-5) + positionPlayer+5;
        return r;
    }


    public void getSimpleMonsterImage() {
        try {

            idleImage = ImageIO.read(new File("res/player/superhero.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {

    }

    public void draw(Graphics2D g2) {
        // récupère l'image du joueur
        BufferedImage image = idleImage;
        // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }


}

