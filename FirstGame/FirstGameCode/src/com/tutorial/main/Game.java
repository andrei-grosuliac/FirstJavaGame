package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running  = false;
	
	public static boolean paused = false;
	public static int diff = 0; 
	// 0 = normal
	// 1 = hard	
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	public enum STATE{
		Menu, Game, Help, End, Select, Shop, Victory
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(handler, hud, shop);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();
		
		spawner = new Spawn(handler, hud);
		
		new Window(WIDTH, HEIGHT, "FirstGame", this);			
		
		menu.renderMenuParticles();
//		
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta+= (now- lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			//frames++;
			
			if(System.currentTimeMillis() - timer >1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}		
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		if(paused && gameState == STATE.Game) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 270, 100);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Shop){
			shop.render(g);
		}
		else {
			handler.render(g);
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	private void tick() {		
		
		if(paused) {
			return;
		}
		
		if(gameState == STATE.Game) {			
				handler.tick();
				hud.tick();	
				spawner.tick();
				
				if(HUD.HEALTH <=0) {
					handler.clear();				
					gameState = STATE.End;
				}				
		}else {
			menu.tick();
			handler.tick();
		}		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;		
	}

	public static void main(String[] args) {
		new Game();
	}
}
