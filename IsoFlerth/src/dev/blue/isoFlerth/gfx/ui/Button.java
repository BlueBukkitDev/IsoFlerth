package dev.blue.isoFlerth.gfx.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.blue.isoFlerth.gfx.Animation;

public class Button extends UIObject {
	private int tooltipTimer = 0;
	private boolean showTooltip = false;
	private boolean useTooltip = false;
	private boolean showingClicked = false;
	private boolean showID;
	private int fontSize;

	/**
	 *Button will not render unless the associated Animation is run. 
	 **/
	public Button(String id, boolean showID, boolean useTooltip, int fontSize, int x, int y, int width, int height,
			int animationSpeed, Animation whileDown, Animation whileUp, Animation whileHover) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.whileDown = whileDown;
		this.whileUp = whileUp;
		this.whileHover = whileHover;
		this.bounds = new Rectangle(x, y, width, height);
		this.showID = showID;
		this.fontSize = fontSize;
		this.useTooltip = useTooltip;
	}

	public void render(Graphics g) {
		animate();
		this.whileUp.render(g);
		this.whileDown.render(g);
		this.whileHover.render(g);
		if (this.showID) {
			g.setFont(new Font("Helvetica", 1, this.fontSize));
			g.setColor(Color.BLACK);
			g.drawString(this.id, this.x + (this.width / 2 - g.getFontMetrics().stringWidth(this.id) / 2),
					(int) (this.y + this.height / 2 + g.getFontMetrics().getHeight() / 3.5D));
		}
		if ((this.showTooltip) && (this.useTooltip)) {
			g.setFont(new Font("Helvetica", 0, 12));
			g.setColor(Color.GRAY);
			g.drawString(this.id, this.x, this.y + g.getFontMetrics().getHeight() * 2);
		}
	}

	public void update() {
		if (this.hovering) {
			this.tooltipTimer += 1;
		} else if (this.showingClicked) {
			this.showingClicked = false;
		}
		if (this.tooltipTimer >= 50) {
			this.showTooltip = true;
		}
	}

	private void animate() {
		if(this.showingClicked) {
			if(!this.whileDown.isRunning()) {
				this.whileUp.cancel();
				this.whileHover.cancel();
				this.whileDown.run();
				return;
			}
		}else if(this.hovering) {
			if(!this.whileHover.isRunning()) {
				this.whileUp.cancel();
				this.whileDown.cancel();
				this.whileHover.run();
				return;
			}
		} else if(!this.whileUp.isRunning()) {
			this.whileDown.cancel();
			this.whileHover.cancel();
			this.whileUp.run();
			return;
		}
	}

	public void onMouseMove(Point p) {
		if (this.bounds.contains(p)) {
			if (!this.hovering) {
				this.hovering = true;
				runOnHover();
			}
		} else {
			if(this.hovering) {
				this.hovering = false;
				this.showTooltip = false;
				this.tooltipTimer = 0;
				runOnStopHover();
			}
		}
	}

	public boolean onClick(int button, Point p) {
		if (this.bounds.contains(p)) {
			runClick();
			return true;
		}
		return false;
	}

	public boolean onMouseDown(int button, Point p) {
		if (this.bounds.contains(p)) {
			//Game.getInstance().getMouseManager().clickedObject = this;
			runMouseDown();
			this.showingClicked = true;
			return true;
		}
		return false;
	}

	public boolean onMouseUp(int button, Point p) {
		/*if (Game.getInstance().getMouseManager().clickedObject == this) {
			Game.getInstance().getMouseManager().clickedObject = null;
		}*/
		if (this.showingClicked) {
			this.showingClicked = false;
			if (this.bounds.contains(p)) {
				runMouseUp();
				onClick(button, p);
				return true;
			}
			return false;
		}
		return false;
	}

	public void onType(KeyEvent e) {
	}

	public void onKeyPressed(KeyEvent e) {
	}

	public Animation getWhileUpAnim() {
		return this.whileUp;
	}

	public Animation getWhileDownAnim() {
		return this.whileDown;
	}
	
	public Animation getWhileHoverAnim() {
		return this.whileHover;
	}

	public void setWhileUpAnim(Animation whileUp) {
		this.whileUp = whileUp;
	}

	public void setWhileDownAnim(Animation whileDown) {
		this.whileDown = whileDown;
	}
	
	public void setWhileHoverAnim(Animation whileHover) {
		this.whileHover = whileHover;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
