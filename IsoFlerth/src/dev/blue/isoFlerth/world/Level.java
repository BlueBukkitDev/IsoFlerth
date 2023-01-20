package dev.blue.isoFlerth.world;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.blue.isoFlerth.gfx.ImageLoader;
import dev.blue.isoFlerth.world.terrain.Tile;

public class Level {
	private Tile[][] tiles;
	private long seed;
	private Random rand;
	private BufferedImage map;
	
	public Level(long seed) {
		tiles = new Tile[500][500];
		this.seed = seed;
		rand = new Random(seed);
		map = ImageLoader.loadImage("textures/terrain/map.png");
		fillWorld();
	}
	
	private void fillWorld() {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				Tile tile = new Tile(this, new Color(map.getRGB(i, j)), i, j);
				tiles[i][j] = tile;
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
}
