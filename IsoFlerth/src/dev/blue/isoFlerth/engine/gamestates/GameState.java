package dev.blue.isoFlerth.engine.gamestates;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import dev.blue.isoFlerth.engine.input.ui.ButtonRegistry;

public abstract class GameState {
	public abstract void render(Graphics g);
	public abstract void update();
	protected ButtonRegistry buttonRegistry;
	
	public ButtonRegistry getButtonRegistry() {
		return buttonRegistry;
	}
	
	public GameState(ButtonRegistry buttonRegistry) {
		this.buttonRegistry = buttonRegistry;
	}
	
	public abstract void onMouseMove(Point p);

	public abstract boolean onClick(int button, Point p);

	public abstract boolean onMouseDown(int button, Point p);

	public abstract boolean onMouseUp(int button, Point p);

	public abstract void onType(KeyEvent e);

	public abstract void onKeyPressed(KeyEvent e);
}
