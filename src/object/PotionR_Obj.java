package object;

import entities.Entity;
import main.GamePanel;

public class PotionR_Obj extends Entity {

	GamePanel gp;
	
	public PotionR_Obj(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_C;
		name = "Health Potion";
		down1 = setUp("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Health Potion]\nHeals you 30% (" + (gp.player.maxLife / 10) * 3 + ") of your\nmax life.";
	}
	
	public void use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drank the health potion healing " + (gp.player.maxLife / 10) * 3 + " life points!";
		entity.life += (gp.player.maxLife / 10) * 3;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
	}

}
