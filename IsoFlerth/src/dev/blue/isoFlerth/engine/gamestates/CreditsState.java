package dev.blue.isoFlerth.engine.gamestates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.engine.input.ui.ButtonRegistry;
import dev.blue.isoFlerth.gfx.Textures;

public class CreditsState extends GameState{
	
	/**
	 *creditFrames is the number of frames that you wish to spend showing the credits screen. 
	 **/
	private int creditFrames = 100;//400;
	
	/**
	 *fadeSpeed is a (somewhat poorly implemented) variable that decreases the opacity of the credits by this value per frame. Set to 255 to have no fade. 
	 **/
	private int fadeSpeed = 5;
	/**
	 *fadeFrames being set above 255 will probably break the fade in the render method.  
	 **/
	private int fadeFrames = 255; //200;
	private List<BufferedImage> credits;
	private Game game;
	private Dimension windowDim;
	private boolean exitCreditsState = false;
	
	public CreditsState(Game game) {
		super(new ButtonRegistry());
		this.game = game;
		windowDim = game.getWindow().getSize();
		credits = new Textures().loadCredits();
	}

	@Override
	public void render(Graphics g) {
		if(fadeFrames-fadeSpeed >= 0) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, windowDim.width, windowDim.height);
			for(BufferedImage each:credits) {
				g.drawImage(credits.get(0), 0, 20, windowDim.width, credits.get(0).getHeight()*(windowDim.width/each.getWidth()), null);
			}
			if(creditFrames > 0) {
				creditFrames--;
			}
			if(creditFrames <= 0) {
				g.setColor(new Color(0, 0, 0, 255-(fadeFrames -= fadeSpeed)));
				g.fillRect(0, 0, windowDim.width, windowDim.height);
				fadeFrames--;
			}
		}else exitCreditsState = true;
	}

	@Override
	public void update() {
		if(exitCreditsState) {
			game.changeToMenuState();
		}
	}

	@Override
	public void onMouseMove(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onClick(int button, Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onMouseDown(int button, Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onMouseUp(int button, Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onType(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
