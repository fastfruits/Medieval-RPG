package object;

import entities.Entity;
import main.GamePanel;

public class Chest_Obj extends Entity{

	public boolean isOpened = false;
	GamePanel gp;
	
public Chest_Obj(GamePanel gp) {
		super(gp);
		name = "chest";
		down1 = setUp("/objects/chest", gp.tileSize, gp.tileSize);
		collision = true;
	}	
}
