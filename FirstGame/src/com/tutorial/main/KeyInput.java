package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;	
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;
	
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
					tempObject.setVelY(-5);
				}
				if(key == KeyEvent.VK_S) {
					dP=true; 
					tempObject.setVelY(5);
				}
				if(key == KeyEvent.VK_D) {
					rP=true; 
					tempObject.setVelX(5);
				}
				if(key == KeyEvent.VK_A) {
					lP=true; 
					tempObject.setVelX(-5);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
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
						tempObject.setVelY(5);
					}
					else{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_S) {
					dP=false;
					if(uP) {
						tempObject.setVelY(-5);
					}
					else{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_D) {
					rP=false;
					if(lP) {
						tempObject.setVelX(-5);
					}
					else{
						tempObject.setVelX(0);
					}
				}
				if(key == KeyEvent.VK_A) {
					lP=false;
					if(rP) {
						tempObject.setVelX(5);
					}
					else{
						tempObject.setVelX(0);
					}
				}
			}
		}
	}
	
}
