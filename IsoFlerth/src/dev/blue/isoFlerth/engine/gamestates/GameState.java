package dev.blue.isoFlerth.engine.gamestates;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import dev.blue.isoFlerth.engine.input.ui.ButtonRegistry;

/**
 *An abstract class to hold all UIObjects and event handlers for a given state. When the state is rendered or updated, 
 *everything contained within the state that can be is also rendered and updated. 
 **/
public abstract class GameState {
	protected ButtonRegistry buttonRegistry;
	
	/**
	 *Creates an instance of GameState and requires a ButtonRegistry object. 
	 **/
	public GameState(ButtonRegistry buttonRegistry) {
		this.buttonRegistry = buttonRegistry;
	}
	
	/**
	 *Returns the ButtonRegistry that was provided on initialization of this class. 
	 **/
	public ButtonRegistry getButtonRegistry() {
		return buttonRegistry;
	}
	/**
	 *Renders all renderable objects within the state. 
	 **/
	public abstract void render(Graphics g);
	/**
	 *Updates all updatable objects within the state. 
	 **/
	public abstract void update();
	/**
	 *Called by the MouseManager (found in the Game class) when the mouse is moved. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract void onMouseMove(Point p);
	/**
	 *Called by the MouseManager (found in the Game class) when a mouse button is clicked. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract boolean onClick(int button, Point p);
	/**
	 *Called by the MouseManager (found in the Game class) when a mouse button is pressed. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract boolean onMouseDown(int button, Point p);
	/**
	 *Called by the MouseManager (found in the Game class) when a mouse button is released. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract boolean onMouseUp(int button, Point p);

	/**
	 *Called by the KeyManager (found in the Game class) when a key is typed. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract void onType(KeyEvent e);
	/**
	 *Called by the KeyManager (found in the Game class) when a key is initially pressed. When overridden by extending classes, custom functions and operations can be performed. 
	 **/
	public abstract void onKeyPressed(KeyEvent e);
}
