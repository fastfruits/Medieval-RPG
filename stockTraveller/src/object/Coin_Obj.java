package object;

import entities.Entity;
import main.GamePanel;

public class Coin_Obj extends Entity {

	GamePanel gp;
	
public Coin_Obj(GamePanel gp) {
	super(gp);

		name = "coin";
		down1 = setUp("/objects/coin_bronze", gp.tileSize, gp.tileSize);
	}
}
