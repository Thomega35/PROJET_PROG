package main;

import java.awt.*;
import javax.swing.JPanel;

import badGuys.Monsters;
import badGuys.ShootingMonster;
import badGuys.SimpleMonster;
import entity.Player;
import entityItem.Items;
import entityItem.Shield;
import entityItem.Sword;
import event.GameOver;
import event.Success;
import graphic.Hearth;
import tile.TileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8330524675799416531L;
	//Paramétres de l'écran
	final int originalTileSize = 16; // une tuile de taille 16x16
	final int scale = 3; // échelle utilisée pour agrandir l'affichage
	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; // ces valeurs donnent une résolution 4:3
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels

	// FPS : taux de rafraichissement
	int FPS = 60;
	// Création des différentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this, keyH);
	Hearth hearth = new Hearth(player, this);
	//Monsters simplemonster1 = new SimpleMonster(this);
	//Monsters shootermonster1 = new ShootingMonster(this);
	Success success = new Success(this, player);
	public GameOver gameOver = new GameOver(this, player);
	public TileManager tileM = new TileManager(this, 1);
	public Functions f = new Functions(this);

	//Liste choses
	public ArrayList<Items> listeObjects = new ArrayList<Items>();
	public ArrayList<Monsters> listeMonsters = new ArrayList<Monsters>();

	// Constructeur de la classe
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

		
		//listeMonsters.add(simplemonster1);
		//listeMonsters.add(shootermonster1);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	public void run() {

		double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval;

		while(gameThread != null) { //Tant que le thread du jeu est actif

			tileM = new TileManager(this, tileM.numMap);
			//Permet de mettre é jour les différentes variables du jeu
			update();
			//Dessine sur l'écran le personnage et la map avec les nouvelles informations. la méthode "paintComponent" doit obligatoirement étre appelée avec "repaint()"
			repaint();
			//Calcule le temps de pause du thread


			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void update() {
		player.update();
		success.update();
		hearth.update();
		for(Items obj : listeObjects) {
			obj.update();
		}
		for(Monsters mons : listeMonsters) {
			mons.update();
		}
		listeObjects.removeIf(x->x.dying);
		listeMonsters.removeIf(x->x.dying);
	}

	public void paintComponent(Graphics g) {
		if(!success.end && !gameOver.end) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			tileM.draw(g2);
			for(int i=0;i<(tileM.itemMaps[tileM.numMap].length);i++) {
				if(tileM.itemMaps[tileM.numMap][i]!=null) {
				tileM.itemMaps[tileM.numMap][i].draw(g2);}
			}
			for(Monsters mons : listeMonsters) {
				mons.draw(g2);
			}
			player.draw(g2);
			try {
				hearth.draw(g2);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			g2.dispose();
		}
		else if (!gameOver.end){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			success.draw(g2);
		}else {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			gameOver.draw(g2);
		}
	}

}


/*
public void run() {
double drawInterval = 1000000000/FPS;
double delta = 0;
long lastTime = System.nanoTime();
long currentTime;
long timer = 0;
long drawCount = 0;

while (gameThread != null) {

	currentTime = System.nanoTime();

	delta += (currentTime - lastTime) / drawInterval;
	timer += (currentTime - lastTime);
	lastTime = currentTime;

	if(delta >= 1) {
		update();
		repaint();
		delta--;
		drawCount++;
	}

	if(timer >= 1000000000) {
		System.out.println("FPS:" + drawCount);
		drawCount = 0;
		timer = 0;
	}
}
}*/
