package entities;

import main.GamePanel;

public class Projectile extends Entity{

	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	public void update() {
		collisionOn = false;
		if(user == gp.player) {
			gp.cCheck.checkTile(this);
			if(collisionOn == true) {
				alive = false;
			}
			int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.dmgMonster(monsterIndex, attack);
				alive = false;
			}
		}
		if(user != gp.player) {
			boolean contactPlayer = gp.cCheck.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damageP(attack);
				alive = false;
			}
			gp.cCheck.checkTile(this);
			if(collisionOn == true) {
				alive = false;
			}
		}
		
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		case "upS": worldY -= speed; break;
		case "downS": worldY += speed; break;
		case "leftS": worldX -= speed; break;
		case "rightS": worldX += speed; break;
		}
		life--;
		if(life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12){
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public boolean haveMana(Entity user) {
		boolean haveMana = false;
		return haveMana;
	}
	public void subtractMana(Entity user) {
	}
}
