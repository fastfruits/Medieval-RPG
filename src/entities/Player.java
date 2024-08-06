package entities;

import main.KeyHandler;
import object.Axe_Obj;
import object.Fireball_Obj;
import object.Key_Obj;
import object.PotionR_Obj;
import object.Shield_Obj;
import object.Weapon_Obj;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	
	public final int screenX;
	public final int screenY;
	public int keyCnt = 0;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int inventorySize = 20;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		getAtkImage();
		setItems();
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 16;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16;
		solidArea.height = 20;
	}
	
	public void setDefaultPos() {
		worldX = gp.tileSize * 4;
		worldY = gp.tileSize * 42;
		direction = "down";
	}
	
	public void setSPECIAL() {
		strength = (S / 2) + 1;
		fortitude = (E / 2) + 1;
		discount = C * 10;
		maxMana = 2 + (I / 2);
		speed = 2 + (A / 2);
		dropInc = L * 10;
	}
	
	public void restoreStats() {
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	
	public void setItems() {
		inventory.clear();
	}
	
	public void cheats() {
		if(keyH.sCheat == true) {
			speed = 15;
		}
		else if(keyH.cCheat == true) {
			gp.player.coin = 999;
		}
		else if(keyH.kCheat == true) {
			keyCnt = 999;
		}
	}
	
	public void setDefaultValues() {
		maxSPECIAL = 5;
		numSPECIAL = 0;
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 42;
		mana = maxMana;
		maxLife = 6;
		life = maxLife;
		level = 1;
		exp = 0;
		nextLvlXp = 5;
		coin = 0;
		direction = "down";
		attack = getAttack();
		defense = getDefense();
		keyCnt++;
	}
	
	public int getAttack() {
		if(currentWeapon != null) {
		atkArea = currentWeapon.atkArea;
		return attack = strength * currentWeapon.attackValue;
		}
		return 0;
	}
	
	public int getDefense() {
		if(currentShield != null) {
		return defense = fortitude * currentShield.defenseValue;
		}
		return 0;
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndex();
		if(itemIndex < inventory.size()) {
			Entity selectedI = inventory.get(itemIndex);
			if(selectedI.type == type_S || selectedI.type == type_A) {
				currentWeapon = selectedI;
				attack = getAttack();
			}
			if(selectedI.type == type_SH) {
				currentShield = selectedI;
				defense = getDefense();
			}	
			if(selectedI.type == type_C) {
				selectedI.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

	public void getPlayerImage() {
			up1 = setUp("/player/characterU1", gp.tileSize, gp.tileSize);
			up2 = setUp("/player/characterU2", gp.tileSize, gp.tileSize);
			down1 = setUp("/player/characterD1", gp.tileSize, gp.tileSize);
			down2 = setUp("/player/characterD2", gp.tileSize, gp.tileSize);
			left1 = setUp("/player/characterL1", gp.tileSize, gp.tileSize);
			left2 = setUp("/player/characterL2", gp.tileSize, gp.tileSize);
			right1 = setUp("/player/characterR1", gp.tileSize, gp.tileSize);
			right2 = setUp("/player/characterR2", gp.tileSize, gp.tileSize);
			stillUp = setUp("/player/characterUS", gp.tileSize, gp.tileSize);
			stillDown = setUp("/player/character (1)", gp.tileSize, gp.tileSize);
			stillLeft = setUp("/player/characterLS", gp.tileSize, gp.tileSize);
			stillRight = setUp("/player/characterRS", gp.tileSize, gp.tileSize);
	}
	
	public void getAtkImage() {
		
		atkUp1 = setUp("/player/characterAU1", gp.tileSize, gp.tileSize * 2);
		atkUp2 = setUp("/player/characterAU2", gp.tileSize, gp.tileSize * 2);
		atkDown1 = setUp("/player/characterAD1", gp.tileSize, gp.tileSize * 2);
		atkDown2 = setUp("/player/characterAD2", gp.tileSize, gp.tileSize * 2);
		atkLeft1 = setUp("/player/characterAL1", gp.tileSize * 2, gp.tileSize);
		atkLeft2 = setUp("/player/characterAL2", gp.tileSize * 2, gp.tileSize);
		atkRight1 = setUp("/player/characterAR1", gp.tileSize * 2, gp.tileSize);
		atkRight2 = setUp("/player/characterAR2", gp.tileSize * 2, gp.tileSize);

	}
	
	public void update() {
		setSPECIAL();
		getAttack();
		getDefense();
			if(atk == true && inventory != null) {
				attacking();
			}
			else {
			if(keyH.upPressed == true) {
				worldY -= speed;
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				worldY += speed;
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				worldX -= speed;
				direction = "left";
			}
			else if(keyH.rightPressed == true){
				worldX += speed;
				direction = "right";
			}
			if(keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false) {
				if(direction.equals("up")) {
					direction = "upS";
				}
				else if(direction.equals("down")) {
					direction = "downS";
				}
				else if(direction.equals("left")) {
					direction = "leftS";
				}
				else if(direction.equals("right")) {
					direction = "rightS";
				}
		}
		
		collisionOn = false;
		gp.cCheck.checkTile(this);
		int objIndex = gp.cCheck.checkObject(this, true);
		pickUpObj(objIndex);
		int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
		touchMonster(monsterIndex);
		cheats();
		gp.eHandler.checkEvent();
		
		if(collisionOn == true && keyH.enterPressed == false) {
			
			switch(direction) {
			case "up": worldY += speed; break;
			case "down": worldY -= speed; break;
			case "left": worldX += speed; break;
			case "right": worldX -= speed; break;
			}
		}
		gp.keyH.enterPressed = false;
		
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
		if(projectile != null) {
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotCounter == 30 && projectile.haveMana(this) == true) {
			projectile.set(worldX, worldY, direction, true, this);
			projectile.subtractMana(this);
			gp.projectileList.add(projectile);
			shotCounter = 0;
			gp.playSE(6);
			manaRefresh = 0;
		}
		}
		
		if(invincible == true) {
			invincibleCnt++;
			if(invincibleCnt > 60) {
				invincible = false;
				invincibleCnt = 0;
			}
		}
		if(shotCounter < 30) {
			shotCounter++;
		}
		if(manaRefresh < 180) {
			manaRefresh++;
		}
		if(manaRefresh >= 180 && mana != maxMana) {
			mana++;
			manaRefresh = 0;
		}
	}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
		}
	}
	
	public void pickUpObj(int i) {
		if(i != 999) {
			String text = "";
			if(inventory.size() != inventorySize) {
				String objectName = gp.obj[gp.currentMap][i].name;
				switch(objectName) {
				case "Rusty Key":
					keyCnt++;
					gp.playSE(1);
					inventory.add(gp.obj[gp.currentMap][i]);
					text = "Picked up a Rusty Key";
					gp.obj[0][i] = null;
					break;
				case "chest":
					if(keyCnt > 0) {
						gp.obj[gp.currentMap][i].name = "chest_opened";
						gp.obj[gp.currentMap][i].collision = false;
						gp.obj[gp.currentMap][i].down1 = setUp("/objects/chest_opened", gp.tileSize, gp.tileSize);
						gp.playSE(2);	
						keyCnt--;
						text = "Opened up a Chest";
						if(gp.obj[2][i].worldX == 4 * gp.tileSize) {
							gp.ui.currentDialogue = "You found a Cobalt Shield!";
							gp.gameState = gp.dialogueState;
						}
					}
					break;
				case "Axe":
					inventory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(1);
					text = "Picked up a Axe";
					gp.obj[gp.currentMap][i] = null;
					break;
				case "Cobalt Shield":
					inventory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(1);
					text = "Picked up a Cobalt Shield";
					gp.obj[gp.currentMap][i] = null;
					break;
				case "door":
					if(gp.player.keyCnt > 0) {
						gp.obj[gp.currentMap][i] = null;
						text = "Opened a door";
						gp.player.keyCnt--;
					}
					break;
				case "Health Potion":
					inventory.add(gp.obj[gp.currentMap][i]);
					text = "Picked up a Health Potion";
					gp.obj[gp.currentMap][i] = null;
					gp.playSE(1);
					break;
				case "Sword":
					inventory.add(gp.obj[gp.currentMap][i]);
					text = "Picked up a sword";
					gp.obj[gp.currentMap][i] = null;
					gp.playSE(1);
					break;
				case "Shield":
					inventory.add(gp.obj[gp.currentMap][i]);
					text = "Picked up a wooden shield";
					gp.obj[gp.currentMap][i] = null;
					gp.playSE(1);
					break;
				}
			}
			else {
				text = "Inventory is full!";
			}
			gp.ui.addMessage(text);
		}
	}
	
	public void attacking() {
		spriteCounter++;
		if(spriteCounter <= 10) {
			spriteNum = 1;
		}
		if(spriteCounter > 10 && spriteCounter <= 25){
			spriteNum = 2;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			switch(direction) {
			case "up":
				worldY -= atkArea.height;
				break;
			case "down":
				worldY += atkArea.height;
				break;
			case "left":
				worldX -= atkArea.width;
				break;
			case "right":
				worldX += atkArea.width;
				break;
			case "upS":
				worldY -= atkArea.height;
				break;
			case "downS":
				worldY += atkArea.height;
				break;
			case "leftS":
				worldX -= atkArea.width;
				break;
			case "rightS":
				worldX += atkArea.width;
				break;
			}
			solidArea.width = atkArea.width;
			solidArea.height = atkArea.height;
			
			int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
			dmgMonster(monsterIndex, attack);
		}
		if(spriteCounter > 25){
			gp.playSE(4);
			spriteNum = 1;
			spriteCounter = 0;
			atk = false;
		}
	}
	
	public void draw(Graphics2D g2) {
	
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(atk == false || (atk == true && currentWeapon == null)) {
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
			}
			if(atk == true && currentWeapon != null) {
				tempScreenY = screenY -gp.tileSize;
				if(spriteNum == 1) {
					image = atkUp1;
				}
				if(spriteNum == 2) {
					image = atkUp2;
				}
			}
			break;
		case "down":
			if(atk == false || (atk == true && currentWeapon == null)) {
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
			}
			if(atk == true && currentWeapon != null) {
				if(spriteNum == 1) {
					image = atkDown1;
				}
				if(spriteNum == 2) {
					image = atkDown2;
				}
			}
			break;
		case "left":
			if(atk == false || (atk == true && currentWeapon == null)) {
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
			}
			if(atk == true && currentWeapon != null) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {
					image = atkLeft1;
				}
				if(spriteNum == 2) {
					image = atkLeft2;
				}
			}
			break;
		case "right":
			if(atk == false || (atk == true && currentWeapon == null)) {
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
			}
			if(atk == true && currentWeapon != null) {
				if(spriteNum == 1) {
					image = atkRight1;
				}
				if(spriteNum == 2) {
					image = atkRight2;
				}
			}
			break;
		case "upS":
			if(atk == false || (atk == true && currentWeapon == null)) {
			image = stillUp;
			}
			if(atk == true && currentWeapon != null) {
				tempScreenY = screenY -gp.tileSize;
				image = atkUp2;
			}
			break;
		case "downS":
			if(atk == false || (atk == true && currentWeapon == null)) {
			image = stillDown;
			}
			if(atk == true && currentWeapon != null) {
				image = atkDown2;
			}
			break;
		case "leftS":
			if(atk == false || (atk == true && currentWeapon == null)) {
			image = stillLeft;
			}
			if(atk == true && currentWeapon != null) {
				tempScreenX = screenX - gp.tileSize;
				image = atkLeft2;
			}
			break;
		case "rightS":
			if(atk == false || (atk == true && currentWeapon == null)) {
			image = stillRight;
			}
			if(atk == true && currentWeapon != null) {
				image = atkRight2;
			}
			break;
		}
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
	
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		
	}
	
	public void interactNPC(int i) {
		if(gp.keyH.enterPressed == true) {

		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
				if(gp.ui.dialogueIndex == 3) {
					gp.sellFireball();
				}
			}
		}
		else {
			if(gp.keyH.enterPressed == true) {
				gp.playSE(4);
				atk = true;
			}
		}
		gp.keyH.enterPressed = false;
		}
	}

	public void touchMonster(int i) {
		if(i != 999) {
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				gp.playSE(5);
				invincible = true;
			}
		}
	}
	
	public void dmgMonster(int i, int attack) {
		if(i != 999) {
			if(gp.monster[gp.currentMap][i].invincible == false) {
				gp.playSE(3);
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.monster[gp.currentMap][i].invincible = true;
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.monster[gp.currentMap][i].alive = false;
					gp.ui.addMessage("Gained " + gp.monster[gp.currentMap][i].exp + " XP");
					gp.ui.addMessage("Gained " + gp.monster[gp.currentMap][i].coin + " coin(s)");
					int roll = new Random().nextInt(100) + 1;
					if(roll < 10 && (gp.monster[gp.currentMap][i].name.equals("Green Slime") || gp.monster[gp.currentMap][i].name.equals("Red Slime"))) {
						gp.ui.addMessage("The monster dropped a health potion!");
						inventory.add(new PotionR_Obj(gp));
					}
					if(roll > 10 && roll < 15 && gp.monster[gp.currentMap][i].name.equals("Red Slime")) {
						gp.ui.addMessage("The monster dropped a rusty key!");
						inventory.add(new Key_Obj(gp));
					}
					if(roll > 15 && roll < 20 && gp.monster[gp.currentMap][i].name.equals("Red Slime")) {
						gp.ui.addMessage("The monster dropped a axe!");
						inventory.add(new Axe_Obj(gp));
					}
					exp += gp.monster[gp.currentMap][i].exp;
					coin += gp.monster[gp.currentMap][i].coin;
					checkLvlUp();
				}
			}
		}
	}
	
	public void checkLvlUp() {
		if(exp >= nextLvlXp) {
			level++;
			nextLvlXp = nextLvlXp * 2;
			if(level % 3 == 0) {
			maxLife += 2;
			}
			maxSPECIAL++;
			attack = getAttack();
			defense = getDefense();
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Leveled Up To " + level + "!";
			gp.gameState = gp.statState;
		}
	}
}
