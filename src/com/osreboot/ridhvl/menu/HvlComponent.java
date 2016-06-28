package com.osreboot.ridhvl.menu;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.reflect.HvlDoNotClone;
import com.osreboot.ridhvl.painter.HvlCursor;

public abstract class HvlComponent {

	private float x, y;
	private float width, height;

	private boolean enabled, visible;

	private HvlAction2<HvlComponent, Float> updateOverride, drawOverride;

	private static HvlAction2<HvlComponent, Float> universalUpdateOverride, universalDrawOverride;

	private HvlComponentContainer container;

	protected HvlComponent(float wArg, float hArg) {
		x = 0;
		y = 0;
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

	public boolean isBeingPressed(int buttonArg) {
		if (!enabled || !HvlMenu.getCurrent().isInteractable())
			return false;

		return (isHovering() && Mouse.isButtonDown(buttonArg)) || HvlMenuInteractor.isBeingClicked(this);
	}

	public boolean isHovering() {
		if (!enabled || !HvlMenu.getCurrent().isInteractable())
			return false;

		return (Mouse.isInsideWindow() && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorY() > getY()
				&& HvlCursor.getCursorX() < getX() + getWidth() && HvlCursor.getCursorY() < getY() + getHeight()) || HvlMenuInteractor.isHovering(this);
	}

	public <T> T cloneComponent(T cloneTo) {
		List<Field> fields = new LinkedList<>();

		Class<?> currentClass = getClass();

		while (!currentClass.equals(HvlComponent.class.getSuperclass())) {
			for (Field f : currentClass.getDeclaredFields()) {
				if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())
						|| f.isAnnotationPresent(HvlDoNotClone.class))
					continue;

				f.setAccessible(true);
				fields.add(f);
			}
			currentClass = currentClass.getSuperclass();
		}

		for (Field f : fields) {
			try {
				f.set(cloneTo, f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// This shouldn't be able to happen:
				// see above - "f.setAccessible(true)"
			}
		}

		return specialClone(cloneTo);
	}

	/**
	 * Used for any special cloning that can't be handled normally (like
	 * recursive cloning)
	 */
	protected <T> T specialClone(T cloneTo) {
		return cloneTo;
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

	public void setPosition(float x, float y) {
		this.x = x;
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

	public void setDimensions(float width, float height) {
		this.width = width;
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

	public HvlComponentContainer getContainer() {
		return container;
	}

	public void setContainer(HvlComponentContainer container) {
		this.container = container;
	}
}
