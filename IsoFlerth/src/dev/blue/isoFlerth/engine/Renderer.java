package dev.blue.isoFlerth.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Renderer implements Runnable {
	
	private Thread thread;
	private boolean running;
	private Game game;
	private double FPS = 120;
	private BufferStrategy bs;
	private int framerate = 0;
	private Graphics g;
	
	public Renderer(Game game) {
		thread = new Thread(this);
		this.game = game;
	}

	/**
	 *This method should never be called externally. It should only ever be referenced by the start() method. 
	 **/
	@Override
	public void run() {
		long lastFrame = System.nanoTime();
		long lastTick = System.nanoTime();
		double passedSinceLastRender = 0.0D;
		double passedSinceLastTick = 0.0D;
		int framerateClock = 0;
		while (this.running) {
			long now = System.nanoTime();
			passedSinceLastRender = ((now - lastFrame) / 1000000L);
			passedSinceLastTick = ((now - lastTick) / 1000000L);
			if (passedSinceLastRender >= 1000.0D/FPS) {
				passedSinceLastRender = 0.0D;
				render();
				lastFrame = now;
				framerateClock++;
			}
			if (passedSinceLastTick >= 1000.0D) {
				framerate = framerateClock;
				framerateClock = 0;
				lastTick = now;
			}
		}
	}
	
	/**
	 *This method sets up the graphics environment and then calls the game.render() method. 
	 **/
	public void render() {
		if (bs == null) {
			try {
				game.getWindow().getCanvas().createBufferStrategy(2);
				bs = game.getWindow().getCanvas().getBufferStrategy();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			render();//This is a recursive precaution, in case the buffer strategy fails (as it does sometimes without explanation). 
			return;
		}
		if(g == null) {
			g = bs.getDrawGraphics();
		}
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		game.render(g);
		//g.dispose();
		bs.show();
	}

	/**
	 *This method should be called to start the renderer. 
	 **/
	public synchronized void start() {
		thread.start();
		running = true;
		//initializeGraphics();
	}
	
	/**
	 *This method should be called to stop the renderer. 
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
	 *Returns an int representing the number of frames that have been rendered in the last 1,000 milliseconds. 
	 **/
	public int getFramerate() {
		return framerate;
	}
}
