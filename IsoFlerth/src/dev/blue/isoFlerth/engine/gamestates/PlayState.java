package dev.blue.isoFlerth.engine.gamestates;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.engine.input.ui.ButtonRegistry;
import dev.blue.isoFlerth.experience.Camera;
import dev.blue.isoFlerth.experience.GameUI;
import dev.blue.isoFlerth.gfx.Textures;
import dev.blue.isoFlerth.gfx.ui.UIContainer;
import dev.blue.isoFlerth.world.Level;
import dev.blue.isoFlerth.world.Location;

public class PlayState extends GameState {

	private Dimension windowDim;
	private UIContainer menuItems;
	private HashMap<String, BufferedImage> menuTextures;
	private Game game;
	private Level level;
	private Camera camera;
	private GameUI gameUI;
	
	public PlayState(Game game) {
		super(new ButtonRegistry());
		this.game = game;
		windowDim = game.getWindow().getSize();
		menuItems = new UIContainer(new Location(windowDim.getWidth()/2, windowDim.getHeight()/2));
		menuTextures = new Textures().loadMenus();
		level = new Level(0l);
		System.out.println("Level Coord Check: 0x0-"+level.getTile(0, 0).getType().toString()+"; 499x0-"+level.getTile(499, 0).getType().toString()
				+"; 500x500-"+level.getTile(499, 499).getType().toString()+"; 1x500-"+level.getTile(0, 499).getType().toString());
		camera = new Camera(game);
		gameUI = new GameUI(game);
	}

	@Override
	public void render(Graphics g) {
		camera.render(g);
		gameUI.render(g);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onMouseMove(Point p) {
		
	}

	@Override
	public boolean onClick(int button, Point p) {
		return false;
	}

	@Override
	public boolean onMouseDown(int button, Point p) {
		return false;
	}

	@Override
	public boolean onMouseUp(int button, Point p) {
		return false;
	}

	@Override
	public void onType(KeyEvent e) {
		
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.exit();
		}
		if(e.getKeyCode() == KeyEvent.VK_INSERT) {
			game.debug = !game.debug;
		}
	}
}
