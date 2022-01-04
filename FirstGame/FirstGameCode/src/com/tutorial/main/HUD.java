package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float HEALTH = 100;
	
	private int bounds = 0;	
	private float greenValue = 255;
	private int score = 0;
	private int normalHighScore = 0;
	private int hardHighScore = 0;
		
	public int getNormalHighScore() {
		return normalHighScore;
	}

	public void setNormalHighScore(int normalhighScore) {
		normalHighScore = normalhighScore;
	}

	public int getHardHighScore() {
		return hardHighScore;
	}

	public void setHardHighScore(int hardhighScore) {
		hardHighScore = hardhighScore;
	}

	private int level = 1;
	
	
	public boolean maxHealth() {
		return bounds == 400;
	}
	
	public void increaseMaxHealth() {
		bounds = (int) Game.clamp(bounds+40, 0, 400);
		HEALTH+=20;
	}
	
	public void refillHealth() {		
		HEALTH = Game.clamp(HEALTH+(((100 + (bounds/2))/2)), 0, 100 + (bounds/2));
	}
	
	public void tick() {		
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
		greenValue = HEALTH*255/100;
		greenValue = Game.clamp(greenValue, 0, 255);		
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		g.drawString((int)HEALTH+" / " + (100 + (bounds / 2)), 15, 15);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Space for Shop", 15, 94);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void reset() {
		setLevel(1);
		setScore(0);
		HEALTH = 100;
		bounds = 0;
	}
	
}
