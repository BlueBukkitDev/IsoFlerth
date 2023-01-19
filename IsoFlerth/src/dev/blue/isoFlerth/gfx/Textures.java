package dev.blue.isoFlerth.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Textures {
	public Textures() {
		
	}
	/**
	 *Loads the credits images. 
	 **/
	public List<BufferedImage> loadCredits() {
		List<BufferedImage> credits = new ArrayList<BufferedImage>();
		
		int i = 0;
		BufferedImage current;
		while((current = ImageLoader.loadImage("credits/credit"+(i)+".png")) != null) {
			credits.add(current);
			i++;
		}
		
		return credits;
	}
	
	/**
	 *
	 **/
	public HashMap<String, BufferedImage> loadMenus() {
		HashMap<String, BufferedImage> menus = new HashMap<String, BufferedImage>();
		
		menus.put("button_singleplayer", ImageLoader.loadImage("menu/buttons/singleplayer.png"));
		menus.put("button_singleplayer_hover", ImageLoader.loadImage("menu/buttons/singleplayer_hover.png"));
		return menus;
	}
}