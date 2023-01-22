package dev.blue.isoFlerth.engine;

public class Engine {

	private Renderer renderer;
	private Ticker ticker;
	
	/**
	 *Creates a thread and then sets local variables. This should only ever be instantiated once. 
	 **/
	public Engine(Window window, Game game) {
		this.renderer = new Renderer(game);
		this.ticker = new Ticker(game);
	}
	
	public void start() {
		ticker.start();
		renderer.start();
	}
	
	public void stop() {
		renderer.stop();
		ticker.stop();
		System.exit(0);
	}
	
	public int getFramerate() {
		return renderer.getFramerate();
	}
}
