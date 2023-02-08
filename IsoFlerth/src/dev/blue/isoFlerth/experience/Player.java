package dev.blue.isoFlerth.experience;

import dev.blue.isoFlerth.world.dynamic.Entity;

/**
 *A Player is not an object in-game; it is a sentient being, controlling a keyboard and mouse, and using 
 *this game as a real-world interface between themselves and their adventure. As such, this class does 
 *not include rendering or animating, but rather it contains the things which belong to the player; a 
 *Human entity, an inventory, a set of stats and data, and a history of not only state, but conversations, 
 *achievements, and relationships. 
 *
 *
 *Name/ID of the player will be retrieved from an authentication server. All other data will come from the game server. 
 **/
public class Player {
	Entity human;
	
	public Player(String name) {
		
	}
	
}
