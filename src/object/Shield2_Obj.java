package object;

import entities.Entity;
import main.GamePanel;

public class Shield2_Obj extends Entity{

	public Shield2_Obj(GamePanel gp) {
		super(gp);
		name = "Cobalt Shield";
		type = type_SH;
		down1 = setUp("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenseValue = 3;
		description = "[" + name + "]\nA much better shield\nmade of cobalt.";
	
	}

}
