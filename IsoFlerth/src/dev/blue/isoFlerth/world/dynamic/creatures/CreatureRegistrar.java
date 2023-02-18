package dev.blue.isoFlerth.world.dynamic.creatures;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CreatureRegistrar {
	private List<Creature> creatures;
	
	public CreatureRegistrar() {
		creatures = new ArrayList<Creature>();
	}
	
	public void registerCreature(Creature creature) {
		creatures.add(creature);
	}
	
	public void unregisterCreature(Creature creature) {
		creatures.remove(creature);
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}
	
	public void killAllCreatures() {
		creatures.clear();
	}
	
	public void render(Graphics g) {
		for(Creature each:creatures) {
			each.render(g);
		}
	}
	
	public void update() {
		for(Creature each:creatures) {
			each.update();
		}
	}
}
