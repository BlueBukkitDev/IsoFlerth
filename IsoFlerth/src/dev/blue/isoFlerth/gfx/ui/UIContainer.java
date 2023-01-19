package dev.blue.isoFlerth.gfx.ui;

import dev.blue.isoFlerth.world.Location;

public class UIContainer {
	private Location location;
	
	/**
	 *Layout determines whether objects will be stacked vertically or horizontally. 0 == vertical layout, 1 == horizontal layout. Padding refers to the space desired between each UIObject. 
	 **/
	public UIContainer(Location location) {
		this.location = location;
	}
	
	/**
	 *Adds an object to the container. A location is required in order to properly place this piece relative to the center of the container. 
	 **/
	public void add(UIObject object, Location location) {
		object.setX((int)(this.location.getX()-(object.width/2)+location.getX()));
		object.setY((int)(this.location.getY()-(object.height/2)+location.getY()));
		object.getAnimDown().setX((int)(this.location.getX()-(object.getAnimDown().getWidth()/2)+location.getX()));
		object.getAnimDown().setY((int)(this.location.getY()-(object.getAnimDown().getHeight()/2)+location.getY()));
		object.getAnimUp().setX((int)(this.location.getX()-(object.getAnimUp().getWidth()/2)+location.getX()));
		object.getAnimUp().setY((int)(this.location.getY()-(object.getAnimUp().getHeight()/2)+location.getY()));
		object.getAnimHover().setX((int)(this.location.getX()-(object.getAnimHover().getWidth()/2)+location.getX()));
		object.getAnimHover().setY((int)(this.location.getY()-(object.getAnimHover().getHeight()/2)+location.getY()));
	}
	
	public Location getLocation() {
		return location;
	}
}
