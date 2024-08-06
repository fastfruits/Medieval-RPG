package object;

import entities.Entity;
import main.GamePanel;

public class Shield_Obj extends Entity{

	public Shield_Obj(GamePanel gp) {
		super(gp);
		type = type_SH;
		name = "Shield";
		description = "[" + name +  "]\nShabby wooden shield.";
		down1 = setUp("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;
	}

}
