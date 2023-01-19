package dev.blue.isoFlerth.gfx.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dev.blue.isoFlerth.gfx.Animation;

public abstract class UIObject {
	protected int height;
	protected int width;
	protected int y;
	protected int x;
	protected Rectangle bounds;
	protected String id;
	protected boolean hovering = false;
	protected boolean selected = false;
	protected Color color;
	protected BufferedImage texture;
	protected Animation whileDown;
	protected Animation whileUp;
	protected Animation whileHover;

	protected void runClick() {
	}

	protected void runMouseDown() {
	}

	protected void runMouseUp() {
	}

	protected void runOnHover() {
	}

	protected void runOnStopHover() {
	}

	public abstract void render(Graphics paramGraphics);

	public abstract void update();

	public abstract boolean onClick(int paramInt, Point paramPoint);

	public abstract void onType(KeyEvent paramKeyEvent);

	public abstract void onKeyPressed(KeyEvent paramKeyEvent);

	public abstract void onMouseMove(Point paramPoint);

	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
		this.bounds = new Rectangle(x, this.y, this.width, this.height);
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
		this.bounds = new Rectangle(this.x, y, this.width, this.height);
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.bounds = new Rectangle(this.x, this.y, width, this.height);
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.bounds = new Rectangle(this.x, this.y, this.width, height);
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
		if (hovering = false) {
			runOnStopHover();
		} else if (hovering = true) {
			runOnHover();
		}
	}

	public void stopHovering() {
		this.hovering = false;
		runOnStopHover();
	}

	protected boolean isHovering() {
		return this.hovering;
	}

	public boolean isSelected() {
		return this.selected;
	}
	
	public Animation getAnimUp() {
		return whileUp;
	}
	
	public Animation getAnimDown() {
		return whileDown;
	}
	
	public Animation getAnimHover() {
		return whileHover;
	}
}
