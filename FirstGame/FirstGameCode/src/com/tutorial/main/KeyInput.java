package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;	
	private static boolean uP = false;
	private static boolean dP = false;
	private static boolean lP = false;
	private static boolean rP = false;
	
	public static void resetKeys() {
		uP = false;
		dP = false;
		lP = false;
		rP = false;
	}
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				
				if(key == KeyEvent.VK_W) {
					uP=true; 
					tempObject.setVelY(-handler.getSpeed());
				}
				if(key == KeyEvent.VK_S) {
					dP=true; 
					tempObject.setVelY(handler.getSpeed());
				}
				if(key == KeyEvent.VK_D) {
					rP=true; 
					tempObject.setVelX(handler.getSpeed());
				}
				if(key == KeyEvent.VK_A) {
					lP=true; 
					tempObject.setVelX(-handler.getSpeed());
				}
			}
		}
		
		if(Game.gameState == STATE.Game) {
			if(key == KeyEvent.VK_P) Game.paused = !Game.paused;
		}
		
		//if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		
		if(!Game.paused && Game.gameState == STATE.Game || Game.gameState == STATE.Shop) {
			if(key == KeyEvent.VK_SPACE) {
				Game.paused = !Game.paused;
				if(Game.gameState == STATE.Game) {				
					Game.gameState = STATE.Shop;
				}
				else if(Game.gameState == STATE.Shop) {
					Game.gameState = STATE.Game;
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				
				if(key == KeyEvent.VK_W) {					
					uP=false;
					if(dP) {
						tempObject.setVelY(handler.getSpeed());
					}
					else{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_S) {
					dP=false;
					if(uP) {
						tempObject.setVelY(-handler.getSpeed());
					}
					else{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_D) {
					rP=false;
					if(lP) {
						tempObject.setVelX(-handler.getSpeed());
					}
					else{
						tempObject.setVelX(0);
					}
				}
				if(key == KeyEvent.VK_A) {
					lP=false;
					if(rP) {
						tempObject.setVelX(handler.getSpeed());
					}
					else{
						tempObject.setVelX(0);
					}
				}
			}
		}
	}
	
}
