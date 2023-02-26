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
import dev.blue.isoFlerth.world.terrain.TileType;

public class Camera {
	private Tile[][] view;
	private boolean built;
	protected short zoom = 0;
	private Viewport viewport;
	private int panBorder = 3;
	//private int xOffset, yOffset;
	//private double renderX, renderY;
	private Game game;
	private Location location;
	private double windowWidth, windowHeight;
	private double panSpeed;
	private double renderX, renderY;
	private float renderOffsetX, renderOffsetY;
	
	
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
		panSpeed = 0.1;
	}
	
	public void onKeyPressed(KeyEvent e) {
		
	}
	
	public void onMouseMoved(Point p) {
		if(p.getX() < panBorder) {
			moveX(false);
		}else if(p.getX() > windowWidth-panBorder) {
			moveX(true);
		}
		if(p.getY() < panBorder) {
			moveY(false);
		}else if(p.getY() > windowHeight-panBorder) {
			moveY(true);
		}
		adjustLocationFromOffset();
		viewport.populate(location);
	}
	
	private void adjustLocationFromOffset() {
		if(renderOffsetX >= Values.tileWidth/2) {
			renderOffsetX -= Values.tileWidth/2;
			location.add(1, -1);
		}else if(renderOffsetX <= -(Values.tileWidth/2)) {
			renderOffsetX += Values.tileWidth/2;
			location.add(-1, 1);
		}
		if(renderOffsetY >= Values.tileHeight/2) {//down
			renderOffsetY -= Values.tileHeight/2;
			location.add(1, 1);
		}else if(renderOffsetY <= -(Values.tileHeight/2)) {//up
			renderOffsetY += Values.tileHeight/2;
			location.add(-1, -1);
		}
	}
	
	/**
	 *Takes in whether the movement is away from the Top-Left corner
	 **/
	private void moveX(boolean down_right) {
		if(!down_right) {
			//location.subtract(panSpeed, 0);
			renderOffsetX -= panSpeed*Values.tileWidth;
			return;
		}
		//location.add(panSpeed, 0);
		renderOffsetX += panSpeed*Values.tileWidth;
		return;
	}
	
	/**
	 *Takes in whether the movement is away from the Top-Left corner
	 **/
	private void moveY(boolean down_right) {
		if(!down_right) {
			//location.subtract(0, panSpeed);
			renderOffsetY -= panSpeed*Values.tileWidth;
			return;
		}
		//location.add(0, panSpeed);
		renderOffsetY += panSpeed*Values.tileWidth;
		return;
	}
	
	public void render(Graphics g) {
		if(!built) {
			return;
		}
		int yOffset = (int)(Values.tileHeight*viewport.getTilesToEdge()/2)-(int)(Values.tileHeight/3);
		int xOffset = (int)(Values.tileWidth/2);
		renderX = game.getWindow().getWidth()/2-Values.tileWidth/2-xOffset;
		renderY = 0-yOffset;
		for(int i = 0; i < view.length; i++) {
			for(int j = 0; j < view[0].length; j++) {
				if(shouldRender(renderX, renderY)) {
					drawTile(g, view[i][j], (int)renderX, (int)renderY);
					if(game.debug) {
						drawDebug(g, view[i][j], renderX, renderY);
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
	
	public void update() {
		
	}
	
	private void drawTile(Graphics g, Tile tile, int x, int y) {
		if(tile == null) {
			tile = new Tile(null, TileType.VOID, 0, 0);
		}
		g.drawImage(tile.getTexture(), x-(int)renderOffsetX, y-(int)renderOffsetY, (int)Values.tileWidth, (int)Values.tileHeight, null);
	}
	
	private void drawDebug(Graphics g, Tile tile, double renderX, double renderY) {
		g.setColor(Color.BLACK);
		if(tile == null) {
			tile = new Tile(null, TileType.VOID, 0, 0);
		}
		if(tile.getType() != TileType.VOID) {
			g.drawString((int)tile.getCoord().getX()+","+(int)tile.getCoord().getY(), (int)(renderX+Values.tileWidth/2)-20, (int)(renderY+Values.tileHeight/2)+10);
		}
		g.setColor(Color.RED);
		g.drawString("Viewing: "+location.getX()+", "+location.getY(), (int)windowWidth-200, 40);
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
	
	public void setLocation(Location loc) {
		this.location = loc;
	}

	public Location getTileLocationAt(Point p) {
		return new Location(0, 0);//This location needs to be calculated carefully. This will not be easy in an isometric view. 
	}

}
