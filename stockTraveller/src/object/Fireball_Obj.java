package object;

import entities.Entity;
import entities.Projectile;
import main.GamePanel;

public class Fireball_Obj extends Projectile{
	GamePanel gp;
	public Fireball_Obj(GamePanel gp) {
		super(gp);
		solidArea.x = 5;
		solidArea.y = 5;
		solidArea.width = 38;
		solidArea.height = 38;
		this.gp = gp;
		maxLife = 80;
		life = maxLife;
		name = "Fireball";
		collisionOn = false;
		attack = 2;
		useCost = 1;
		speed = 7;
		type = type_F;
		alive = false;
		getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		down1 = setUp("/projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
		up1 = setUp("/projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/projectiles/fireball_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/projectiles/fireball_right_2", gp.tileSize, gp.tileSize);
	}
	public boolean haveMana(Entity user) {
		boolean haveMana = false;
		
		if(user.mana >= useCost) {
			haveMana = true;
		}
		
		return haveMana;
	}
	public void subtractMana(Entity user) {
		user.mana -= useCost;
	}
}
