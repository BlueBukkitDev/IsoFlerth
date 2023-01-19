package dev.blue.isoFlerth.world;

/**
 *Location is intended to be used as the center-point of an object, image, container, or other. 
 **/
public class Location {
	private double x;
	private double y;

	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location location) {
		this.x = location.getX();
		this.y = location.getY();
	}

	public Location subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
		return new Location(this.x, this.y);
	}

	public Location add(double x, double y) {
		this.x += x;
		this.y += y;
		return new Location(this.x, this.y);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double distance(Location loc) {
		if (loc == null) {
			return 0.0D;
		}
		return Math.hypot(Math.abs(loc.getX() - this.x), Math.abs(loc.getY() - this.y));
	}
}
