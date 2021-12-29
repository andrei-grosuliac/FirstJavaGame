package com.tutorial.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r;
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 200) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			r = new Random();
			if(hud.getLevel() == 2) {
				new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
			}
			if(hud.getLevel() == 3) {
				new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
			}
		}
	}
}
