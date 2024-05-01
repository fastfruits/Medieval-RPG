package object;

import entities.Entity;
import main.GamePanel;

public class Heart_Obj extends Entity{
	
	GamePanel gp;
	
	public Heart_Obj(GamePanel gp) {
		super(gp);

			name = "heart";
			image = setUp("/objects/heart_blank", gp.tileSize, gp.tileSize);
			image2 = setUp("/objects/heart_half", gp.tileSize, gp.tileSize);
			image3 = setUp("/objects/heart_full", gp.tileSize, gp.tileSize);
		}
}
