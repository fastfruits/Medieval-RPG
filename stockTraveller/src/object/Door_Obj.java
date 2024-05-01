package object;

import entities.Entity;
import main.GamePanel;

public class Door_Obj extends Entity {

	GamePanel gp;
	
public Door_Obj(GamePanel gp) {
	super(gp);
	
		name = "door";
		down1 = setUp("/objects/door_iron", gp.tileSize, gp.tileSize);
		
		collision = true;
	}
}
