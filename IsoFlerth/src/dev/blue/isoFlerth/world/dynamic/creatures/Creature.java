package dev.blue.isoFlerth.world.dynamic.creatures;

import java.awt.Graphics;

import dev.blue.isoFlerth.world.dynamic.Entity;

public abstract class Creature extends Entity {

	public Creature(double life, double maxLife, double health, double maxHealth, double energy, double maxEnergy) {
		super(life, maxLife, health, maxHealth, energy, maxEnergy);
	}
	
	public abstract void move();
	
	public abstract void render(Graphics g);
	
	public abstract void update();
}
