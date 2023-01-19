package dev.blue.isoFlerth.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.blue.isoFlerth.world.Location;

public class Animation {
	private BufferedImage[] images;
	private boolean isRunning = false;
	private BufferedImage frame;
	private boolean terminated;
	private Location loc;
	private int width;
	private int height;
	private int inc;
	private int counter;
	private int index;

	/**
	 *@param inc - an integer representing how many updates should pass per frame
	 *@param loc - an XY location for the top-left corner of the animation. Use a UIContainer to center 
	 *@param width - the width that the textures will be stretched or compressed to
	 *@param height - the height that the textures will be stretched or compressed to
	 *@param images - an array of BufferedImages that represent the cycle of this animation. They will play in order of array position. 
	 **/
	public Animation(int inc, Location loc, int width, int height, BufferedImage... images) {
		this.images = images;
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.inc = inc;
		this.counter = 0;
		this.index = 0;
		this.frame = images[this.index];
		this.terminated = false;
	}

	public BufferedImage[] getImages() {
		return this.images;
	}

	public BufferedImage getFrame(int index) {
		return this.images[index];
	}

	public void render(Graphics g) {
		onRender();
		if (this.isRunning) {
			g.drawImage(this.frame, (int) this.loc.getX(), (int) this.loc.getY(), this.width, this.height, null);
		}
	}

	public void update() {
		this.frame = this.images[this.index];
		this.counter += 1;
		if (this.counter > this.inc) {
			this.counter = 0;
			this.index += 1;
			if (this.index >= this.images.length) {
				if (this.terminated) {
					cancel();
					onEnd();
				} else {
					this.index = 0;
				}
			}
		}
	}

	public void onRender() {
	}

	public void run() {
		this.isRunning = true;
		this.terminated = false;
	}

	public void end() {
		this.terminated = true;
	}

	public void cancel() {
		this.isRunning = false;
	}

	public void onEnd() {
	}

	public double getX() {
		return this.loc.getX();
	}

	public void setX(double x) {
		this.loc.setX(x);
	}

	public double getY() {
		return this.loc.getY();
	}

	public void setY(double y) {
		this.loc.setY(y);
	}

	public Location getLocation() {
		return this.loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean isRunning() {
		return this.isRunning;
	}
}
