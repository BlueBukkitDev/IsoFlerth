package dev.blue.isoFlerth.experience;

import java.util.Arrays;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.gfx.Values;
import dev.blue.isoFlerth.world.Level;
import dev.blue.isoFlerth.world.Location;
import dev.blue.isoFlerth.world.terrain.Tile;

public class Viewport {
	public static int width;
	public static int height;
	public Location topLeft;
	private Camera camera;
	private double windowWidth;
	private int tilesToEdge;
	private Level level;
	
	public Viewport(Game game, Camera camera, Level level) {
		windowWidth = game.getWindow().getWidth(); 
		this.camera = camera;
		this.level = level;
	}
	
	/**
	 *Populates the View (a Tile[][]) with tiles from the level based on the location of the camera. 
	 **/
	public void populate(Location location) {
		int xMin, xMax, yMin, yMax;
		calculateTilesToEdge();
		xMin = (int) location.getX() - tilesToEdge;
		yMin = (int) location.getY() - tilesToEdge;
		xMax = (int) location.getX() + tilesToEdge;
		yMax = (int) location.getY() + tilesToEdge;
	    Tile[][] view = new Tile[yMax-yMin][xMax-xMin];
	    for (int i = 0; i < view[0].length; i++) {
	        view[i] = Arrays.copyOfRange(level.getTiles()[xMin+i], yMin, yMax);
	    }
	    camera.setView(view);
	}
	
	/**
	 *Calculates the number of tiles between the center of the window and the edge of the window. It does so by dividing the window's width by 2, 
	 *then dividing that by half the width of a tile (due to the offset nature of an isometric grid), and then adding 1 since some tiles would be 
	 *touching the edge of the screen while the next row would be offset by half a tile.  
	 **/
	private void calculateTilesToEdge() {
		tilesToEdge = (int)Math.ceil(windowWidth/2/(Values.tileWidth/2)+1);
	}
	
	/**
	 *Returns the number of tiles to the edge of the window. 
	 **/
	public int getTilesToEdge() {
		return tilesToEdge;
	}

	/*public boolean contains(Location location) {
		return (location.getX() >= this.topLeft.getX()) && (location.getX() < this.topLeft.getX() + width)
				&& (location.getY() >= this.topLeft.getY()) && (location.getY() < this.topLeft.getY() + height);
	}*/
}
