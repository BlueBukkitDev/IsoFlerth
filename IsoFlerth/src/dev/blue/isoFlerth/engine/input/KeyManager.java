package dev.blue.isoFlerth.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dev.blue.isoFlerth.engine.Game;

public class KeyManager implements KeyListener {
	
	private Game game;
	
	public KeyManager(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		game.getState().onType(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.getState().onKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
