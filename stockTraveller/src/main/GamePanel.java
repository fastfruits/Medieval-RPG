package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import object.Fireball_Obj;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

	final int originalTileSize = 16;
	final int scale = 3;
	public double timerN = 0;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public final int maxMap = 10;
	public int currentMap = 2;
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	public CollisionChecker cCheck = new CollisionChecker(this, keyH);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][13];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][20];
	public UI ui = new UI(this);
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	Config config = new Config(this);

	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int optionState = 2;
	public final int dialogueState = 3;
	public final int statusState = 4;
	public final int gameOverState = 5;
	public final int statState = 6;
	public final int winState =7;
	
	public boolean fullscreen = false;
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		
	}
	
	public void sellFireball() {
		if(player.coin >= 25 - (25 / 100 * player.discount)) {
			player.coin -= 25 - (25 / 100 * player.discount);
			player.projectile = new Fireball_Obj(this);
			ui.addMessage("You can now shoot fireballs!");
		}
		else {
			ui.addMessage("Not enough coins!");
		}
	}
	
	public void retry() {
		player.setDefaultPos();
		player.restoreStats();
		aSetter.setNPC();
		aSetter.setMonster();
	}
	
	public void restart() {
		player.setDefaultValues();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
	}
	
	public void setUpGame() {
		
		gameState = titleState;
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		playMusic(0);
		if(fullscreen == true) {
			setFullScreen();
		}
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics(); 
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval; 
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if(delta >= 1) {
			update();
			drawToTempScreen();
			drawToScreen();
			delta--;
			}
			
			if(timer >= 1000000000) {
				timer = 0;
			}
		}
	}
	
	
	
	public void update() {
		if(gameState == playState) {
			timerN += 0.016;
			player.update();
			for(int i = 0;i< npc[1].length;i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
//			for(int i = 0; i < obj.length; i++) {
//				if(obj[i] != null) {
//					obj[i].update();
//				}
//			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false ) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}
		}
		if(gameState == optionState) {		
		}
		if(gameState == statusState) {
			
		}
	}
	
	public void drawToTempScreen() {
		if(gameState == titleState) {
			ui.draw(g2);
		}
		else {
			tileM.draw(g2);
			entityList.add(player);
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			for(int i = 0; i < obj[1].length; i++) {
				if(obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}
			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					entityList.add(monster[currentMap][i]);
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			Collections.sort(entityList, new Comparator<Entity>()	{

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}	
			});
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			for(int i = 0; i < entityList.size(); i++) {
				entityList.remove(i);
			}
			ui.draw(g2);
		}
	}
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
	public void setFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
		
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
		
	}
}
