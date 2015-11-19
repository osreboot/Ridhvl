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

	private int blockCount;
	private boolean continuousBlocks;
	private float spaceBetweenBlocks;

	private float borderL, borderR, borderU, borderD;

	public HvlProgressBar(float wArg, float hArg, float value, HvlComponentDrawable background,
			HvlComponentDrawable foreground, Direction direction) {
		super(wArg, hArg);
		this.value = value;
		this.background = background;
		this.foreground = foreground;
		this.direction = direction;
		this.blockCount = 1;
		this.continuousBlocks = true;
	}

	public HvlProgressBar(float xArg, float yArg, float wArg, float hArg, float value, HvlComponentDrawable background,
			HvlComponentDrawable foreground, Direction direction) {
		super(xArg, yArg, wArg, hArg);
		this.value = value;
		this.background = background;
		this.foreground = foreground;
		this.direction = direction;
		this.blockCount = 1;
		this.continuousBlocks = true;
	}

	@Override
	public void draw(float delta) {
		if (background != null)
			background.draw(delta, getX(), getY(), getWidth(), getHeight());

		if (foreground != null && value != 0.0f) {
			float valuePerBlock = 1.0f / blockCount;

			switch (direction) {
			case HORIZONTAL:
				float blockTW = (getWidth() - borderL - borderR) / blockCount;

				for (int i = 0; i < blockCount; i++) {
					if (value >= i * valuePerBlock) {
						if (!continuousBlocks || value - i * valuePerBlock >= valuePerBlock) {
							foreground.draw(delta, getX() + (i * blockTW) + (spaceBetweenBlocks / 2) + borderL,
									getY() + borderU, blockTW - spaceBetweenBlocks, getHeight() - borderU - borderD);
						} else {
							float frac = (value % valuePerBlock) / valuePerBlock;

							foreground.draw(delta, getX() + (i * blockTW) + (spaceBetweenBlocks / 2) + borderL,
									getY() + borderU, Math.max((blockTW * frac) - spaceBetweenBlocks, 0),
									getHeight() - borderU - borderD);
						}
					}
				}

				break;
			case VERTICAL:
				float blockTH = (getHeight() - borderU - borderD) / blockCount;

				for (int i = 0; i < blockCount; i++) {
					if (value >= i * valuePerBlock) {
						if (!continuousBlocks || value - i * valuePerBlock >= valuePerBlock) {
							foreground.draw(delta, getX() + borderL,
									getY() + (i * blockTH) + (spaceBetweenBlocks / 2) + borderU,
									getWidth() - borderL - borderR, blockTH - spaceBetweenBlocks);
						} else {
							float frac = (value % valuePerBlock) / valuePerBlock;

							foreground.draw(delta, getX() + borderL,
									getY() + (i * blockTH) + (spaceBetweenBlocks / 2) + borderU,
									getWidth() - borderL - borderR, Math.max((blockTH * frac) - spaceBetweenBlocks, 0));
						}
					}
				}

				break;
			}
		}

		// if (foreground != null) {
		// switch (direction) {
		// case HORIZONTAL:
		// if (invertedDirection)
		// foreground
		// .draw(delta,
		// getX() - offsetR + getWidth()
		// - ((getWidth() - offsetL - offsetR)
		// * Math.min(1.0f, Math.max(0.0f, value))),
		// getY() + offsetU,
		// (getWidth() - offsetL - offsetR) * Math.min(1.0f, Math.max(0.0f,
		// value)),
		// getHeight() - offsetU - offsetD);
		// else
		// foreground.draw(delta, getX() + offsetL, getY() + offsetU,
		// (getWidth() - offsetL - offsetR) * Math.min(1.0f, Math.max(0.0f,
		// value)),
		// getHeight() - offsetU - offsetD);
		// break;
		// case VERTICAL:
		// if (invertedDirection)
		// foreground
		// .draw(delta, getX() + offsetL,
		// getY() - offsetD + getHeight()
		// - ((getHeight() - offsetU - offsetD)
		// * Math.min(1.0f, Math.max(0.0f, value))),
		// getWidth() - offsetL - offsetR,
		// (getHeight() - offsetU - offsetD) * Math.min(1.0f, Math.max(0.0f,
		// value)));
		// else
		// foreground.draw(delta, getX() + offsetL, getY() + offsetU, getWidth()
		// - offsetL - offsetR,
		// (getHeight() - offsetU - offsetD) * Math.min(1.0f, Math.max(0.0f,
		// value)));
		// break;
		// }
		// }
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

	public float getBorderL() {
		return borderL;
	}

	public void setBorderL(float borderL) {
		this.borderL = borderL;
	}

	public float getBorderR() {
		return borderR;
	}

	public void setBorderR(float borderR) {
		this.borderR = borderR;
	}

	public float getBorderU() {
		return borderU;
	}

	public void setBorderU(float borderU) {
		this.borderU = borderU;
	}

	public float getBorderD() {
		return borderD;
	}

	public void setBorderD(float borderD) {
		this.borderD = borderD;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public boolean isContinuousBlocks() {
		return continuousBlocks;
	}

	public void setContinuousBlocks(boolean continuousBlocks) {
		this.continuousBlocks = continuousBlocks;
	}

	public float getSpaceBetweenBlocks() {
		return spaceBetweenBlocks;
	}

	public void setSpaceBetweenBlocks(float spaceBetweenBlocks) {
		this.spaceBetweenBlocks = spaceBetweenBlocks;
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

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public Builder setBorderL(float borderL) {
			tr.setBorderL(borderL);
			return this;
		}

		public Builder setBorderR(float borderR) {
			tr.setBorderR(borderR);
			return this;
		}

		public Builder setBorderU(float borderU) {
			tr.setBorderU(borderU);
			return this;
		}

		public Builder setBorderD(float borderD) {
			tr.setBorderD(borderD);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
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

		public Builder setBlockCount(int blockCount) {
			tr.setBlockCount(blockCount);
			return this;
		}

		public Builder setContinuousBlocks(boolean continuousBlocks) {
			tr.setContinuousBlocks(continuousBlocks);
			return this;
		}

		public Builder setSpaceBetweenBlocks(float spaceBetweenBlocks) {
			tr.setSpaceBetweenBlocks(spaceBetweenBlocks);
			return this;
		}

	}
}
