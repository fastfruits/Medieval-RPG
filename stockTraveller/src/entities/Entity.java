package entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX, worldY, speed, life, maxLife, type, solidAreaDefaultX, solidAreaDefaultY, strength, defense, intelligence, fortitude, attack, exp, level, nextLvlXp, coin, attackValue, defenseValue, maxMana, mana, useCost;
	public BufferedImage image, image2, image3, up1, up2, down1, down2, left1, left2, right1, right2, stillUp, stillDown, stillLeft, stillRight, atkUp1, atkUp2, atkDown1, atkDown2, atkLeft1, atkLeft2, atkRight1, atkRight2;
	public String name;
	public boolean collision = false;
	public String direction = "down";	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle atkArea = new Rectangle(0, 0, 0, 0);
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public boolean invincible = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	int dyingCounter = 0;
	public int shotCounter = 0;
	public int invincibleCnt = 0;
	public int manaRefresh = 0;
	public boolean atk = false;
	public String[] dialogues = new String[10];
	public int hpBarCounter = 0;
	public Entity currentWeapon, currentShield;
	UtilityTool uTool = new UtilityTool();
	public String description = "";
	public final int type_P = 0;
	public final int type_N = 1;
	public final int type_M = 2;
	public final int type_S = 3;
	public final int type_A = 4;
	public final int type_SH = 5;
	public final int type_C = 6;
	public final int type_F = 7;

	public int S = 0;
	public int P = 0;
	public int E = 0;
	public int C =0;
	public int I = 0;
	public int A = 0;
	public int L = 0;
	public int discount = 0;
	public int dropInc = 0;
	public int maxSPECIAL = 0;
	public int numSPECIAL = 0;
	
	
	public Projectile projectile;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void use(Entity entity) {}
	
	public void setAction() {
	}
	
	public void speak() {
		if(dialogues[gp.ui.dialogueIndex] == null) {
			gp.ui.dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[gp.ui.dialogueIndex];
		gp.ui.dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":direction = "down";break;
		case  "down":direction = "up";break;
		case "left":direction = "right";break;
		case "right":direction = "left";break;
		}
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cCheck.checkTile(this);
		boolean contactP = gp.cCheck.checkPlayer(this);
		gp.cCheck.checkObject(this, false);
		gp.cCheck.checkEntity(this, gp.npc);
		gp.cCheck.checkEntity(this, gp.monster);

		if(this.type == type_M && contactP == true) {
			damageP(attack);
		}
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		spriteCounter++;
		if(spriteCounter > 16) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		if(invincible == true) {
			invincibleCnt++;
			if(invincibleCnt > 30) {
				invincible = false;
				invincibleCnt = 0;
			}
		}
		if(shotCounter < 30) {
			shotCounter++;
		}
	}
	
	public BufferedImage setUp(String filePath, int width, int height) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(filePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			if(type != 7) {
				switch(direction) {
				case "up":
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
				break;
				case "down":
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
				break;
				case "left":
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
				break;
				case "right":
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
				break;
				case "stillUp":
					image = stillUp;
				break;
				}
			}
			else if(type == 7) {
				switch(direction) {
				case "up":
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
					break;
				case "down":
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
					break;
				case "left":
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
					break;
				case "right":
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
					break;
				case "upS":if(spriteNum == 1) {image = up1;}if(spriteNum == 2) {image = up2;}break;
				case "downS":  if(spriteNum == 1) {image = down1;}if(spriteNum == 2) {image = down2;} break;
				case "leftS": if(spriteNum == 1) {image = left1;}if(spriteNum == 2) {image = left2;} break;
				case "rightS": if(spriteNum == 1) {image = right1;}if(spriteNum == 2) {image = right2;} break;
				}
			}
			
			if(type == 2 && hpBarOn == true) {
				double oneScale = (double) gp.tileSize/maxLife;
				double hpBarVal = oneScale * life;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-6, gp.tileSize + 2, 12);
				g2.setColor(new Color(255, 0, 30));
				if(life > 0) {
					g2.fillRect(screenX, screenY - 5, (int)hpBarVal, 10);
				}
				else {
					g2.fillRect(screenX, screenY - 5, 0, 10);
				}
				
				hpBarCounter++;
				if(hpBarCounter > 600){
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			if(invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			}
			if(dying == true) {
				dyingAni(g2);
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		}			
	}
	public void dyingAni(Graphics2D g2) {
		dyingCounter++;
		if(dyingCounter <= 5) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
		if(dyingCounter > 5 && dyingCounter <= 10) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
		if(dyingCounter > 10 && dyingCounter <= 15) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
		if(dyingCounter > 15 && dyingCounter <= 20) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
		if(dyingCounter > 20 && dyingCounter <= 25) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
		if(dyingCounter > 25 && dyingCounter <= 30) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
		if(dyingCounter > 30 && dyingCounter <= 35) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
		if(dyingCounter > 35 && dyingCounter <= 40) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));alive = false;}
	}

	public void damageP(int attack) {
		if(gp.player.invincible == false) {
			gp.playSE(3);
			int damage = attack - gp.player.defense;
			if(damage < 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}
	}
}

