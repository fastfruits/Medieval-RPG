package object;

import entities.Entity;
import main.GamePanel;

public class Mana_Obj extends Entity{

	public Mana_Obj(GamePanel gp) {
		super(gp);

		name = "Mana Crystal";
		image = setUp("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image2 = setUp("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);

	}

}
