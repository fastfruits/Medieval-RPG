package object;

import entities.Projectile;
import main.GamePanel;

public class Rock_Obj extends Projectile{
	GamePanel gp;
	public Rock_Obj(GamePanel gp) {
		super(gp);
		this.gp = gp;
		maxLife = 100;
		life = maxLife;
		name = "Fireball";
		collisionOn = false;
		attack = 2;
		useCost = 1;
		speed = 3;
		type = type_F;
		alive = false;
		getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		down1 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		up1 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		left1 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		right1 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
	}

}
