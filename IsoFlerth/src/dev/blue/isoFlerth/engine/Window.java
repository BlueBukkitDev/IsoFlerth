package dev.blue.isoFlerth.engine;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import dev.blue.isoFlerth.engine.input.KeyManager;
import dev.blue.isoFlerth.engine.input.MouseManager;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	
	/**
	 *Creation and setup of a full-screen window. 
	 **/
	public Window() {
		setTitle("IsoFlerth");
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setUndecorated(false);
		this.canvas = new Canvas();
		add(this.canvas);
		pack();
		setVisible(true);
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		device.setFullScreenWindow(this);
	}
	
	/**
	 *Useful for getting canvas dimensions.
	 **/
	public Canvas getCanvas() {
		return canvas;
	}
	/**
	 *Sets the KeyManager for the window. 
	 **/
	public void setKeyManager(KeyManager keyManager) {
		addKeyListener(keyManager);
		this.canvas.addKeyListener(keyManager);
		pack();
	}
	/**
	 *Sets the MouseManager for the window. 
	 **/
	public void setMouseManager(MouseManager mouseManager) {
		addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
		addMouseWheelListener(mouseManager);
		this.canvas.addMouseListener(mouseManager);
		this.canvas.addMouseMotionListener(mouseManager);
		this.canvas.addMouseWheelListener(mouseManager);
		pack();
	}
}
