package object;

import entities.Entity;
import main.GamePanel;

public class Key_Obj extends Entity {

	GamePanel gp;
	
	public Key_Obj(GamePanel gp) {
		super(gp);

		name = "Rusty Key";
		description = "[" + name +  "]\nUse to open doors and\nchests.";
		down1 = setUp("/objects/key", gp.tileSize, gp.tileSize);
		solidAreaDefaultX = 0;
		solidAreaDefaultY = 0;
	}
}
