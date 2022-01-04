package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	private int speed = 3;
	
	public int getSpeed() {
		return speed;
	}

	public void increaseSpeed() {
		speed++;
	}
	
	public void resetSpeed() {
		speed = 3;
	}
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemies() {
		
		object.removeIf(x -> x.getId() != ID.Player);
	}
	public void clear() {
		
		object.clear();
	}
}
