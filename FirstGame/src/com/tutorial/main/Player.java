package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 48);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		
		collision();
		
	}
   
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
				}
			}
		}
		
	}

	public void render(Graphics g) {
				
		g.setColor(Color.white);		
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	
}
