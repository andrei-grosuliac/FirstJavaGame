package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {
	
	private Handler handler;
	private Random r = new Random();

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		velX=5;
		velY=5;
	}

	public void tick() {
		x+=velX;
		y+=velY;
		if(Game.diff==0) {
			if(y <=0 || y>= Game.HEIGHT - 50) {
				velY *= -1;
			}
			
			if(x <=0 || x>= Game.WIDTH - 32) {
				velX *= -1;
			}
		}
		else {
			if(y <=0 || y>= Game.HEIGHT - 50) {
				if(velY<=0) {
					velY = (r.nextInt(7)+3);
				}
				else {
					velY = -(r.nextInt(7)+3);
				}
			}
			
			if(x <=0 || x>= Game.WIDTH - 32) {
				if(velX<=0) {
					velX = (r.nextInt(7)+3);
				}
				else {
					velX = -(r.nextInt(7)+3);
				}
			}
		}
		Color color = Color.red;
		if(Game.diff == 1) {
			color = Color.yellow;
		}
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
