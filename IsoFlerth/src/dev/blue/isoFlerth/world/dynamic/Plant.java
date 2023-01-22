package dev.blue.isoFlerth.world.dynamic;

public class Plant extends Entity {

	/**
	 *After being damaged, the plant will use up energy to increase overall health. When health is at maximum, the plant will begin to heal. When health and life are at a maximum, 
	 *the plant will accumulate energy until, at a maximum, it uses it up in order to grow. 
	 **/
	public Plant(double life, double maxLife, double health, double maxHealth, double energy, double maxEnergy) {
		super(life, maxLife, health, maxHealth, energy, maxEnergy);
	}
}
