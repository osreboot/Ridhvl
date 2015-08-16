package com.osreboot.ridhvl.menu;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.painter.HvlCursor;

public abstract class HvlComponent {

	private float x, y, width, height;

	private boolean enabled, visible, focused;

	private HvlAction2<HvlComponent, Float> updateOverride, drawOverride;

	private static HvlAction2<HvlComponent, Float> universalUpdateOverride, universalDrawOverride;

	protected HvlComponent(float wArg, float hArg) {
		width = wArg;
		height = hArg;
		visible = true;
		enabled = true;
	}

	protected HvlComponent(float xArg, float yArg, float wArg, float hArg) {
		x = xArg;
		y = yArg;
		width = wArg;
		height = hArg;
		visible = true;
		enabled = true;
	}

	public final void metaUpdate(float delta) {
		if (updateOverride != null)
			updateOverride.run(this, delta);
		else if (universalUpdateOverride != null)
			universalUpdateOverride.run(this, delta);
		else
			update(delta);
	}

	public final void metaDraw(float delta) {
		if (drawOverride != null)
			drawOverride.run(this, delta);
		else if (universalDrawOverride != null)
			universalDrawOverride.run(this, delta);
		else
			draw(delta);
	}

	public void update(float delta) {
	}

	public void draw(float delta) {
	}

	public boolean isBeingPressed(int buttonArg) {// TODO account for
													// HvlDisplayMode
		if (!enabled || !HvlMenu.getCurrent().isInteractable())
			return false;

		return isHovering() && Mouse.isButtonDown(buttonArg);
	}

	public boolean isHovering() {// TODO account for HvlDisplayMode
		if (!enabled || !HvlMenu.getCurrent().isInteractable())
			return false;

		return Mouse.isInsideWindow() && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorY() > getY() && HvlCursor.getCursorX() < getX() + getWidth()
				&& HvlCursor.getCursorY() < getY() + getHeight();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focusedArg) {
		focused = focusedArg;
	}

	public HvlAction2<HvlComponent, Float> getUpdateOverride() {
		return updateOverride;
	}

	public void setUpdateOverride(HvlAction2<HvlComponent, Float> updateOverride) {
		this.updateOverride = updateOverride;
	}

	public HvlAction2<HvlComponent, Float> getDrawOverride() {
		return drawOverride;
	}

	public void setDrawOverride(HvlAction2<HvlComponent, Float> drawOverride) {
		this.drawOverride = drawOverride;
	}

	public static HvlAction2<HvlComponent, Float> getUniversalUpdateOverride() {
		return universalUpdateOverride;
	}

	public static void setUniversalUpdateOverride(HvlAction2<HvlComponent, Float> universalUpdateOverride) {
		HvlComponent.universalUpdateOverride = universalUpdateOverride;
	}

	public static HvlAction2<HvlComponent, Float> getUniversalDrawOverride() {
		return universalDrawOverride;
	}

	public static void setUniversalDrawOverride(HvlAction2<HvlComponent, Float> universalDrawOverride) {
		HvlComponent.universalDrawOverride = universalDrawOverride;
	}
}
