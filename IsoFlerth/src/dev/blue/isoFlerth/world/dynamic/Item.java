package dev.blue.isoFlerth.world.dynamic;

public class Item extends Entity {

	/**
	 *When an item runs out of "energy", it "dies". 
	 **/
	public Item(double energy, double maxEnergy) {
		super(1, 1, 1, 1, energy, maxEnergy);
	}
}
