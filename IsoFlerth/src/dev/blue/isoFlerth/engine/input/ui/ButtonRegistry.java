package dev.blue.isoFlerth.engine.input.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import dev.blue.isoFlerth.gfx.ui.Button;

public class ButtonRegistry {
	private List<Button> buttons = new ArrayList<Button>();
	private List<Button> hiddenButtons = new ArrayList<Button>();

	public void update() {
		for (Button each : this.buttons) {
			each.update();
		}
	}

	public void render(Graphics g) {
		for (int i = this.buttons.size(); i > 0; i--) {
			((Button) this.buttons.get(i - 1)).render(g);
		}
	}

	public void registerButton(Button button) {
		button.getWhileUpAnim().run();
		this.buttons.add(0, button);
	}

	public void unregisterButton(Button button) {
		button.getWhileDownAnim().cancel();
		button.getWhileUpAnim().cancel();
		button.getWhileHoverAnim().run();
		this.buttons.remove(button);
	}

	public List<Button> getButtons() {
		return this.buttons;
	}
	
	public List<Button> getHiddenButtons() {
		return this.hiddenButtons;
	}

	public void clear() {
		this.buttons.clear();
		this.hiddenButtons.clear();
	}

	public void hideButton(String ID) {
		Button inQuestion = null;
		for (Button each : this.buttons) {
			if (each.getId() == ID) {
				inQuestion = each;
			}
		}
		if ((inQuestion != null) && (!this.hiddenButtons.contains(inQuestion))) {
			this.buttons.remove(inQuestion);
			this.hiddenButtons.add(inQuestion);
		}
	}

	public void revealButton(String ID) {
		Button inQuestion = null;
		for (Button each : this.hiddenButtons) {
			if (each.getId() == ID) {
				inQuestion = each;
			}
		}
		if ((inQuestion != null) && (!this.buttons.contains(inQuestion))) {
			this.hiddenButtons.remove(inQuestion);
			this.buttons.add(inQuestion);
		}
	}
}
