package dev.blue.isoFlerth.world;

import dev.blue.isoFlerth.world.terrain.Tile;

public class Chunk {
	private Location coord;
	private Tile[][] tiles;
	
	public Chunk(Location coord) {
		this.coord = coord;
		tiles = new Tile[10][10];
	}
	
	public void generate(long seed) {
		
	}
}
