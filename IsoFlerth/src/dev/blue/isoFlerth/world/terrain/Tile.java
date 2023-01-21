package dev.blue.isoFlerth.world.terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.blue.isoFlerth.gfx.ImageLoader;
import dev.blue.isoFlerth.gfx.Spritesheet;
import dev.blue.isoFlerth.gfx.Values;
import dev.blue.isoFlerth.world.Level;
import dev.blue.isoFlerth.world.Location;

public class Tile {
	private Location coord;
	private TileType type;
	//private Level level;
	//private Layer layer;
	private static BufferedImage grassTex;
	private static BufferedImage stoneTex;
	private static BufferedImage dirtTex;
	private static BufferedImage waterTex;
	private static BufferedImage snowTex;
	private static BufferedImage sandTex;
	
	public Tile(Level level, TileType type, int x, int y) {
		this.type = type;
		this.coord = new Location(x, y);
	}
	
	public static void loadTex() {
		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("textures/terrain/tiles.png"), 100, 50);
		grassTex = sheet.getSprite(1, 1);
		dirtTex = sheet.getSprite(1, 2);
		stoneTex = sheet.getSprite(1, 3);
		snowTex = sheet.getSprite(1, 4);
		waterTex = sheet.getSprite(1, 5);
		sandTex = sheet.getSprite(1, 1);
	}
	
	public Tile(Level level, Color color, int x, int y) {
		//this.level = level;
		if(color.equals(Values.grass)) {
			type = TileType.GRASS;
		}else if(color.equals(Values.stone)) {
			type = TileType.STONE;
		}else if(color.equals(Values.water)) {
			type = TileType.WATER;
		}else if(color.equals(Values.snow)) {
			type = TileType.SNOW;
		}else {
			type = TileType.GRASS;
			System.out.println("Gonna have to compromise");
		}
		this.coord = new Location(x, y);
	}
	
	public Location getCoord() {
		return coord;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void update() {
		
	}
	
	public TileType getType() {
		return type;
	}
	
	public BufferedImage getTexture() {
		if(type==TileType.GRASS) {
			return grassTex;
		} 
		if(type==TileType.STONE) {
			return stoneTex;
		}
		if(type==TileType.DIRT) {
			return dirtTex;
		}
		if(type==TileType.WATER) {
			return waterTex;
		}
		if(type==TileType.SNOW) {
			return snowTex;
		}
		if(type==TileType.SAND) {
			return sandTex;
		}
		else return stoneTex;
	}
}
