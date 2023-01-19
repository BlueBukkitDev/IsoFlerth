package dev.blue.isoFlerth.experience;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.world.Level;
import dev.blue.isoFlerth.world.Location;
import dev.blue.isoFlerth.world.terrain.Tile;

public class Camera {
	private Level level;
	//private Point focus;
	//private Point destination;
	//private boolean built = false;
	protected short zoom = 0;
	private Viewport viewport;
	//private int panBorder = 3;
	//private int xOffset = 0;
	//private int yOffset = 0;
	private Game game;
	private double tileWidth, tileHeight;
	private Dimension windowDim;
	//private double tilesWide, tilesHigh;
	private Location location;
	
	/**
	 *Camera takes the Level data and the Viewport data and its own Position data, and uses them to populate the Viewport with tiles from the Level.
	 *When Camera is then rendered, it renders the tiles found in the Viewport. 
	 **/
	public Camera(Game game) {
		this.game = game;
		this.level = new Level(0);
		Tile.loadTex();
		this.viewport = new Viewport(game, this);
		this.windowDim = new Dimension(game.getWindow().getWidth(), game.getWindow().getHeight());
		this.tileWidth = windowDim.width/19.2;//based on width of 1920. Height is not a matter of concern, game will scale with width alone. 
		this.tileHeight = tileWidth/2;
		//this.tilesWide = windowDim.width/tileWidth;
		//this.tilesHigh = windowDim.height/tileHeight;
		this.location = new Location(370, 150);
		populateViewport();
	}
	
	private void populateViewport() {
		int xMin, xMax, yMin, yMax;
		int tilesToEdge = (int)Math.ceil(windowDim.width/2/tileWidth*2);//+2, then render up
		xMin = (int) location.getX() - tilesToEdge;
		yMin = (int) location.getY() - tilesToEdge;
		Location TLTile = new Location(xMin, yMin);
		xMax = (int) location.getX() + tilesToEdge;
		yMax = (int) location.getY() + tilesToEdge;
		Location BRTile = new Location(xMax, yMax);
		////All those calculations should be correct////
		//Now we need a subarray from the multi-dim array of tiles in the Level, and then we populate our viewport with them. 
	    Tile[][] view = new Tile[xMax-xMin][yMax-yMin];
	    for (int i = 0; i < view[0].length; i++) {
	        view[i] = Arrays.copyOfRange(level.getTiles()[yMin+i], xMin, xMax);
	    }
	    viewport.setView(view);
	}
	
	public double getTileWidth() {
		return tileWidth;
	}
	
