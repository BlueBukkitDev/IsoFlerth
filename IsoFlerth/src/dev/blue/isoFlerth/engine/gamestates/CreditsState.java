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
	private final int creditFrames = 100;//400;
	/**
	 *fadeFrames determines the number of frames during which the credits will fade.  
	 **/
	private final int fadeFrames = 25; //200;
	/**
	 *fadeClock is a countdown that begins equal to fadeFrames. 
	 **/
	private double fadeClock = fadeFrames;
	/**
	 *creditClock is a countdown that begins equal to creditFrames. 
	 **/
	private int creditClock = creditFrames;
	/**
	 *changePerFrame is calculated by dividing the max opacity (255) by the number of frames over which the opacity will decrease. 
	 **/
	private double changePerFrame = 255/fadeFrames;
	
	private int blackoutFrames = 12;
	
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
		if(fadeClock >= 0) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, windowDim.width, windowDim.height);
			for(BufferedImage each:credits) {
				g.drawImage(credits.get(0), 0, 20, windowDim.width, credits.get(0).getHeight()*(windowDim.width/each.getWidth()), null);
			}
			if(creditClock > 0) {
				creditClock--;
			}else {
				g.setColor(new Color(0, 0, 0, 255-(int)(fadeClock*changePerFrame)));
				g.fillRect(0, 0, windowDim.width, windowDim.height);
				fadeClock--;
			}
		}else {
			if(blackoutFrames > 0) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, windowDim.width, windowDim.height);
				blackoutFrames--;
			}else {
				exitCreditsState = true;
			}
		}
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
