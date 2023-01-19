package dev.blue.isoFlerth.engine.input;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dev.blue.isoFlerth.engine.Game;
import dev.blue.isoFlerth.gfx.ui.Button;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	public Point point = new Point(0, 0);
	//private BufferedImage cursor = Main.getTextures().standardCursor;
	public Object cursorController = null;
	public boolean isInFrame = false;
	public Object clickedObject = null;
	private Game game;
	
	public MouseManager(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		//if ((this.cursorController == null) && (this.isInFrame)) {
			//this.cursor = Main.getTextures().standardCursor;
		//}
		//g.drawImage(this.cursor, (int) this.point.getX() - 17, (int) this.point.getY() - 15, 48, 48, null);
	}

	//public void setCursor(BufferedImage image) {
		//this.cursor = image;
	//}

	public void mouseDragged(MouseEvent e) {
		this.point = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {
		this.point = e.getPoint();
		//if (!game.getState().isBuilding) {
			//for (InputField each : Game.getInstance().getState().getFieldManager().getInputFields()) {
				//each.onMouseMove(this.point);
			//}
			//for (TextArea each : Game.getInstance().getState().getTextAreaManager().getTextAreas()) {
				//each.onMouseMove(this.point);
			//}
			for (Button each : game.getState().getButtonRegistry().getButtons()) {
				each.onMouseMove(e.getPoint());
			}
			game.getState().onMouseMove(e.getPoint());
		//}
	}

	public void mouseClicked(MouseEvent e) {
		
		//for (InputField each : Game.getInstance().getState().getFieldManager().getInputFields()) {
			//if (each.onClick(e.getButton(), e.getPoint())) {
				//return;
			//}
		//}
		//if (Tile.inspected != null) {
			//Tile.inspected.setBeingInspected(false, null);
			//Tile.inspected = null;
		//}
		//if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
			//try {
				//Tile tile = Game.getInstance().getState().getCamera().getTileAt(e.getPoint());
				//Location location = Game.getInstance().getState().getCamera().getTileLocationAt(e.getPoint());
				//tile.setBeingInspected(true, location);
			//} catch (NullPointerException ex) {
				//System.out.println("Caught an NPE");
				//ex.printStackTrace();
				//return;
			//}
		//}
	}

	public void mouseEntered(MouseEvent e) {
		this.isInFrame = true;
		//this.cursor = Main.getTextures().standardCursor;
	}

	public void mouseExited(MouseEvent e) {
		this.isInFrame = false;
		//this.cursor = Main.getTextures().blankCursor;
		for (Button each : game.getState().getButtonRegistry().getButtons()) {
			each.stopHovering();
		}
	}

	public void mousePressed(MouseEvent e) {
		for (Button each : game.getState().getButtonRegistry().getButtons()) {
			if (each.onMouseDown(e.getButton(), e.getPoint())) {
				break;
			}
		}
		game.getState().onMouseDown(e.getButton(), e.getPoint());
	}

	public void mouseReleased(MouseEvent e) {
		for (Button each : game.getState().getButtonRegistry().getButtons()) {
			if (each.onMouseUp(e.getButton(), e.getPoint())) {
				break;
			}
		}
		game.getState().onMouseUp(e.getButton(), e.getPoint());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
}
