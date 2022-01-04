package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	private int timer = 60;
	private int timer2 = 50;

	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		velX=0;
		velY=2;
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(timer <=0) velY = 0;
		else timer--;
		
		if(timer<=0) timer2--;
		if(timer2<=0) {
			if(velX == 0) velX = 2;
			int spawn = 0 ;
			if(Game.diff == 0) {
				spawn = r.nextInt(5);
			}
			else {
				spawn = r.nextInt(4);
			}
			
			if(spawn==0) handler.addObject(new EnemyBossBullet(x+48, y+48, ID.BasicEnemy, handler));
		}
//		if(y <=0 || y>= Game.HEIGHT - 50) {
//			velY *= -1;
//		}
//		
		if(x <=0 || x>= Game.WIDTH - 96) {
			velX *= -1;
		}
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.5f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, 96, 96);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

}
