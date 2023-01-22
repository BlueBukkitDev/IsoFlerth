package dev.blue.isoFlerth.world.dynamic;

public class Resource extends Entity {

	/**
	 *For all resources, energy is used to determine when it will respawn or how many drops will be harvested from it. 
	 **/
	public Resource(double life, double maxLife, double health, double maxHealth, double energy, double maxEnergy) {
		super(1, 1, 1, 1, energy, maxEnergy);
	}
}
