package dev.blue.isoFlerth.engine;

public class Ticker implements Runnable {
	
	private Thread thread;
	private boolean running;
	private Game game;
	private double TPS = 120;
	private int tickrate = 0;
	
	public Ticker(Game game) {
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
		int tickrateClock = 0;
		while (this.running) {
			long now = System.nanoTime();
			passedSinceLastRender = ((now - lastFrame) / 1000000L);
			passedSinceLastTick = ((now - lastTick) / 1000000L);
			if (passedSinceLastRender >= 1000.0D/TPS) {
				passedSinceLastRender = 0.0D;
				update();
				lastFrame = now;
				tickrateClock++;
			}
			if (passedSinceLastTick >= 1000.0D) {
				tickrate = tickrateClock;
				tickrateClock = 0;
				lastTick = now;
			}
		}
	}
	
	/**
	 *This method simply calls the game.update() method. 
	 **/
	public void update() {
		game.update();
	}

	/**
	 *This method should be called to start the game engine. 
	 **/
	public synchronized void start() {
		thread.start();
		running = true;
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
	
	
	public int getFramerate() {
		return tickrate;
	}
}
