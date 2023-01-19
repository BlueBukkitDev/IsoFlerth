package dev.blue.isoFlerth.engine.gamestates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.engine.input.ui.ButtonRegistry;
import dev.blue.isoFlerth.gfx.Animation;
import dev.blue.isoFlerth.gfx.Textures;
import dev.blue.isoFlerth.gfx.ui.Button;
import dev.blue.isoFlerth.gfx.ui.UIContainer;
import dev.blue.isoFlerth.world.Location;

public class MenuState extends GameState {
	
	private Dimension windowDim;
	private UIContainer menuItems;
	private HashMap<String, BufferedImage> menuTextures;
	private Game game;
	
	public MenuState(Game game) {
		super(new ButtonRegistry());
		this.game = game;
		windowDim = game.getWindow().getSize();
		menuItems = new UIContainer(new Location(windowDim.getWidth()/2, windowDim.getHeight()/2));
		menuTextures = new Textures().loadMenus();
		setupMainMenu();
	}
	
	private void setupMainMenu() {
		clearSlate();
		int menuButtonWidth = windowDim.width/8;
		int menuButtonHeight = menuButtonWidth/6;
		int buttonPadding = 12;
		
		Animation b_sp_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer"));
		Animation b_sp_hover_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer_hover"));
		Animation b_mp_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer"));
		Animation b_mp_hover_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer_hover"));
		Animation b_op_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer"));
		Animation b_op_hover_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer_hover"));
		Animation b_ex_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer"));
		Animation b_ex_hover_anim = new Animation(0, new Location(0, 0), menuButtonWidth, menuButtonHeight, menuTextures.get("button_singleplayer_hover"));
		
		Button b_singleplayer = new Button("Single Player", true, false, 16, 0, 0, menuButtonWidth, menuButtonHeight, 0, b_sp_anim, b_sp_anim, b_sp_hover_anim) {
			public void runClick() {
				game.changeToPlayState();
			}
		};
		Button b_multiplayer = new Button("Multiplayer", true, false, 16, 0, 0, menuButtonWidth, menuButtonHeight, 0, b_mp_anim, b_mp_anim, b_mp_hover_anim);
		Button b_options = new Button("Options", true, false, 16, 0, 0, menuButtonWidth, menuButtonHeight, 0, b_op_anim, b_op_anim, b_op_hover_anim);
		Button b_exit = new Button("Exit", true, false, 16, 0, 0, menuButtonWidth, menuButtonHeight, 0, b_ex_anim, b_ex_anim, b_ex_hover_anim) {
			public void runClick() {
				game.exit();
			}
		};
		
		menuItems.add(b_singleplayer, new Location(0, -1*menuButtonHeight));
		menuItems.add(b_multiplayer, new Location(0, 0+buttonPadding));
		menuItems.add(b_options, new Location(0, menuButtonHeight+(buttonPadding*2)));
		menuItems.add(b_exit, new Location(0, 2*menuButtonHeight+(buttonPadding*3)));
		
		buttonRegistry.registerButton(b_singleplayer);
		buttonRegistry.registerButton(b_multiplayer);
		buttonRegistry.registerButton(b_options);
		buttonRegistry.registerButton(b_exit);
	}
	
	private void setupSingleplayerMenu() {
		clearSlate();
		int menuButtonWidth = windowDim.width/8;
		int menuButtonHeight = menuButtonWidth/6;
		int buttonPadding = 12;
	}
	
	private void clearSlate() {
		menuItems = new UIContainer(new Location(windowDim.getWidth()/2, windowDim.getHeight()/2));
		buttonRegistry.clear();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(40, 40, 40, 255));
		g.fillRect(0, 0, windowDim.width, windowDim.height);
		buttonRegistry.render(g);
	}

	@Override
	public void update() {
		buttonRegistry.update();
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
