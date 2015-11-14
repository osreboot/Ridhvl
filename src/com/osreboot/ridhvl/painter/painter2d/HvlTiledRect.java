package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class HvlTiledRect {

	private Texture texture;
	private float x, y;
	private float totalWidth, totalHeight;
	private float horizontalBarHeight, verticalBarWidth;
	private float uvLeft, uvRight;
	private float uvTop, uvBottom;
	private Color color;
	
	private boolean allowSmallDimensions;

	public HvlTiledRect(Texture tArg, float uvLeftArg,
			float uvRightArg, float uvTopArg, float uvBottomArg, float xArg,
			float yArg, float totalWidthArg, float totalHeightArg,
			float horizontalBarHeightArg, float verticalBarWidthArg) {
		this.texture = tArg;
		this.uvLeft = uvLeftArg;
		this.uvRight = uvRightArg;
		this.uvTop = uvTopArg;
		this.uvBottom = uvBottomArg;
		this.x = xArg;
		this.y = yArg;
		this.totalWidth = totalWidthArg;
		this.totalHeight = totalHeightArg;
		this.horizontalBarHeight = horizontalBarHeightArg;
		this.verticalBarWidth = verticalBarWidthArg;
		this.color = Color.white;
	}
	public HvlTiledRect(Texture tArg, float uvTopLeftArg,
			float uvBottomRightArg, float xArg,
			float yArg, float totalWidthArg, float totalHeightArg,
			float horizontalBarHeightArg, float verticalBarWidthArg) {
		this.texture = tArg;
		this.uvLeft = uvTopLeftArg;
		this.uvRight = uvBottomRightArg;
		this.uvTop = uvTopLeftArg;
		this.uvBottom = uvBottomRightArg;
		this.x = xArg;
		this.y = yArg;
		this.totalWidth = totalWidthArg;
		this.totalHeight = totalHeightArg;
		this.horizontalBarHeight = horizontalBarHeightArg;
		this.verticalBarWidth = verticalBarWidthArg;
		this.color = Color.white;
	}

	public void draw() {
		// Upper Left Corner
		HvlPainter2D.hvlDrawQuad(x, y, verticalBarWidth, horizontalBarHeight,
				0, 0, uvLeft, uvTop, texture, color);
		// Top Edge
		HvlPainter2D.hvlDrawQuad(x + verticalBarWidth, y, totalWidth
				- (verticalBarWidth * 2), horizontalBarHeight, uvLeft, 0,
				uvRight, uvTop, texture, color);
		// Upper Right Corner
		HvlPainter2D.hvlDrawQuad(x + totalWidth - verticalBarWidth, y,
				verticalBarWidth, horizontalBarHeight, uvRight, 0, 1.0f, uvTop,
				texture, color);
		// Left Edge
		HvlPainter2D.hvlDrawQuad(x, y + horizontalBarHeight, verticalBarWidth,
				totalHeight - (2 * horizontalBarHeight), 0, uvTop, uvLeft,
				uvBottom, texture, color);
		// Center
		HvlPainter2D.hvlDrawQuad(x + verticalBarWidth, y + horizontalBarHeight,
				totalWidth - (2 * verticalBarWidth), totalHeight
						- (2 * horizontalBarHeight), uvLeft, uvTop, uvRight,
				uvBottom, texture, color);
		// Right Edge
		HvlPainter2D.hvlDrawQuad(x + totalWidth - verticalBarWidth, y
				+ horizontalBarHeight, verticalBarWidth, totalHeight
				- (2 * horizontalBarHeight), uvRight, uvTop, 1.0f, uvBottom, texture,
				color);

		// Lower Left Corner
		HvlPainter2D.hvlDrawQuad(x, y + totalHeight - horizontalBarHeight,
				verticalBarWidth, horizontalBarHeight, 0, uvBottom, uvLeft,
				1.0f, texture, color);

		// Bottom Edge
		HvlPainter2D.hvlDrawQuad(x + verticalBarWidth, y + totalHeight
				- horizontalBarHeight, totalWidth - (2 * verticalBarWidth),
				horizontalBarHeight, uvLeft, uvBottom, uvRight, 1.0f, texture, color);

		// Lower Right Corner
		HvlPainter2D.hvlDrawQuad(x + totalWidth - verticalBarWidth, y
				+ totalHeight - horizontalBarHeight, verticalBarWidth,
				horizontalBarHeight, uvRight, uvBottom, 1.0f, 1.0f, texture, color);
	}

	

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
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

	public float getTotalWidth() {
		return totalWidth;
	}

	public void setTotalWidth(float totalWidth) {
		this.totalWidth = allowSmallDimensions ? totalWidth : Math.max(totalWidth, 2 * verticalBarWidth);
	}

	public float getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(float totalHeight) {
		this.totalHeight = allowSmallDimensions ? totalHeight : Math.max(totalHeight, 2 * horizontalBarHeight);
	}

	public float getHorizontalBarHeight() {
		return horizontalBarHeight;
	}

	public void setHorizontalBarHeight(float horizontalBarHeight) {
		this.horizontalBarHeight = horizontalBarHeight;
	}

	public float getVerticalBarWidth() {
		return verticalBarWidth;
	}

	public void setVerticalBarWidth(float verticalBarWidth) {
		this.verticalBarWidth = verticalBarWidth;
	}

	public float getUvLeft() {
		return uvLeft;
	}

	public void setUvLeft(float uvLeft) {
		this.uvLeft = uvLeft;
	}

	public float getUvRight() {
		return uvRight;
	}

	public void setUvRight(float uvRight) {
		this.uvRight = uvRight;
	}

	public float getUvTop() {
		return uvTop;
	}

	public void setUvTop(float uvTop) {
		this.uvTop = uvTop;
	}

	public float getUvBottom() {
		return uvBottom;
	}

	public void setUvBottom(float uvBottom) {
		this.uvBottom = uvBottom;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public boolean allowsSmallDimensions() {
		return allowSmallDimensions;
	}
	
	public void setAllowSmallDimensions(boolean allowSmallDimensions) {
		this.allowSmallDimensions = allowSmallDimensions;
	}

	
}
