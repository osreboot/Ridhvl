package com.osreboot.ridhvl.menu.component.collection;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlSlider;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureListBox extends HvlComponent {

	private HvlSlider scrollBar;
	private HvlButton scrollUpButton, scrollDownButton;
	private HvlArrangerBox scrollBox;
	private HvlFontPainter2D font;
	private Texture itemBackgroundOff, itemBackgroundHover, itemBackgroundOn;
	private List<Object> items;
	private float itemHeight;
	private int maxVisibleItems;
	private boolean autoSize;
	private float textScale;
	private Color textColor;
	private int selectedIndex;
	private boolean fullBackground;
	private Texture background;

	private int sizeIntervalsForScroll;

	private HvlRenderFrame renderFrame;
	private float pX, pY, pWidth, pHeight;
	private boolean isFocused;

	public HvlTextureListBox(float xArg, float yArg, float wArg, float hArg,
			HvlSlider scrollArg, HvlButton upArg, HvlButton downArg,
			HvlFontPainter2D fontArg, Texture itemBackgroundOffArg,
			Texture itemBackgroundOnArg, float itemHeightArg,
			int maxVisibleItemsArg) {
		super(xArg, yArg, wArg, hArg);
		scrollBar = scrollArg;
		scrollUpButton = upArg;
		scrollDownButton = downArg;
		font = fontArg;
		itemBackgroundOff = itemBackgroundOffArg;
		itemBackgroundHover = itemBackgroundOffArg;
		itemBackgroundOn = itemBackgroundOnArg;
		itemHeight = itemHeightArg;
		maxVisibleItems = maxVisibleItemsArg;

		scrollBox = new HvlArrangerBox(0, 0, 0, 0, ArrangementStyle.VERTICAL);
		scrollBox.addChild(upArg);
		scrollBox.addChild(scrollArg);
		scrollBox.addChild(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		fullBackground = false;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT,
				(int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public HvlTextureListBox(float xArg, float yArg, float wArg, float hArg,
			HvlSlider scrollArg, HvlButton upArg, HvlButton downArg,
			HvlFontPainter2D fontArg, Texture itemBackgroundOffArg,
			Texture itemBackgroundHoverArg, Texture itemBackgroundOnArg,
			float itemHeightArg, int maxVisibleItemsArg) {
		super(xArg, yArg, wArg, hArg);
		scrollBar = scrollArg;
		scrollUpButton = upArg;
		scrollDownButton = downArg;
		font = fontArg;
		itemBackgroundOff = itemBackgroundOffArg;
		itemBackgroundHover = itemBackgroundHoverArg;
		itemBackgroundOn = itemBackgroundOnArg;
		itemHeight = itemHeightArg;
		maxVisibleItems = maxVisibleItemsArg;

		scrollBox = new HvlArrangerBox(0, 0, 0, 0, ArrangementStyle.VERTICAL);
		scrollBox.addChild(upArg);
		scrollBox.addChild(scrollArg);
		scrollBox.addChild(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT,
				(int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	@Override
	public void update(float delta) {
		if (autoSize)
			setHeight(itemHeight * maxVisibleItems);

		if (this.isBeingPressed(0))
			isFocused = true;
		else if (Mouse.isButtonDown(0))
			isFocused = false;

		scrollUpButton.setEnabled(!scrollBar.isBeingHeld());
		scrollDownButton.setEnabled(!scrollBar.isBeingHeld());

		scrollBar.setEnabled(items.size() > maxVisibleItems);
		if (items.size() - maxVisibleItems <= 0) {
			scrollBar.setSnapInterval(0.0f);
		} else {
			scrollBar.setSnapInterval((1.0f / sizeIntervalsForScroll)
					/ (items.size() - maxVisibleItems));
		}

		layoutUpdate();
		scrollBox.update(delta);

		if (scrollUpButton.isTriggered())
			scrollBar.setValue(Math.max(
					scrollBar.getValue() - scrollBar.getSnapInterval()
							* sizeIntervalsForScroll, 0.0f));
		if (scrollDownButton.isTriggered())
			scrollBar.setValue(Math.min(
					scrollBar.getValue() + scrollBar.getSnapInterval()
							* sizeIntervalsForScroll, 1.0f));
		if (isFocused)
			scrollBar
					.setValue(scrollBar.getValue()
							+ ((-Mouse.getDWheel() / 120)
									* scrollBar.getSnapInterval() * sizeIntervalsForScroll));

		scrollBar
				.setValue(Math.max(Math.min(scrollBar.getValue(), 1.0f), 0.0f));

		float topItem = (scrollBar.getValue() * (items.size() - maxVisibleItems));
		for (int i = 0; i < Math.min(items.size(), topItem + maxVisibleItems); i++) {
			if (Mouse.isButtonDown(0)
					&& HvlCursor.getCursorX() > getX()
					&& HvlCursor.getCursorX() < getX() + getWidth()
							- scrollBox.getWidth()
					&& HvlCursor.getCursorY() > getY()
							+ ((i - topItem) * itemHeight)
					&& HvlCursor.getCursorY() < getY()
							+ (((i + 1) - topItem) * itemHeight))
				selectedIndex = i;
		}
	}

	@Override
	public void draw(float delta) {
		if (pX != getX() || pY != getY() || pWidth != getWidth()
				|| pHeight != getHeight()) {
			renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT,
					(int) getX(), (int) getY(), (int) getWidth(),
					(int) getHeight());
		}

		pX = getX();
		pY = getY();
		pWidth = getWidth();
		pHeight = getHeight();

		// TODO: an HvlRenderFrame is not required, this can be done through uvs
		// and altering hvlDrawQuad dimensions (more memory efficient)
		HvlRenderFrame.setCurrentRenderFrame(renderFrame);
		if (background != null)
			HvlPainter2D.hvlDrawQuad(
					getX(),
					getY(),
					fullBackground ? getWidth() : getWidth()
							- scrollBox.getWidth(), getHeight(), background);

		scrollBox.draw(delta);

		float topItem = (scrollBar.getValue() * (items.size() - maxVisibleItems));
		for (int i = 0; i < Math.min(items.size(), topItem + maxVisibleItems); i++) {
			if (i == selectedIndex)
				HvlPainter2D.hvlDrawQuad(getX(), getY()
						+ ((i - topItem) * itemHeight),
						getWidth() - scrollBox.getWidth(), itemHeight,
						itemBackgroundOn, Color.white);
			else if (HvlCursor.getCursorX() > getX()
					&& HvlCursor.getCursorX() < getX() + getWidth()
							- scrollBox.getWidth()
					&& HvlCursor.getCursorY() > getY()
							+ ((i - topItem) * itemHeight)
					&& HvlCursor.getCursorY() < getY()
							+ (((i + 1) - topItem) * itemHeight))
				HvlPainter2D.hvlDrawQuad(getX(), getY()
						+ ((i - topItem) * itemHeight),
						getWidth() - scrollBox.getWidth(), itemHeight,
						itemBackgroundHover, Color.white);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY()
						+ ((i - topItem) * itemHeight),
						getWidth() - scrollBox.getWidth(), itemHeight,
						itemBackgroundOff, Color.white);
			font.hvlDrawWord(items.get(i).toString(), getX(), getY()
					+ ((i - topItem) * itemHeight), textScale, textColor);
		}
		HvlPainter2D.hvlDrawQuad(0f, 0f, 0f, 0f, itemBackgroundOff,
				Color.transparent);// TODO: WHY DO I NEED TO DO THIS???
		HvlRenderFrame.setCurrentRenderFrame(null);

		hvlDrawQuad(getX(), getY() + getHeight(), getWidth(), -getHeight(),
				renderFrame.getTextureID());
	}

	private void layoutUpdate() {
		scrollBox.setY(getY());
		scrollBox.setWidth(Math.max(
				Math.max(scrollBar.getWidth(), scrollUpButton.getWidth()),
				scrollDownButton.getWidth()));
		scrollBox.setX(getX() + getWidth() - scrollBox.getWidth());
		scrollBox.setHeight(getHeight());
		scrollBar.setHeight(getHeight() - scrollUpButton.getHeight()
				- scrollDownButton.getHeight());
	}

	public HvlSlider getScrollBar() {
		return scrollBar;
	}

	public void setScrollBar(HvlSlider scrollBar) {
		this.scrollBar = scrollBar;
	}

	public HvlButton getScrollUpButton() {
		return scrollUpButton;
	}

	public void setScrollUpButton(HvlButton scrollUpButton) {
		this.scrollUpButton = scrollUpButton;
	}

	public HvlButton getScrollDownButton() {
		return scrollDownButton;
	}

	public void setScrollDownButton(HvlButton scrollDownButton) {
		this.scrollDownButton = scrollDownButton;
	}

	public HvlArrangerBox getScrollBox() {
		return scrollBox;
	}

	public void setScrollBox(HvlArrangerBox scrollBox) {
		this.scrollBox = scrollBox;
	}

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
	}

	public Texture getItemBackgroundOff() {
		return itemBackgroundOff;
	}

	public void setItemBackgroundOff(Texture itemBackgroundOff) {
		this.itemBackgroundOff = itemBackgroundOff;
	}

	public Texture getItemBackgroundHover() {
		return itemBackgroundHover;
	}

	public void setItemBackgroundHover(Texture itemBackgroundHover) {
		this.itemBackgroundHover = itemBackgroundHover;
	}

	public Texture getItemBackgroundOn() {
		return itemBackgroundOn;
	}

	public void setItemBackgroundOn(Texture itemBackgroundOn) {
		this.itemBackgroundOn = itemBackgroundOn;
	}

	public float getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(float itemHeight) {
		this.itemHeight = itemHeight;
	}

	public int getMaxVisibleItems() {
		return maxVisibleItems;
	}

	public void setMaxVisibleItems(int maxVisibleItems) {
		this.maxVisibleItems = maxVisibleItems;
	}

	public boolean isAutoSize() {
		return autoSize;
	}

	public void setAutoSize(boolean autoSize) {
		this.autoSize = autoSize;
	}

	public float getTextScale() {
		return textScale;
	}

	public void setTextScale(float textScale) {
		this.textScale = textScale;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public boolean isFullBackground() {
		return fullBackground;
	}

	public void setFullBackground(boolean fullBackground) {
		this.fullBackground = fullBackground;
	}

	public Texture getBackground() {
		return background;
	}

	public void setBackground(Texture background) {
		this.background = background;
	}

	public int getSizeIntervalsForScroll() {
		return sizeIntervalsForScroll;
	}

	public void setSizeIntervalsForScroll(int sizeIntervalsForScroll) {
		this.sizeIntervalsForScroll = sizeIntervalsForScroll;
	}

	public Object getSelectedItem() {
		if (selectedIndex < 0 || selectedIndex >= items.size())
			return null;
		return items.get(selectedIndex);
	}

	public void setSelectedItem(Object item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == item) {
				selectedIndex = i;
				return;
			}
		}
	}

	public void addItem(Object item) {
		items.add(item);
	}

	public void removeItem(Object item) {
		items.remove(item);
	}
}
