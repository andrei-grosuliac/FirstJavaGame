package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private Shop shop;
	
	
	public Menu(Handler handler, HUD hud, Shop shop) {
		this.handler = handler;
		this.hud = hud;
		this.shop = shop;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		AudioPlayer.getSound("menu_sound").play();
		// Menu
		if(Game.gameState == STATE.Menu) {	
			//play button
			if (mouseOver(mx, my, 210, 100, 200, 64)) {	
				Game.gameState = STATE.Select;						
				return;
			}
			
			//help button
			if (mouseOver(mx, my, 210, 200, 200, 64)) {
				Game.gameState = STATE.Help;
			}		
			
			//quit button
			if (mouseOver(mx, my, 210, 300, 200, 64)) {
				System.exit(0);
			}
		}	
		
		//Help Menu
		if(Game.gameState == STATE.Help) {
			//back button
			if (mouseOver(mx, my, 210, 300, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		//Game Over
		if(Game.gameState == STATE.End || Game.gameState == STATE.Victory) {
			
			//try again
			if (mouseOver(mx, my, 210, 200, 200, 64)) {
				initializeGame();
				return;
			}
			
			//back to menu
			if (mouseOver(mx, my, 210, 300, 200, 64)) {
				renderMenuParticles();
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		
		if(Game.gameState == STATE.Select) {
			
			//normal button
			if (mouseOver(mx, my, 210, 100, 200, 64)) {	
				initializeGame();
				Game.diff = 0;
				
			}
			
			//hard button
			if (mouseOver(mx, my, 210, 200, 200, 64)) {
				initializeGame();
				Game.diff = 1;
			}		
			
			//back button
			if (mouseOver(mx, my, 210, 300, 200, 64)) {
				Game.gameState = STATE.Menu;
			}
		}	
	}
	
	private void initializeGame() {
		Game.gameState = STATE.Game;
		new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler);
		handler.clearEnemies();
		new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
		hud.reset();
		handler.resetSpeed();
		KeyInput.resetKeys();
		shop.resetCosts();
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public static boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		return mx > x && mx < x+width && my > y && my < y + height; 
			
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt3);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Use WASD keys to move player and dodge enemies", 70, 150);
			g.drawString("Press Space to access shop and upgrade", 110, 200);
			g.drawString("Press P to pause the game", 180, 250);
			
			g.setFont(fnt2);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Back", 280, 340);
		}
		
		else if(Game.gameState == STATE.Menu){
						
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 250, 70);
			
			g.setFont(fnt3);
			g.drawString("Current High Score Normal Mode: " + hud.getNormalHighScore(), 150, 395);
			g.drawString("Current High Score Hard Mode: " + hud.getHardHighScore(), 150, 420);
			
			g.setFont(fnt2);
			g.drawRect(210, 100, 200, 64);
			g.drawString("Play", 280, 140);
			
			g.drawRect(210, 200, 200, 64);
			g.drawString("Help", 280, 240);
			
			g.drawRect(210, 300, 200, 64);
			g.drawString("Quit", 280, 340);
		}
		else if(Game.gameState == STATE.End){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 170, 70);
			
			g.setFont(fnt3);			
			g.drawString("You lost with a score of: " + hud.getScore(), 170, 150);
			
			g.setFont(fnt2);
			g.drawRect(210, 200, 200, 64);
			g.drawString("Try Again", 250, 240);
			
			g.setFont(fnt2);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Menu", 270, 340);
			
		}
		else if(Game.gameState == STATE.Select){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 130, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 100, 200, 64);
			g.drawString("Normal", 260, 140);
			
			g.drawRect(210, 200, 200, 64);
			g.drawString("Hard", 280, 240);
			
			g.drawRect(210, 300, 200, 64);
			g.drawString("Back", 280, 340);
		}
		else if(Game.gameState == STATE.Victory){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Victory", 215, 70);
			
			g.setFont(fnt3);			
			g.drawString("You won with a score of: " + hud.getScore(), 170, 150);
			
			g.setFont(fnt2);
			g.drawRect(210, 200, 200, 64);
			g.drawString("Try Again", 250, 240);
			
			g.setFont(fnt2);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Menu", 270, 340);
			
		}
		
	}
	
	public void renderMenuParticles() {
		for (int i = 0; i < 15; i++) {
			new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler);
		}
	}
	
}
