package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlProgressBar extends HvlComponent {

	public static enum Direction {
		HORIZONTAL, VERTICAL
	}

	private float value;

	private HvlComponentDrawable background;
	private HvlComponentDrawable foreground;

	private Direction direction;
	private boolean invertedDirection;

	private float offsetL, offsetR, offsetU, offsetD;

	public HvlProgressBar(float wArg, float hArg, float value, HvlComponentDrawable background,
			HvlComponentDrawable foreground, Direction direction) {
		super(wArg, hArg);
		this.value = value;
		this.background = background;
		this.foreground = foreground;
		this.direction = direction;
	}

	public HvlProgressBar(float xArg, float yArg, float wArg, float hArg, float value, HvlComponentDrawable background,
			HvlComponentDrawable foreground, Direction direction) {
		super(xArg, yArg, wArg, hArg);
		this.value = value;
		this.background = background;
		this.foreground = foreground;
		this.direction = direction;
	}

	@Override
	public void draw(float delta) {
		if (background != null)
			background.draw(delta, getX(), getY(), getWidth(), getHeight());

		if (foreground != null) {
			switch (direction) {
			case HORIZONTAL:
				if (invertedDirection)
					foreground
							.draw(delta,
									getX() - offsetR + getWidth()
											- ((getWidth() - offsetL - offsetR)
													* Math.min(1.0f, Math.max(0.0f, value))),
									getY() + offsetU,
									(getWidth() - offsetL - offsetR) * Math.min(1.0f, Math.max(0.0f, value)),
									getHeight() - offsetU - offsetD);
				else
					foreground.draw(delta, getX() + offsetL, getY() + offsetU,
							(getWidth() - offsetL - offsetR) * Math.min(1.0f, Math.max(0.0f, value)),
							getHeight() - offsetU - offsetD);
				break;
			case VERTICAL:
				if (invertedDirection)
					foreground
							.draw(delta, getX() + offsetL,
									getY() - offsetD + getHeight()
											- ((getHeight() - offsetU - offsetD)
													* Math.min(1.0f, Math.max(0.0f, value))),
									getWidth() - offsetL - offsetR,
									(getHeight() - offsetU - offsetD) * Math.min(1.0f, Math.max(0.0f, value)));
				else
					foreground.draw(delta, getX() + offsetL, getY() + offsetU,
							getWidth() - offsetL - offsetR,
							(getHeight() - offsetU - offsetD) * Math.min(1.0f, Math.max(0.0f, value)));
				break;
			}
		}
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public HvlComponentDrawable getBackground() {
		return background;
	}

	public void setBackground(HvlComponentDrawable background) {
		this.background = background;
	}

	public HvlComponentDrawable getForeground() {
		return foreground;
	}

	public void setForeground(HvlComponentDrawable foreground) {
		this.foreground = foreground;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isInvertedDirection() {
		return invertedDirection;
	}

	public void setInvertedDirection(boolean invertedDirection) {
		this.invertedDirection = invertedDirection;
	}

	public float getOffsetL() {
		return offsetL;
	}

	public void setOffsetL(float offsetL) {
		this.offsetL = offsetL;
	}

	public float getOffsetR() {
		return offsetR;
	}

	public void setOffsetR(float offsetR) {
		this.offsetR = offsetR;
	}

	public float getOffsetU() {
		return offsetU;
	}

	public void setOffsetU(float offsetU) {
		this.offsetU = offsetU;
	}

	public float getOffsetD() {
		return offsetD;
	}

	public void setOffsetD(float offsetD) {
		this.offsetD = offsetD;
	}

	public static class Builder {
		HvlProgressBar tr;
		
		public Builder() {
			tr = new HvlProgressBar(0, 0, 0.0f, null, null, Direction.HORIZONTAL);
			if (HvlComponentDefault.hasDefault(HvlProgressBar.class))
				tr = HvlComponentDefault.getDefault(HvlProgressBar.class).cloneComponent(tr);
		}
		
		public HvlProgressBar build() {
			return tr;
		}

		public Builder setValue(float value) {
			tr.setValue(value);
			return this;
		}

		public Builder setBackground(HvlComponentDrawable background) {
			tr.setBackground(background);
			return this;
		}

		public Builder setForeground(HvlComponentDrawable foreground) {
			tr.setForeground(foreground);
			return this;
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public Builder setDirection(Direction direction) {
			tr.setDirection(direction);
			return this;
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public Builder setInvertedDirection(boolean inverted) {
			tr.setInvertedDirection(inverted);
			return this;
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public Builder setOffsetL(float offsetL) {
			tr.setOffsetL(offsetL);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public Builder setOffsetR(float offsetR) {
			tr.setOffsetR(offsetR);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setOffsetU(float offsetU) {
			tr.setOffsetU(offsetU);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setOffsetD(float offsetD) {
			tr.setOffsetD(offsetD);
			return this;
		}

		public Builder setUpdateOverride(HvlAction2<HvlComponent, Float> updateOverride) {
			tr.setUpdateOverride(updateOverride);
			return this;
		}

		public Builder setDrawOverride(HvlAction2<HvlComponent, Float> drawOverride) {
			tr.setDrawOverride(drawOverride);
			return this;
		}
	}
}
