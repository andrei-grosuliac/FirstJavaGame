package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {
	
	private Handler handler;
	Random r = new Random();

	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		
		if(Game.diff == 0) {
			velX= (r.nextInt(16) -8);
			velY=5;
		}
		else {
			velX= (r.nextInt(20) -10);
			velY=7;
		}
		
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
//		if(y <=0 || y>= Game.HEIGHT - 50) {
//			velY *= -1;
//		}
//		
//		if(x <=0 || x>= Game.WIDTH - 32) {
//			velX *= -1;
//		}
		
		if(y>= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
