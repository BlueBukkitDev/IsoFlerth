package dev.blue.isoFlerth.world.dynamic.creatures.herbivores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.blue.isoFlerth.world.dynamic.creatures.Creature;

public class Rabbit extends Creature {
	
	private Rectangle hitbox;

	public Rabbit(double life, double maxLife, double health, double maxHealth, double energy, double maxEnergy) {
		super(life, maxLife, health, maxHealth, energy, maxEnergy);
		hitbox = new Rectangle(10, 7);
	}

	@Override
	public void move() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)(loc.getX()-(hitbox.getX()/2)), (int)(loc.getY()-(hitbox.getY()/2)), (int)hitbox.getX(), (int)hitbox.getY());
	}

	@Override
	public void update() {
		
	}
}
