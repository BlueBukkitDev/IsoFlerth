package dev.blue.isoFlerth.engine;

import java.awt.Graphics;

import dev.blue.isoFlerth.engine.gamestates.GameState;
import dev.blue.isoFlerth.engine.gamestates.MenuState;
import dev.blue.isoFlerth.engine.gamestates.PlayState;
import dev.blue.isoFlerth.engine.input.KeyManager;
import dev.blue.isoFlerth.engine.input.MouseManager;

public class Game {
	
	private Engine engine;
	private Window window;
	private GameState state;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	public boolean debug = false;
	
	public Game() {
		window = new Window();
		keyManager = new KeyManager(this);
		mouseManager = new MouseManager(this);
		state = new dev.blue.isoFlerth.engine.gamestates.CreditsState(this);
		window.setKeyManager(keyManager);
		window.setMouseManager(mouseManager);
		engine = new Engine(window, this);
		engine.start();
	}
	
	public GameState getState() {
		return state;
	}
	
	public void render(Graphics g) {
		state.render(g);
	}
	
	public void update() {
		state.update();
	}
	
	public Window getWindow() {
		return window;
	}
	
	public void changeToMenuState() {
		state = new MenuState(this);
	}
	
	public void changeToPlayState() {
		state = new PlayState(this);
	}
	
	public void exit() {
		engine.stop();
	}
}
