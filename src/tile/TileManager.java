package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	UtilityTool uTool = new UtilityTool();
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[50];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map-2-2.txt", 0);
		loadMap("/maps/interior.txt", 1);
		loadMap("/maps/forest.txt", 2);
		
	}
	
	public void getTileImage() {
			
		setUp(0, "grass00", false);
		setUp(1, "grass00", false);
		setUp(2, "grass00", false);
		setUp(3, "grass00", false);
		setUp(4, "grass00", false);
		setUp(5, "grass00", false);
		setUp(6, "grass00", false);
		setUp(7, "grass00", false);
		setUp(8, "grass00", false);
		setUp(9, "grass00", false);
		setUp(10, "grass00", false);
		setUp(11, "grass01", false); //Accent
		setUp(12, "road00", false);
		setUp(13, "road01", false); //TopLeftC
		setUp(14, "road02", false); //Top
		setUp(15, "road03", false); //TopRightC
		setUp(16, "road04", false); //Left
		setUp(17, "road05", false); //Right
		setUp(18, "road06", false); //BotLeftC
		setUp(19, "road07", false); //Bot
		setUp(20, "road08", false); //BotRightC
		setUp(21, "road09", false); //BotRightE
		setUp(22, "road10", false); //BotLeftE
		setUp(23, "road11", false); //TopRightE
		setUp(24, "road12", false); //TopLeftE
		setUp(25, "wall", true); 
		setUp(26, "water00", true);
		setUp(27, "water01", true); //Accent
		setUp(28, "water02", true); //TopLeftC
		setUp(29, "water03", true); //Top
		setUp(30, "water04", true); //TopRightC
		setUp(31, "water05", true); //Left
		setUp(32, "water06", true); //Right
		setUp(33, "water07", true); //BotLeftC
		setUp(34, "water08", true); //Bot
		setUp(35, "water09", true); //BotRightC
		setUp(36, "water10", true); //BotRightE
		setUp(37, "water11", true); //BotLeftE
		setUp(38, "water12", true); //TopRightE
		setUp(39, "water13", true); //TopLeftE
		setUp(40, "earth", false);
		setUp(41, "floor01", false);
		setUp(42, "tree-2", true);
		
	}
	
	public void setUp(int index, String filePath, boolean collision) {
				
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + filePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void loadMap(String filePath, int map) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
					
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;
			
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}	  
		}
	}
}
