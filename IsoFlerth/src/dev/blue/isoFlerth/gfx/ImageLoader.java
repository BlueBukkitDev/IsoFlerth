package dev.blue.isoFlerth.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	/**
	 *Uses IOImage to read the resource at the given path. Catches illegal arguments and returns null, so don't expect a printout. 
	 *The res folder and following forward-slash are prefixed by default. 
	 **/
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			return null;
		}
		return null;
	}
}
