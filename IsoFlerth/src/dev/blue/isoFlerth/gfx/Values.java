package dev.blue.isoFlerth.gfx;

import java.awt.Color;

import dev.blue.isoFlerth.engine.Game;

public class Values {
	public static Color grass = new Color(0, 255, 0);
	public static Color stone = new Color(127, 127, 127);
	public static Color dirt = new Color(0, 0, 0);
	public static Color sand = new Color(0, 0, 0);
	public static Color water = new Color(0, 0, 255);
	public static Color snow = new Color(255, 255, 255);
	public static double tileWidth;
	public static double tileHeight;
	
	public Values(Game game) {
		tileWidth = game.getWindow().getWidth()/19.2;
		tileHeight = tileWidth/2;
	}
}
