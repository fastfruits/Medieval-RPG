package main;

import object.Door_Obj;
import object.Key_Obj;
import object.PotionR_Obj;
import object.Shield2_Obj;
import object.Shield_Obj;
import object.Weapon_Obj;
import entities.NPC_Oldman;
import monster.GreenSlime;
import monster.Orc;
import monster.RedSlime;
import object.Axe_Obj;
import object.Chest_Obj;

public class AssetSetter {

	GamePanel gp;
	
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		gp.obj[mapNum][0] = new Key_Obj(gp);
		gp.obj[mapNum][0].worldX = 7 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 9 * gp.tileSize;
		
		gp.obj[mapNum][1] = new Key_Obj(gp);
		gp.obj[mapNum][1].worldX = 22 * gp.tileSize;
		gp.obj[mapNum][1].worldY = 15 * gp.tileSize;
		
		gp.obj[mapNum][2] = new Door_Obj(gp);
		gp.obj[mapNum][2].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 12 * gp.tileSize;
		
		gp.obj[mapNum][3] = new Chest_Obj(gp);
		gp.obj[mapNum][3].worldX = 45 * gp.tileSize;
		gp.obj[mapNum][3].worldY = 3 * gp.tileSize;
		
		gp.obj[mapNum][4] = new Chest_Obj(gp);
		gp.obj[mapNum][4].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][4].worldY = 40 * gp.tileSize;
	
		gp.obj[mapNum][5] = new Key_Obj(gp);
		gp.obj[mapNum][5].worldX = 26 * gp.tileSize;
		gp.obj[mapNum][5].worldY = 19 * gp.tileSize;
		
		gp.obj[mapNum][6] = new Axe_Obj(gp);
		gp.obj[mapNum][6].worldX = 46 * gp.tileSize;
		gp.obj[mapNum][6].worldY = 5 * gp.tileSize;
		
		gp.obj[mapNum][7] = new Shield2_Obj(gp);
		gp.obj[mapNum][7].worldX = 6 * gp.tileSize;
		gp.obj[mapNum][7].worldY = 25 * gp.tileSize;
		
		gp.obj[mapNum][8] = new PotionR_Obj(gp);
		gp.obj[mapNum][8].worldX = 6 * gp.tileSize;
		gp.obj[mapNum][8].worldY = 27 * gp.tileSize;
		
		mapNum = 2;
		
		gp.obj[mapNum][9] = new Weapon_Obj(gp);
		gp.obj[mapNum][9].worldX = 6 * gp.tileSize;
		gp.obj[mapNum][9].worldY = 41 * gp.tileSize;
		
		gp.obj[mapNum][10] = new Shield_Obj(gp);
		gp.obj[mapNum][10].worldX = 5 * gp.tileSize;
		gp.obj[mapNum][10].worldY = 42 * gp.tileSize;
		
		gp.obj[mapNum][11] = new Chest_Obj(gp);
		gp.obj[mapNum][11].worldX = 4 * gp.tileSize;
		gp.obj[mapNum][11].worldY = 3 * gp.tileSize;
	}
	
	public void setNPC() {
		int mapNum = 2;
		gp.npc[mapNum][0] = new NPC_Oldman(gp);
		gp.npc[mapNum][0].worldX = 14 * gp.tileSize;
		gp.npc[mapNum][0].worldY = 38 * gp.tileSize;
		
	}
	
	public void setMonster() {
		int mapNum = 0;
		gp.monster[mapNum][0] = new GreenSlime(gp);
		gp.monster[mapNum][0].worldX = 4 * gp.tileSize;
		gp.monster[mapNum][0].worldY = 37 * gp.tileSize;
		
		gp.monster[mapNum][1] = new GreenSlime(gp);
		gp.monster[mapNum][1].worldX = 44 * gp.tileSize;
		gp.monster[mapNum][1].worldY = 5 * gp.tileSize;
		
		gp.monster[mapNum][2] = new GreenSlime(gp);
		gp.monster[mapNum][2].worldX = 44 * gp.tileSize;
		gp.monster[mapNum][2].worldY = 6 * gp.tileSize;
		
		mapNum = 2;
		gp.monster[mapNum][3] = new GreenSlime(gp);
		gp.monster[mapNum][3].worldX = 4 * gp.tileSize;
		gp.monster[mapNum][3].worldY = 34 * gp.tileSize;
		
		gp.monster[mapNum][4] = new GreenSlime(gp);
		gp.monster[mapNum][4].worldX = 3 * gp.tileSize;
		gp.monster[mapNum][4].worldY = 36 * gp.tileSize;
		
		gp.monster[mapNum][5] = new GreenSlime(gp);
		gp.monster[mapNum][5].worldX = 5 * gp.tileSize;
		gp.monster[mapNum][5].worldY = 34 * gp.tileSize;
		
		gp.monster[mapNum][6] = new Orc(gp);
		gp.monster[mapNum][6].worldX = 42 * gp.tileSize;
		gp.monster[mapNum][6].worldY = 4 * gp.tileSize;
		
		gp.monster[mapNum][7] = new RedSlime(gp);
		gp.monster[mapNum][7].worldX = 25 * gp.tileSize;
		gp.monster[mapNum][7].worldY = 34 * gp.tileSize;
		
		gp.monster[mapNum][8] = new RedSlime(gp);
		gp.monster[mapNum][8].worldX = 27 * gp.tileSize;
		gp.monster[mapNum][8].worldY = 32 * gp.tileSize;
		
		gp.monster[mapNum][9] = new RedSlime(gp);
		gp.monster[mapNum][9].worldX = 31 * gp.tileSize;
		gp.monster[mapNum][9].worldY = 30 * gp.tileSize;
		
		gp.monster[mapNum][10] = new RedSlime(gp);
		gp.monster[mapNum][10].worldX = 36 * gp.tileSize;
		gp.monster[mapNum][10].worldY = 24 * gp.tileSize;
	}
	
}
