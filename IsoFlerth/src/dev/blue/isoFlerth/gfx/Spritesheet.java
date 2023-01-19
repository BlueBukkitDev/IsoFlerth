package dev.blue.isoFlerth.gfx;

import java.awt.image.BufferedImage;

public class Spritesheet {
	private BufferedImage sheet;
	int spriteWidth;
	int spriteHeight;

	public Spritesheet(BufferedImage sheet, int spriteWidth, int spriteHeight) {
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
	}

	/**
	 *The indices start counting at 1, not 0. Using 0 will result in a RasterFormatException being thrown. 
	 **/
	public BufferedImage getSprite(int xIndex, int yIndex) {
		return this.sheet.getSubimage((xIndex - 1) * this.spriteWidth, (yIndex - 1) * this.spriteHeight, this.spriteWidth, this.spriteHeight);
	}

	public BufferedImage getSheet() {
		return this.sheet;
	}

	public void setSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public int getSpriteWidth() {
		return this.spriteWidth;
	}

	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}

	public int getSpriteHeight() {
		return this.spriteHeight;
	}

	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}
}
