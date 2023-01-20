package dev.blue.isoFlerth.experience;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import dev.blue.isoFlerth.engine.Game;

public class GameUI {
	
	private Game game;
	private Dimension windowDim;
	
	public GameUI(Game game) {
		this.game = game;
		this.windowDim = new Dimension(game.getWindow().getWidth(), game.getWindow().getHeight());
	}
	
	public void render(Graphics g) {
		drawCrosshair(g);
		drawFPS(g);
	}
	
	private void drawCrosshair(Graphics g) {
		g.setColor(new Color(160, 160, 0));
		g.drawLine((int)windowDim.getWidth()/2-2, (int)windowDim.getHeight()/2, (int)windowDim.getWidth()/2+2, (int)windowDim.getHeight()/2);
		g.drawLine((int)windowDim.getWidth()/2, (int)windowDim.getHeight()/2-2, (int)windowDim.getWidth()/2, (int)windowDim.getHeight()/2+2);
	}
	
	private void drawFPS(Graphics g) {
		g.setColor(new Color(160, 160, 0));
		g.setFont(new Font("Helvetica", Font.BOLD, 16));
		g.drawString("FPS: "+game.getFramerate(), (int)windowDim.getWidth()-100, 20);
	}
}
