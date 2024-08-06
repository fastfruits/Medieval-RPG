package object;

import entities.Entity;
import main.GamePanel;

public class Weapon_Obj extends Entity{

	public Weapon_Obj(GamePanel gp) {
		super(gp);
		name = "Sword";
		type = type_S;
		description = "[" + name +  "]\nWeak iron sword.";
		down1 = setUp("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 1;
		atkArea.width = 40;
		atkArea.height = 40;
	}

}
