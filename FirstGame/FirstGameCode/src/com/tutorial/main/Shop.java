package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter {

	private Handler handler;
	private HUD hud;
	
	private int[] B1 = new int[3];
	private int inc = 100;
	private int start = 500;
	
	public void resetCosts() {
		for (int i = 0; i < B1.length; i++) {
			B1[i] = start;
		}
	}
		
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", 0, 50);
		Font fnt2 = new Font("arial", 0, 40);
		Font fnt3 = new Font("arial", 0, 12);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Shop", 250, 70);
		
		//box 1
		g.setFont(fnt3);
		g.drawRect(100, 100, 100, 80);
		if(!hud.maxHealth()) {
			g.drawString("Upgrade Health", 110, 120);
			g.drawString("Cost: " + B1[0] , 110, 140);
		}
		else {
			g.drawString("Max Health", 110, 120);
			g.drawString("Reached", 110, 140);
		}
		
		
		//box 2
		g.drawRect(250, 100, 100, 80);
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost: " + B1[1] , 260, 140);
				
		//box 3
		g.drawRect(400, 100, 100, 80);
		g.drawString("Refill Health", 410, 120);
		g.drawString("by 50%", 410, 140);
		g.drawString("Cost: " + B1[2] , 410, 160);
		
		g.setFont(fnt2);
		g.drawString("SCORE: " + hud.getScore(), 200, 300);
		
		g.drawString("Press Space To Go Back", 100, 400);
		
	
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		int score = hud.getScore();
				
		if (Game.gameState == STATE.Shop){
			
			//box 1
			if (Menu.mouseOver(mx, my, 100, 100, 100, 80) && !hud.maxHealth()) {	
				if(score >= B1[0]) {				
					hud.setScore(score-B1[0]);
					B1[0]+=inc;
					hud.increaseMaxHealth();
				}
				return;
			}
			
			//box 2
			if (Menu.mouseOver(mx, my, 250, 100, 100, 80)){
				if(score >= B1[1]) {				
					hud.setScore(score-B1[1]);
					B1[1]+=inc;
					handler.increaseSpeed();
				}
				return;
			}
			
			//box 3
			if (Menu.mouseOver(mx, my, 400, 100, 100, 80)) {	
				if(score >= B1[2]) {				
					hud.setScore(score-B1[2]);
					hud.refillHealth();
				}
				return;
			}
		}
		
	}
}
