package dev.blue.isoFlerth.experience;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.gfx.Values;
import dev.blue.isoFlerth.world.Level;
import dev.blue.isoFlerth.world.Location;
import dev.blue.isoFlerth.world.terrain.Tile;

public class Camera {
	private Tile[][] view;
	private boolean built;
	protected short zoom = 0;
	private Viewport viewport;
	//private int panBorder = 3;
	//private int xOffset = 0;
	//private int yOffset = 0;
	private Game game;
	//private double tilesWide, tilesHigh;
	private Location location;
	private double windowWidth, windowHeight;
	
	
	/**
	 *Camera takes the Level data and the Viewport data and its own Position data, and uses them to populate the Viewport with tiles from the Level.
	 *When Camera is then rendered, it renders the tiles found in the Viewport. 
	 **/
	public Camera(Game game) {
		this.game = game;
		Level level = new Level(0);
		Tile.loadTex();
		this.viewport = new Viewport(game, this, level);
		windowWidth = game.getWindow().getWidth(); 
		windowHeight = game.getWindow().getHeight();
		this.location = new Location(370, 150);
		viewport.populate(location);
	}
	
	public void onKeyPressed(KeyEvent e) {
		if(!game.getSettings().getMovementStyle()) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				location.subtract(1, 1);
			} else if(e.getKeyCode() == KeyEvent.VK_S) {
				location.add(1, 1);
			} else if(e.getKeyCode() == KeyEvent.VK_A) {
				location.subtract(1, -1);
			} else if(e.getKeyCode() == KeyEvent.VK_D) {
				location.add(1, -1);
			} else return;
		} else {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				location.subtract(0, 1);
			} else if(e.getKeyCode() == KeyEvent.VK_S) {
				location.add(0, 1);
			} else if(e.getKeyCode() == KeyEvent.VK_A) {
				location.subtract(1, 0);
			} else if(e.getKeyCode() == KeyEvent.VK_D) {
				location.add(1, 0);
			} else return;
		}
		viewport.populate(location);
	}
	
	public void render(Graphics g) {
		if(!built) {
			return;
		}
		int yOffset = (int)(Values.tileHeight*viewport.getTilesToEdge()/2)-(int)(Values.tileHeight/3);
		int xOffset = (int)(Values.tileWidth/2);
		double renderX = game.getWindow().getWidth()/2-Values.tileWidth/2-xOffset;
		double renderY = 0-yOffset;
		for(int i = 0; i < view.length; i++) {
			for(int j = 0; j < view[0].length; j++) {
				if(shouldRender(renderX, renderY)) {
					g.drawImage(view[i][j].getTexture(), (int)renderX, (int)renderY, (int)Values.tileWidth, (int)Values.tileHeight, null);
					if(game.debug) {
						g.setColor(Color.BLACK);
						g.drawString((int)view[i][j].getCoord().getX()+","+(int)view[i][j].getCoord().getY(), (int)(renderX+Values.tileWidth/2)-20, (int)(renderY+Values.tileHeight/2)+10);
					}
				}
				renderX += 0-Values.tileWidth/2;
				renderY += Values.tileHeight/2;
				if(j+1 == view[0].length) {
					renderX = game.getWindow().getWidth()/2+Values.tileWidth/2+((Values.tileWidth*i)/2)-xOffset;
					renderY = 0-yOffset+((Values.tileHeight*i)/2);
				}
			}
		}
	}
	
	private boolean shouldRender(double x, double y) {
		if(x >= 0-Values.tileWidth && x <= windowWidth && y >= 0-Values.tileHeight && y <= windowHeight) {
			return true;
		}
		return false;
	}
	
	public void setView(Tile[][] tiles) {
		this.view = tiles;
		built = true;
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