	public double getTileHeight() {
		return tileHeight;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*public void build(Level level, Point focus) {
		this.level = level;
		this.focus = focus;
		//adjustFocusForEdges();

		this.built = true;
		//Game.getInstance().setPanSpeed(12.0D);
	}*/

	//public Tile getFocusTile() {
		//return Level.getTiles()[((int) Math.ceil(this.focus.getX() / 40.0D))][((int) Math.ceil(this.focus.getY() / 40.0D))];
	//	return null;
	//}

	//public Tile getTileAt(Point p) {
	//	return null;
	//}

	public Location getTileLocationAt(Point p) {
		return new Location(0, 0);//This location needs to be calculated carefully. This will not be easy in an isometric view. 
	}

	public void render(Graphics g) {
		viewport.render(g);
		/*if (this.built) {
			int renderLastX = ViewField.width;
			int renderLastY = ViewField.height;
			boolean doingRenderLast = false;
			for (int y = 0; y < ViewField.height; y++) {
				for (int x = 0; x < ViewField.width; x++) {
					g.drawImage(this.viewField.getView()[x][y].getTexture()[0], x * Tile.getWidth() - this.xOffset,
							y * Tile.getWidth() - this.yOffset, Tile.getWidth() / 2, Tile.getWidth() / 2, null);
					g.drawImage(this.viewField.getView()[x][y].getTexture()[1], x * Tile.getWidth() - this.xOffset,
							y * Tile.getWidth() - this.yOffset + Tile.getWidth() / 2, Tile.getWidth() / 2,
							Tile.getWidth() / 2, null);
					g.drawImage(this.viewField.getView()[x][y].getTexture()[2],
							x * Tile.getWidth() - this.xOffset + Tile.getWidth() / 2,
							y * Tile.getWidth() - this.yOffset + Tile.getWidth() / 2, Tile.getWidth() / 2,
							Tile.getWidth() / 2, null);
					g.drawImage(this.viewField.getView()[x][y].getTexture()[3],
							x * Tile.getWidth() - this.xOffset + Tile.getWidth() / 2,
							y * Tile.getWidth() - this.yOffset, Tile.getWidth() / 2, Tile.getWidth() / 2, null);
					if (this.viewField.getView()[x][y].isBeingInspected()) {
						renderLastX = x;
						renderLastY = y;
						doingRenderLast = true;
					}
				}
			}
			if (doingRenderLast) {
				g.drawImage(Main.getTextures().tileGlow,
						renderLastX * Tile.getWidth() - this.xOffset - Tile.getWidth() / 9,
						renderLastY * Tile.getWidth() - this.yOffset - Tile.getWidth() / 9,
						Tile.getWidth() + Tile.getWidth() / 9 * 2, Tile.getWidth() + Tile.getWidth() / 9 * 2, null);
			}
		}*/
	}

	//public void update() {
		/*if (this.built) {
			Point last = this.focus;
			this.focus = new Point((int) (this.focus.x + moveX()), (int) (this.focus.y + moveY()));
			adjustFocusForEdges();
			this.xOffset = (this.focus.x % Tile.getWidth());
			if (this.xOffset == 0) {
				this.xOffset = Tile.getWidth();
			}
			this.yOffset = (this.focus.y % Tile.getWidth());
			if (this.yOffset == 0) {
				this.yOffset = Tile.getWidth();
			}
			if (this.focus.distance(last) > 0.5D) {
				this.viewField.update(this.focus);
			}
		}*/
	//}

	//public void adjustFocusForEdges() {
		/*int x = (int) this.focus.getX();
		int y = (int) this.focus.getY();
		boolean adjusted = false;
		if (x <= Window.width / 2) {
			x = Window.width / 2 + 1;
			adjusted = true;
		} else if (x >= this.world.getPixelWidth() - Window.width / 2) {
			x = this.world.getPixelWidth() - Window.width / 2 - 1;
			adjusted = true;
		}
		if (y <= Window.height / 2) {
			y = Window.height / 2 + 1;
			adjusted = true;
		} else if (y >= this.world.getPixelHeight() - Window.height / 2) {
			y = this.world.getPixelHeight() - Window.height / 2 - 1;
			adjusted = true;
		}
		if (adjusted) {
			this.focus = new Point(x, y);
		}*/
	//}

	/*public void dispose() {
		this.built = false;
		this.world = null;
		this.focus = null;
		this.destination = null;
	}

	public Level getWorld() {
		return this.level;
	}

	public Viewport getViewport() {
		return this.viewport;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Point getFocus() {
		return this.focus;
	}

	public void setFocus(Point focus) {
		this.focus = focus;
	}

	public Point getDestination() {
		return this.destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	public double getXOffset() {
		return this.xOffset;
	}

	public double getYOffset() {
		return this.yOffset;
	}

	public Point topLeft() {
		int x = this.focus.x;
		int y = this.focus.y;
		x -= windowDim.width / 2;
		y -= windowDim.height / 2;
		return new Point(x, y);
	}

	public Point topRight() {
		int x = this.focus.x;
		int y = this.focus.y;
		x += windowDim.width / 2;
		y -= windowDim.height / 2;
		return new Point(x, y);
	}

	public Point bottomLeft() {
		int x = this.focus.x;
		int y = this.focus.y;
		x -= windowDim.width / 2;
		y += windowDim.height / 2;
		return new Point(x, y);
	}

	public Point bottomRight() {
		int x = this.focus.x;
		int y = this.focus.y;
		x += windowDim.width / 2;
		y += windowDim.height / 2;
		return new Point(x, y);
	}*/
}
