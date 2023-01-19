package dev.blue.isoFlerth.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Engine implements Runnable {

	private Thread thread;
	private boolean running;
	private Window window;
	private Game game;
	private double FPS = 120;
	private BufferStrategy bs;
	
	/**
	 *Creates a thread and then sets local variables. This should only ever be instantiated once. 
	 **/
	public Engine(Window window, Game game) {
		this.thread = new Thread(this);
		this.window = window;
		this.game = game;
	}
	
	/**
	 *This method should be called to start the game engine. 
	 **/
	public synchronized void start() {
		thread.start();
		running = true;
		initializeGraphics();
	}
	
	/**
	 *This method should be called to stop the game engine. 
	 **/
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 *This method should never be called externally. It should only ever be referenced by the start() method. 
	 **/
	@Override
	public void run() {
		long lastFrame = System.nanoTime();
		double passed = 0.0D;
		while (this.running) {
			long now = System.nanoTime();
			passed = ((now - lastFrame) / 1000000L);
			if (passed >= 1000.0D/FPS) {
				passed = 0.0D;
				render();
				//System.out.println("render");
				update();
				lastFrame = now;
			}
		}
	}
	
	/**
	 *Forces a proper initialization of the Graphics object. Without this, the lack of a properly rendered first frame 
	 *can cause errors to be thrown. Days of research couldn't eliminate this method as the best solution, so here it is. 
	 **/
	private void initializeGraphics() {
		BufferStrategy bs = this.window.getCanvas().getBufferStrategy();
		if (bs == null) {
			try {
				this.window.getCanvas().createBufferStrategy(2);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			initializeGraphics();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		g.dispose();
		bs.show();
	}
	
	/**
	 *This method sets up the graphics environment and then calls the game.render() method. 
	 **/
	public void render() {
		if (bs == null) {
			try {
				this.window.getCanvas().createBufferStrategy(2);
				bs = this.window.getCanvas().getBufferStrategy();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			render();//This is a recursive precaution, in case the buffer strategy fails (as it does sometimes without explanation). 
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		game.render(g);
		g.dispose();
		bs.show();
	}
	
	/**
	 *This method simply calls the game.update() method. 
	 **/
	public void update() {
		game.update();
	}
}
