package entities;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import object.Fireball_Obj;

public class NPC_Oldman extends Entity {
 
	public NPC_Oldman(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
		solidArea = new Rectangle();
		solidArea.x = 16;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16;
		solidArea.height = 20;
	}
	
	public void getImage() {
		up1 = setUp("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setUp("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
		stillUp = setUp("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);

	}
	
	public void setDialogue() {
		dialogues[0] = "Hello travaller!";
		dialogues[1] = "I see you aren't magically gifted.";
		dialogues[2] = "Would you like me to teach you to throw fireballs?\nIt'll cost ya " + (25 - (25 / 100 * gp.player.discount)) + " coins.\nSpeak to me again to purchase it.";
		dialogues[3] = "Here you go";
	}
	
	
	
	public void setAction() {
		
		actionLockCounter++;
		if(actionLockCounter == 180) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			
			if(i <= 20) {
				direction = "up";
			}
			if(i > 20 && i <= 40) {
				direction = "down";
			}
			if(i > 40 && i <= 60) {
				direction = "left";
			}
			if(i > 75 && i <= 80) {
				direction = "right";
			}
			if(i > 80 && i <= 100) {
				direction = "stillUp";
			}
			actionLockCounter = 0;
		}
		
	}
	
	public void speak() {
		super.speak();
	}
}
