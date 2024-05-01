package monster;

import java.util.Random;

import entities.Entity;
import main.GamePanel;

public class GreenSlime extends Entity{

	GamePanel gp;
	
	public GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Green Slime";
		type = type_M;
		speed = 1;
		exp = 2;
		coin = 1;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 43;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	
	public void getImage() {
		
		down1 = setUp("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		up1 = setUp("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		stillUp = setUp("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter == 180) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if(i <= 20) {direction = "up";}
			if(i > 20 && i <= 40) {direction = "down";}
			if(i > 40 && i <= 60) {direction = "left";}
			if(i > 75 && i <= 80) {direction = "right";}
			if(i > 80 && i <= 100) {direction = "stillUp";}
		}
	}	

	
}
