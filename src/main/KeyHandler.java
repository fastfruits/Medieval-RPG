package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, sCheat, cCheat, kCheat, shotKeyPressed;
	UI ui;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				if(gp.ui.cmdNum <= 0) {
					
				}
				else {
				gp.ui.cmdNum--;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				if(gp.ui.cmdNum >= 3) {
					
				}
				else {
				gp.ui.cmdNum++;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.cmdNum == 0) {
					gp.gameState = gp.statState;
				}
				if(gp.ui.cmdNum == 1) {
					
				}
				if(gp.ui.cmdNum == 2) {
					gp.gameState = gp.optionState;
				}
				if(gp.ui.cmdNum == 3) {
					System.exit(0);
				}
			}	
		}
		else if(gp.gameState == gp.statState) {
			int maxCmdNum = 7;
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.cmdNum--;
				if(gp.ui.cmdNum < 0) {
					gp.ui.cmdNum++;
				}

			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.cmdNum++;
				if(gp.ui.cmdNum > maxCmdNum) {
					gp.ui.cmdNum--;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.cmdNum == 7) {
					if(gp.player.maxSPECIAL == 10) {
						gp.ui.currentDialogue = "You wake up dazed and confused next to a wagon crash unsure \nof how you got here.";
						gp.gameState = gp.dialogueState;
					}
					else {
						gp.gameState = gp.playState;
					}
				}
			}
			if(code == KeyEvent.VK_A) {
					if(gp.ui.cmdNum == 0 && gp.player.S > 0) {
						gp.player.S--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 1 && gp.player.P > 0) {
						gp.player.P--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 2 && gp.player.E > 0) {
						gp.player.E--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 3 && gp.player.C > 0) {
						gp.player.C--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 4 && gp.player.I > 0) {
						gp.player.I--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 5 && gp.player.A > 0) {
						gp.player.A--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 6 && gp.player.L > 0) {
						gp.player.L--;
						gp.player.numSPECIAL--;
						gp.playSE(2);
					}
			}
			if(code == KeyEvent.VK_D) {
				if(gp.ui.cmdNum == 0 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.S++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 1 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.P++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 2 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.E++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 3 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.C++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 4 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.I++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 5 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.A++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
				if(gp.ui.cmdNum == 6 && gp.player.maxSPECIAL > gp.player.numSPECIAL) {
					gp.player.L++;
					gp.player.numSPECIAL++;
					gp.playSE(2);
				}
			}
		}
		else if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_BACK_SLASH) {
				sCheat = true;
			}
			if(code == KeyEvent.VK_2) {
				cCheat = true;
			}
			if(code == KeyEvent.VK_1) {
				kCheat = true;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.optionState;
			}	
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if(code == KeyEvent.VK_Z) {
				gp.gameState = gp.statusState;
			}
			if(code == KeyEvent.VK_R) {
				switch(gp.currentMap) {
				case 0:
					gp.tileM.loadMap("/maps/map-2-2.txt", 0);
					break;
				case 1:
					gp.tileM.loadMap("/maps/interior.txt", 1);
					break;
				case 2:
					gp.tileM.loadMap("/maps/forest.txt", 2);
					break;
				}
			}
			if(code == KeyEvent.VK_F) {
				shotKeyPressed = true;
				
			}
		}
		else if(gp.gameState == gp.optionState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			
			int maxCmdNum = 0;
			switch(gp.ui.subState) {
			case 0: maxCmdNum = 5;
				break;
			case 3: maxCmdNum = 1;
				break;
			}
			
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.cmdNum--;
				if(gp.ui.cmdNum < 0) {
					gp.ui.cmdNum++;
				}

			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.cmdNum++;
				if(gp.ui.cmdNum > maxCmdNum) {
					gp.ui.cmdNum--;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				if(gp.ui.subState == 0) {
					if(gp.ui.cmdNum == 1 && gp.music.volumeScale > 0) {
						gp.music.volumeScale--;
						gp.music.checkVolume();
						gp.playSE(2);
					}
					if(gp.ui.cmdNum == 2 && gp.se.volumeScale > 0) {
						gp.se.volumeScale--;
						gp.playSE(2);
					}
				}
			}
			if(code == KeyEvent.VK_D) {
				if(gp.ui.subState == 0) {
					if(gp.ui.cmdNum == 1 && gp.music.volumeScale < 5) {
						gp.music.volumeScale++;
						gp.music.checkVolume();
						gp.playSE(2);
					}
				}
				if(gp.ui.subState == 0) {
					if(gp.ui.cmdNum == 2 && gp.se.volumeScale < 5) {
						gp.se.volumeScale++;
						gp.playSE(2);
					}
				}
			}
		}
		
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		else if(gp.gameState == gp.statusState) {
			if(code == KeyEvent.VK_Z) {
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				if(gp.ui.slotRow != 0) {
					gp.ui.slotRow--;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				if(gp.ui.slotRow != 3) {
					gp.ui.slotRow++;
				}
			}
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				if(gp.ui.slotCol != 0) {
					gp.ui.slotCol--;
				}
			}
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				if(gp.ui.slotCol != 4) {
					gp.ui.slotCol++;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				gp.player.selectItem();
			}
		}
		else if(gp.gameState == gp.gameOverState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.cmdNum--;
				if(gp.ui.cmdNum < 0) {
					gp.ui.cmdNum = 0;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.cmdNum++;
				if(gp.ui.cmdNum > 1) {
					gp.ui.cmdNum = 1;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.cmdNum == 0) {
					gp.gameState = gp.playState;
					gp.retry();
				}
				else if(gp.ui.cmdNum == 1) {
					gp.gameState = gp.titleState;
					gp.restart();
				}
			}
		}
		else if(gp.gameState == gp.winState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.cmdNum--;
				if(gp.ui.cmdNum < 0) {
					gp.ui.cmdNum = 0;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.cmdNum++;
				if(gp.ui.cmdNum > 1) {
					gp.ui.cmdNum = 1;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.cmdNum == 0) {
					gp.gameState = gp.playState;
					gp.retry();
				}
				else if(gp.ui.cmdNum == 1) {
					gp.gameState = gp.titleState;
					gp.restart();
				}
			}
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
	}

}
