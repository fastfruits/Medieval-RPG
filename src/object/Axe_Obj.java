package object;

import entities.Entity;
import main.GamePanel;

public class Axe_Obj extends Entity{

	public Axe_Obj(GamePanel gp) {
		super(gp);
		
		name = "Axe";
		type = type_A;
		down1 = setUp("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		description = "[" + name + "]\nA heavy axe that can chop\nenemies or wood.";
		atkArea.width = 30;
		atkArea.height = 30;
		
	}

}
