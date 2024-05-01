package monster;

import java.util.Random;

import entities.Entity;
import main.GamePanel;
import object.Rock_Obj;

public class Orc extends Entity{
	GamePanel gp;
	public Orc(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Orc";
		type = type_M;
		speed = 1;
		exp = 25;
		coin = 15;
		maxLife = 25;
		life = maxLife;
		attack = 6;
		defense = 1;
		
		projectile = new Rock_Obj(gp);
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 43;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	
	public void getImage() {
		
		down1 = setUp("/monsters/orc_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/monsters/orc_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/monsters/orc_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/monsters/orc_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/monsters/orc_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/monsters/orc_right_2", gp.tileSize, gp.tileSize);
		up1 = setUp("/monsters/orc_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/monsters/orc_up_2", gp.tileSize, gp.tileSize);
		stillUp = setUp("/monsters/orc_down_1", gp.tileSize, gp.tileSize);
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
			actionLockCounter = 0;
		}
		int i = new Random().nextInt(100) + 1;
		if(i > 99 && projectile.alive == false && shotCounter == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shotCounter = 0;
		}
	}

}
