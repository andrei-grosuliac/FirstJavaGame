package com.tutorial.main;

import java.util.Random;

import com.tutorial.main.Game.STATE;

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
		
		if(scoreKeep >= 500) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			r = new Random();
			
			if(Game.diff == 0) {
				if(hud.getLevel() == 2) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}	
				if(hud.getLevel() == 3) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}
				if(hud.getLevel() == 4) {
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
				}
				if(hud.getLevel() == 5) {
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 6) {
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
				}
				if(hud.getLevel() == 7) {
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 8) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}
				if(hud.getLevel() == 9) {
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 10) {
					handler.clearEnemies();
					new EnemyBoss(Game.WIDTH/2 - 48, -120, ID.EnemyBoss, handler);
				}
				if(hud.getLevel() == 15) {
					handler.clear();
					hud.setNormalHighScore(hud.getScore());
					Game.gameState = STATE.Victory;
				}
			}else {
				if(hud.getLevel() == 2) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}	
				if(hud.getLevel() == 3) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
				}
				if(hud.getLevel() == 4) {
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 5) {
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
				}
				if(hud.getLevel() == 6) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}
				if(hud.getLevel() == 7) {
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
					new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler);
				}
				if(hud.getLevel() == 8) {
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
				}
				if(hud.getLevel() == 9) {
					new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler);
					new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 10) {
					handler.clearEnemies();
					new EnemyBoss(Game.WIDTH/2 - 48, -120, ID.EnemyBoss, handler);
				}
				if(hud.getLevel() == 12) {					
					new EnemyBoss(Game.WIDTH/2 - 48, -120, ID.EnemyBoss, handler);
				}
				if(hud.getLevel() == 15) {
					handler.clear();
					hud.setHardHighScore(hud.getScore());
					Game.gameState = STATE.Victory;
				}
			}			
		}
	}
}
