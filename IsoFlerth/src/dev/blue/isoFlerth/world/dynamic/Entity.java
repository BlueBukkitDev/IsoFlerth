package dev.blue.isoFlerth.world.dynamic;

import dev.blue.isoFlerth.world.Location;

public abstract class Entity {
	protected double life;
	protected double maxLife;
	protected double health;
	protected double maxHealth;
	protected double energy;
	protected double maxEnergy;
	protected Location loc;
	protected DNA dna;
	
	public Entity(double life, double maxLife, double health, double maxHealth, double energy, double maxEnergy) {
		
	}
	
	protected void generateDNA() {
		dna = new DNA();
	}
	
	protected void spawn(Location loc) {
		this.loc = loc;
	}
}
