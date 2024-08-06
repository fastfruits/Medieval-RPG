package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import object.Coin_Obj;
import object.Key_Obj;
import object.Mana_Obj;
import object.Heart_Obj;
import entities.Entity;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	BufferedImage keyI, coinI, heartFull, heartHalf, heartBlank, manaFull, manaBlank;
	public boolean messageOn = false;
	public boolean gameFinished = false;
	double playTime;
	DecimalFormat df = new DecimalFormat("#0.00");
	public String currentDialogue = "";
	public int dialogueIndex = 0;
	public int cmdNum = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCnt = new ArrayList<>();
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	
	public UI (GamePanel gp) {
		
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Key_Obj key = new Key_Obj(gp);
		keyI = key.image;
		Coin_Obj coin = new Coin_Obj(gp);
		coinI = coin.image;
		
		Entity heart = new Heart_Obj(gp);
		heartFull = heart.image3;
		heartHalf = heart.image2;
		heartBlank = heart.image;
		Entity crystal = new Mana_Obj(gp);
		manaFull = crystal.image;
		manaBlank = crystal.image2;
	}
	
	public void draw(Graphics2D g2) {

		this.g2 = g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.WHITE);
		
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState == gp.statState) {
			drawSPECIAL();
			System.out.println("Special");
		}
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawPlayerMana();
			drawMessage();
			drawTimer();
		}
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
			drawPlayerLife();
			drawPlayerMana();
		}
		if(gp.gameState == gp.dialogueState) {
			showDialogue();
		}
		if(gp.gameState == gp.statusState) {
			drawPStats();
			drawInventory();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOver();
		}
		if(gp.gameState == gp.winState) {
			drawGameWin();
		}
	}
	
	public void drawGameWin() {
		g2.setColor(new Color(0, 0, 0, 200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		text = "GAME WIN!";
		x = getXCenter(text);
		y = gp.tileSize * 3;
		g2.setColor(new Color(255,255,255));
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(new Color(219,200,22));
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(25F));
		y += gp.tileSize;
		text = "" + df.format(gp.timerN);
		x = getXCenter(text);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "Retry";
		x = getXCenter(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if(cmdNum == 0) {
			g2.drawString(">", x - 25, y);
		}
		
		text = "Menu";
		x = getXCenter(text);
		y += 55;
		g2.drawString(text, x, y);
		if(cmdNum == 1) {
			g2.drawString(">", x - 25, y);
		}
	}
	
	public void drawTimer() {
		g2.setColor(Color.WHITE);
		g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		g2.drawString(""+ df.format(gp.timerN), gp.screenWidth / 2 - 10, gp.tileSize / 2);
	}
	
	public void drawSPECIAL() {
		g2.setColor(Color.WHITE);
		g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameW = gp.tileSize * 8;
		int frameH = gp.tileSize * 10;
		int tailX = (frameX + frameW) - 15;

		
		drawSubWindow(frameX,frameY,frameW,frameH);
		String text = "S.P.E.C.I.A.L Stats";
		int textX = getXCenter(text);
		int textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		text = "Strength";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.S, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 0) {
			g2.drawString(">", textX - 15, textY);
		}
		
		
		textY += gp.tileSize;
		text = "Perception(WIP)";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.P, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 1) {
			g2.drawString(">", textX - 15, textY);
		}

		textY += gp.tileSize;
		text = "Endurance";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.E, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 2) {
			g2.drawString(">", textX - 15, textY);
		}

		textY += gp.tileSize;
		text = "Charisma";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.C, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 3) {
			g2.drawString(">", textX - 15, textY);
		}

		textY += gp.tileSize;
		text = "Intelligence";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.I, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 4) {
			g2.drawString(">", textX - 15, textY);
		}

		textY += gp.tileSize;
		text = "Agility";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.A, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 5) {
			g2.drawString(">", textX - 15, textY);
		}

		textY += gp.tileSize;
		text = "Luck";
		g2.drawString(text, textX, textY);
		g2.drawString("" + gp.player.L, getXAlignmentText("0", tailX), textY);
		if(cmdNum == 6) {
			g2.drawString(">", textX - 15, textY);
		}
		
		textY += gp.tileSize + 20;
		text = "Confirm";
		textX = getXCenter(text);
		g2.drawString(text, textX, textY);
		if(cmdNum == 7) {
			g2.drawString(">", textX - 25, textY);
		}
	}
	
	public void drawGameOver() {
		g2.setColor(new Color(0, 0, 0, 200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		text = "GAME OVER";
		x = getXCenter(text);
		y = gp.tileSize * 3;
		g2.setColor(new Color(255,255,255));
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(new Color(219,200,22));
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "Retry";
		x = getXCenter(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if(cmdNum == 0) {
			g2.drawString(">", x - 25, y);
		}
		
		text = "Menu";
		x = getXCenter(text);
		y += 55;
		g2.drawString(text, x, y);
		if(cmdNum == 1) {
			g2.drawString(">", x - 25, y);
		}
		
	
	}
	
	public void drawPlayerMana() {
		int x = gp.tileSize / 6;
		int y = gp.tileSize + 5;
		int i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(manaBlank, x, y,null);
			i++;
			x += 30;
		}
		x = gp.tileSize / 6;
		y = gp.tileSize + 5;
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(manaFull, x, y, null);
			i++;
			x += 30;
		}
	}

	public void drawInventory() {
		Color yel = new Color(219,200,22);
		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameW = gp.tileSize * 6;
		int frameH = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameW, frameH);
		
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(0,46,173));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			if(gp.player.inventory.get(i) != null) {
				g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			}
			slotX += gp.tileSize;
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXStart;
				slotY += gp.tileSize;
			}
		}
		
		int cursorX = slotXStart + (gp.tileSize * slotCol);
		int cursorY = slotYStart + (gp.tileSize * slotRow);
		int cursorW = gp.tileSize;
		int cursorH = gp.tileSize;
		g2.setColor(yel);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorW, cursorH, 10, 10);
		
		int dFrameX = frameX;
		int dFrameY = frameY + frameH;
		int dFrameW = frameW;
		int dFrameH = gp.tileSize * 3;
		
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(24F));
		
		int itemIndex = getItemIndex();
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameW, dFrameH);
			if(gp.player.inventory.get(0) != null) {
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
			}
		}
		
	}
	
	public int getItemIndex() {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize - 20;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22F));
		
		for(int i = 0; i < message.size(); i++) {
			 if(message.get(i) != null) {
				 g2.setColor(Color.BLACK);
				 g2.drawString(message.get(i), messageX + 2, messageY + 2);
				 g2.setColor(Color.WHITE);
				 g2.drawString(message.get(i), messageX, messageY);
				 int counter = messageCnt.get(i) + 1;
				 messageCnt.set(i, counter);
				 messageY += 25;
				 if(messageCnt.get(i) > 180) {
					 message.remove(i);
					 messageCnt.remove(i);
				 }
			 }
		}
		
	}
	
	public void drawPStats() {
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameW = gp.tileSize * 5;
		final int frameH = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameW, frameH);
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		g2.drawString("Life", textX, textY);
		g2.drawString("Mana", textX, textY += lineHeight);
		g2.drawString("Level", textX, textY += lineHeight);
		g2.drawString("EXP", textX, textY += lineHeight);
		g2.drawString("Next Level", textX, textY += lineHeight);
		g2.drawString("Strength", textX, textY += lineHeight);
		g2.drawString("Attack", textX, textY += lineHeight);
		g2.drawString("Fortitude", textX, textY += lineHeight);
		g2.drawString("Defense", textX, textY += lineHeight);
		g2.drawString("Coins", textX, textY += lineHeight);
		g2.drawString("Weapon", textX, textY += lineHeight + 15);
		g2.drawString("Shield", textX, textY += lineHeight + 15);
		
		int tailX = (frameX + frameW) - 15;
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.level);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.exp);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.nextLvlXp);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.strength);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.attack);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.fortitude);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.defense);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.coin);
		textX = getXAlignmentText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += 48;
		if(gp.player.currentWeapon != null) {
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 32, null);
		}
		textY += 48;
		if(gp.player.currentShield != null) {
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 32, null);
		}
	}
	
	public void drawTitleScreen() {
		Color back = new Color(0,46,173);
		Color txt = new Color(219,200,22);
		gp.setBackground(back);
		g2.setColor(back);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Medieval RPG";
		int x = getXCenter(text);
		int y = gp.tileSize * 3;
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(txt);
		g2.drawString(text, x, y);
		
		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 1.5;
		g2.drawImage(gp.player.stillDown, x, y, gp.tileSize * 2, gp.tileSize * 2,null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
		text = "New Game";
		x = getXCenter(text);
		y += gp.tileSize * 3;
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(txt);
		g2.drawString(text, x, y);
		if(cmdNum == 0) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
		
		text = "Load Game";
		x = getXCenter(text);
		y += gp.tileSize;
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(txt);
		g2.drawString(text, x, y);
		if(cmdNum == 1) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
		
		text = "Options";
		x = getXCenter(text);
		y += gp.tileSize;
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(txt);
		g2.drawString(text, x, y);
		if(cmdNum == 2) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
		
		text = "Quit";
		x = getXCenter(text);
		y += gp.tileSize;
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(txt);
		g2.drawString(text, x, y);
		if(cmdNum == 3) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
	}
	
	public void showControls(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		String text = "Controls";
		textX = getXCenter(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Interact/Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Cast Magic", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		
		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Enter", textX, textY);
		textY += gp.tileSize;
		g2.drawString("F", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Z", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += gp.tileSize;
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
			if(cmdNum == 0) {
				g2.drawString(">", textX - 25, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 0;
				}
			}
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize / 5;
		int y = gp.tileSize / 5;
		int i = 0;
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heartBlank, x, y,null);
			i++;
			x+= gp.tileSize;
		}
		x = gp.tileSize / 5;
		y = gp.tileSize / 5;
		i = 0;
		while(i < gp.player.life && i < gp.player.maxLife) {
			g2.drawImage(heartHalf, x, y,null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heartFull, x, y,null);
			}
			i++;
			x += gp.tileSize;
		}
	}
	
	public void showDialogue() {
		int x = gp.tileSize * 2;
		int y = gp.tileSize;
		int width = gp.screenWidth - gp.tileSize * 4;
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);
		
		x += gp.tileSize - 20;
		y += gp.tileSize - 12;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 180);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		g2.setStroke(new BasicStroke(5));
		g2.setColor(new Color(219,200,22));

		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 35, 35);
	}
	
	public void drawOptionScreen() {
		g2.setColor(Color.WHITE);
		g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameW = gp.tileSize * 8;
		int frameH = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameW, frameH);
		
		switch(subState) {
		case 0: 
			optionsTop(frameX, frameY);
			break;
		case 1: 
			optionsFullscreenNotif(frameX, frameY);
			break;
		case 2:
			showControls(frameX, frameY);
			break;
		case 3:
			quitConfirm(frameX, frameY);
			break;
		}
		
		gp.keyH.enterPressed = false;
	}
	
	public void quitConfirm(int frameX, int frameY){
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 2;
		
		currentDialogue = "Quit game to title screen?";
		g2.drawString(currentDialogue, textX, textY);
		String text = "Yes";
		textX = getXCenter(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if(cmdNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		text = "No";
		textX = getXCenter(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(cmdNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				cmdNum = 0;
			}
		}
	}
	
	public void optionsFullscreenNotif(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize;
		
		currentDialogue = ("This change requires a \nrestart of the game.");
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		textY = frameY +  gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
		if(cmdNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	
	public void optionsTop(int frameX, int frameY) {
		String text = "Options";
		int textX = getXCenter(text);
		int textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Full Screen", textX, textY);
		if(cmdNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullscreen == false) {
					gp.fullscreen = true;
				}
				else {
					gp.fullscreen = false;
				}
				subState = 1;
			}
		}
		textY += gp.tileSize;

		g2.drawString("Music", textX, textY);
		if(cmdNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		textY += gp.tileSize;

		g2.drawString("Sound Effects", textX, textY);
		if(cmdNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		textY += gp.tileSize;
		
		g2.drawString("Controls", textX, textY);
		if(cmdNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				cmdNum = 0; 
			}
		}
		textY += gp.tileSize;
		
		g2.drawString("Quit Game", textX, textY);
		if(cmdNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				cmdNum = 0;
			}
		}
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if(cmdNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				cmdNum = 0;
			}
		}
		
		textX = frameX + gp.tileSize * 5 ;
		textY = frameY + gp.tileSize * 2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullscreen == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	}
	
	public int getXCenter(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x =gp.screenWidth / 2 - length / 2;
		return x;
	}
	
	public int getXAlignmentText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCnt.add(0);
	}

}
