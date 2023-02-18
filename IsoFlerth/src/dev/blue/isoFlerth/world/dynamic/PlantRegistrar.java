package dev.blue.isoFlerth.world.dynamic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PlantRegistrar {
	private List<Plant> plants;
	
	public PlantRegistrar() {
		plants = new ArrayList<Plant>();
	}
	
	public void addPlant(Plant plant) {
		plants.add(plant);
	}
	
	public void removePlant(Plant plant) {
		plants.remove(plant);
	}
	
	public void render(Graphics g) {
		
	}
}
