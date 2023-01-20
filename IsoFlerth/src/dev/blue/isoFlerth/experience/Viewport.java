package dev.blue.isoFlerth.experience;

import java.awt.Color;
import java.awt.Graphics;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.world.Location;
import dev.blue.isoFlerth.world.terrain.Tile;

public class Viewport {
	//private Point focus;
	private Tile[][] view;
	public static int width;
	public static int height;
	public Location topLeft;
	private Camera camera;
	private boolean built = false;
	private Game game;
	//private Tile[][] allTiles;
	//private double tilesFromLeft;
	//private double tilesFromTop;
	
	private double tileWidth, tileHeight;
	
	public Viewport(Game game, Camera camera) {
		//this.focus = camera.getFocus();
		this.game = game;
		this.camera = camera;
		//this.allTiles = level.getTiles();
		//this.view = new Tile[width][height];
		//this.topLeft = new Location((int) (Math.ceil(this.focus.getX() / tileWidth) - width / 2), (int) (Math.ceil(this.focus.getY() / tileHeight) - height / 2));
		//update(this.focus);
	}
	
	public void render(Graphics g) {
		if(!built) {
			return;
		}
		int yOffset = (int)(camera.getTileHeight()*camera.getTilesToEdge()/2)-(int)(camera.getTileHeight()/3);
		int xOffset = (int)(camera.getTileWidth()/2);
		Location renderLocation = new Location(game.getWindow().getWidth()/2-camera.getTileWidth()/2-xOffset, 0-yOffset);//(game.getWindow().getHeight()/2-camera.getTileHeight()/2)
		for(int i = 0; i < view.length; i++) {
			for(int j = 0; j < view[0].length; j++) {
				g.drawImage(view[i][j].getTexture(), (int)renderLocation.getX(), (int)renderLocation.getY(), (int)camera.getTileWidth(), (int)camera.getTileHeight(), null);
				if(game.debug) {
					g.setColor(Color.BLACK);
					g.drawString((int)view[i][j].getCoord().getX()+","+(int)view[i][j].getCoord().getY(), (int)(renderLocation.getX()+camera.getTileWidth()/2)-20, (int)(renderLocation.getY()+camera.getTileHeight()/2)+10);
				}
				renderLocation.add(-(camera.getTileWidth()/2), camera.getTileHeight()/2);
				if(j+1 == view[0].length) {
					renderLocation.setX(game.getWindow().getWidth()/2+camera.getTileWidth()/2+((camera.getTileWidth()*i)/2)-xOffset);
					renderLocation.setY(0-yOffset+((camera.getTileHeight()*i)/2));
				}
			}
		}
	}

	/*public void update(Point focus) {
		this.focus = focus;
		this.topLeft = new Location((int) (Math.ceil(focus.getX() / tileWidth) - width / 2),
				(int) (Math.ceil(focus.getY() / tileHeight) - height / 2));

		buildView();
	}*/

	public Tile[][] getView() {
		return this.view;
	}

	//public Tile getFocusTile() {
		//return level.getTiles()[((int) Math.ceil(this.focus.getX() / 40.0D))][((int) Math.ceil(this.focus.getY() / 40.0D))];
	//}

	//public void setFocus(Point p) {
	//	this.focus = p;
	//}

	/*public double getTilesFromLeft() {
		return this.tilesFromLeft;
	}

	public double getTilesFromTop() {
		return this.tilesFromTop;
	}

	public boolean contains(Location location) {
		return (location.getX() >= this.topLeft.getX()) && (location.getX() < this.topLeft.getX() + width)
				&& (location.getY() >= this.topLeft.getY()) && (location.getY() < this.topLeft.getY() + height);
	}*/
	
	public void setView(Tile[][] tiles) {
		this.view = tiles;
		built = true;
	}

	/*private void buildViewg() {
		int y = (int) this.topLeft.getY();
		for (int i = 0; i < width; y++) {
			this.view[i] = ((Tile[]) Arrays.copyOfRange(this.allTiles[y], (int) this.topLeft.getX(),
					(int) this.topLeft.getX() + height));
			i++;
		}
		this.tilesFromLeft = this.topLeft.getX();
		this.tilesFromTop = this.topLeft.getY();
	}

	private void buildView() {
		int newX = 0;
		int newY = 0;
		for (int y = (int) this.topLeft.getY(); (newY < height) && (y < this.allTiles[0].length); newY++) {
			for (int x = (int) this.topLeft.getX(); (newX < width) && (x < this.allTiles.length); newX++) {
				this.view[newX][newY] = this.allTiles[x][y];
				x++;
			}
			newX = 0;
			y++;
		}
		this.tilesFromLeft = this.topLeft.getX();
		this.tilesFromTop = this.topLeft.getY();
	}*/

	public double getTileWidth() {
		return tileWidth;
	}

	public double getTileHeight() {
		return tileHeight;
	}
}
