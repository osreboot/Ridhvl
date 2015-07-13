package com.osreboot.ridhvl.menu.component;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlListBox extends HvlComponent {

	private HvlSlider scrollBar;
	private HvlButton scrollUpButton, scrollDownButton;
	private HvlArrangerBox scrollBox;
	private HvlFontPainter2D font;
	private HvlComponentDrawable itemBackgroundOff, itemBackgroundHover, itemBackgroundOn;
	private List<Object> items;
	private float itemHeight;
	private int maxVisibleItems;
	private boolean autoSize;
	private float textScale;
	private Color textColor;
	private int selectedIndex;
	private int pSelectedIndex;
	private boolean fullBackground;
	private HvlComponentDrawable background;

	private int sizeIntervalsForScroll;

	private HvlRenderFrame renderFrame;
	private float pX, pY, pWidth, pHeight;
	private boolean isFocused;

	protected HvlListBox(float wArg, float hArg, HvlSlider scrollArg, HvlButton upArg, HvlButton downArg, HvlFontPainter2D fontArg,
			HvlComponentDrawable itemBackgroundOffArg, HvlComponentDrawable itemBackgroundOnArg, float itemHeightArg, int maxVisibleItemsArg) {
		super(wArg, hArg);
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
		scrollBox.add(upArg);
		scrollBox.add(scrollArg);
		scrollBox.add(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		fullBackground = false;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	protected HvlListBox(float wArg, float hArg, HvlSlider scrollArg, HvlButton upArg, HvlButton downArg, HvlFontPainter2D fontArg,
			HvlComponentDrawable itemBackgroundOffArg, HvlComponentDrawable itemBackgroundHoverArg, HvlComponentDrawable itemBackgroundOnArg,
			float itemHeightArg, int maxVisibleItemsArg) {
		super(wArg, hArg);
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
		scrollBox.add(upArg);
		scrollBox.add(scrollArg);
		scrollBox.add(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	protected HvlListBox(float xArg, float yArg, float wArg, float hArg, HvlSlider scrollArg, HvlButton upArg, HvlButton downArg, HvlFontPainter2D fontArg,
			HvlComponentDrawable itemBackgroundOffArg, HvlComponentDrawable itemBackgroundOnArg, float itemHeightArg, int maxVisibleItemsArg) {
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
		scrollBox.add(upArg);
		scrollBox.add(scrollArg);
		scrollBox.add(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		fullBackground = false;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	protected HvlListBox(float xArg, float yArg, float wArg, float hArg, HvlSlider scrollArg, HvlButton upArg, HvlButton downArg, HvlFontPainter2D fontArg,
			HvlComponentDrawable itemBackgroundOffArg, HvlComponentDrawable itemBackgroundHoverArg, HvlComponentDrawable itemBackgroundOnArg,
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
		scrollBox.add(upArg);
		scrollBox.add(scrollArg);
		scrollBox.add(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;

		textScale = 1.0f;
		textColor = Color.white;
		selectedIndex = -1;
		sizeIntervalsForScroll = 10;

		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public void onSelectionChanged(int indexArg, Object selArg) {
	}

	@Override
	public void update(float delta) {
		if (autoSize)
			setHeight(itemHeight * maxVisibleItems);

		if (this.isBeingPressed(0))
			isFocused = true;
		else if (Mouse.isButtonDown(0))
			isFocused = false;

		if (scrollUpButton != null)
			scrollUpButton.setEnabled(!scrollBar.isBeingHeld());

		if (scrollDownButton != null)
			scrollDownButton.setEnabled(!scrollBar.isBeingHeld());

		scrollBar.setEnabled(items.size() > maxVisibleItems);
		if (items.size() - maxVisibleItems <= 0) {
			scrollBar.setSnapInterval(0.0f);
		} else {
			scrollBar.setSnapInterval((1.0f / sizeIntervalsForScroll) / (items.size() - maxVisibleItems));
		}

		layoutUpdate();
		scrollBox.update(delta);

		if (scrollUpButton != null && scrollUpButton.isTriggered())
			scrollBar.setValue(Math.max(scrollBar.getValue() - scrollBar.getSnapInterval() * sizeIntervalsForScroll, 0.0f));
		if (scrollDownButton != null && scrollDownButton.isTriggered())
			scrollBar.setValue(Math.min(scrollBar.getValue() + scrollBar.getSnapInterval() * sizeIntervalsForScroll, 1.0f));
		if (isFocused)
			scrollBar.setValue(scrollBar.getValue() + ((-Mouse.getDWheel() / 120) * scrollBar.getSnapInterval() * sizeIntervalsForScroll));

		scrollBar.setValue(Math.max(Math.min(scrollBar.getValue(), 1.0f), 0.0f));

		float topItem = (scrollBar.getValue() * (items.size() - maxVisibleItems));
		for (int i = 0; i < Math.min(items.size(), topItem + maxVisibleItems); i++) {
			if (Mouse.isButtonDown(0) && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorX() < getX() + getWidth() - scrollBox.getWidth()
					&& HvlCursor.getCursorY() > getY() + ((i - topItem) * itemHeight) && HvlCursor.getCursorY() < getY() + (((i + 1) - topItem) * itemHeight))
				selectedIndex = i;
		}

		if (selectedIndex != pSelectedIndex)
			onSelectionChanged(selectedIndex, getSelectedItem());

		pSelectedIndex = selectedIndex;
	}

	@Override
	public void draw(float delta) {
		if (pX != getX() || pY != getY() || pWidth != getWidth() || pHeight != getHeight()) {
			renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
		}

		pX = getX();
		pY = getY();
		pWidth = getWidth();
		pHeight = getHeight();

		// TODO: an HvlRenderFrame is not required, this can be done through uvs
		// and altering hvlDrawQuad dimensions (more memory efficient)
		HvlRenderFrame.setCurrentRenderFrame(renderFrame);
		if (background != null)
			background.draw(delta, getX(), getY(), fullBackground ? getWidth() : getWidth() - scrollBox.getWidth(), getHeight());

		scrollBox.draw(delta);

		float topItem = (scrollBar.getValue() * (items.size() - maxVisibleItems));
		for (int i = 0; i < Math.min(items.size(), topItem + maxVisibleItems); i++) {
			if (i == selectedIndex)
				itemBackgroundOn.draw(delta, getX(), getY() + ((i - topItem) * itemHeight), getWidth() - scrollBox.getWidth(), itemHeight);
			else if (HvlCursor.getCursorX() > getX() && HvlCursor.getCursorX() < getX() + getWidth() - scrollBox.getWidth()
					&& HvlCursor.getCursorY() > getY() + ((i - topItem) * itemHeight) && HvlCursor.getCursorY() < getY() + (((i + 1) - topItem) * itemHeight))
				itemBackgroundHover.draw(delta, getX(), getY() + ((i - topItem) * itemHeight), getWidth() - scrollBox.getWidth(), itemHeight);
			else
				itemBackgroundOff.draw(delta, getX(), getY() + ((i - topItem) * itemHeight), getWidth() - scrollBox.getWidth(), itemHeight);
			font.drawWord(items.get(i).toString(), getX(), getY() + ((i - topItem) * itemHeight), textScale, textColor);
		}
		HvlPainter2D.hvlForceRefresh();
		HvlRenderFrame.setCurrentRenderFrame(null);

		hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), renderFrame);
	}

	private void layoutUpdate() {
		scrollBox.removeAll();
		scrollBox.add(scrollUpButton);
		scrollBox.add(scrollBar);
		scrollBox.add(scrollDownButton);
		
		scrollBox.setY(getY());
		scrollBox.setWidth(Math.max(Math.max(scrollBar.getWidth(), scrollUpButton == null ? 0 : scrollUpButton.getWidth()), scrollDownButton == null ? 0
				: scrollDownButton.getWidth()));
		scrollBox.setX(getX() + getWidth() - scrollBox.getWidth());
		scrollBox.setHeight(getHeight());
		scrollBar.setHeight(getHeight() - (scrollUpButton == null ? 0 : scrollUpButton.getHeight())
				- (scrollDownButton == null ? 0 : scrollDownButton.getHeight()));
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

	public void setScrollBox(HvlArrangerBox scrollBox) {
		this.scrollBox = scrollBox;
	}

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
	}

	public HvlComponentDrawable getItemBackgroundOff() {
		return itemBackgroundOff;
	}

	public void setItemBackgroundOff(HvlComponentDrawable itemBackgroundOff) {
		this.itemBackgroundOff = itemBackgroundOff;
	}

	public HvlComponentDrawable getItemBackgroundHover() {
		return itemBackgroundHover;
	}

	public void setItemBackgroundHover(HvlComponentDrawable itemBackgroundHover) {
		this.itemBackgroundHover = itemBackgroundHover;
	}

	public HvlComponentDrawable getItemBackgroundOn() {
		return itemBackgroundOn;
	}

	public void setItemBackgroundOn(HvlComponentDrawable itemBackgroundOn) {
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

	public HvlComponentDrawable getBackground() {
		return background;
	}

	public void setBackground(HvlComponentDrawable background) {
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

	public int getItemCount() {
		return items.size();
	}

	public Object getItem(int index) {
		if (index >= items.size() || index < 0)
			return null;

		return items.get(index);
	}
	
	public static class Builder {
		private HvlListBox tr;
		
		public Builder() {
			// TODO: REEEALLLY fill in with default values (this is non-functional at this point)
			tr = new HvlListBox(0, 0, null, null, null, null, null, null, 0.0f, 0);
		}

		public float getX() {
			return tr.getX();
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public float getY() {
			return tr.getY();
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public float getWidth() {
			return tr.getWidth();
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public float getHeight() {
			return tr.getHeight();
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public HvlSlider getScrollBar() {
			return tr.getScrollBar();
		}

		public Builder setScrollBar(HvlSlider scrollBar) {
			tr.setScrollBar(scrollBar);
			return this;
		}

		public HvlButton getScrollUpButton() {
			return tr.getScrollUpButton();
		}

		public Builder setScrollUpButton(HvlButton scrollUpButton) {
			tr.setScrollUpButton(scrollUpButton);
			return this;
		}

		public HvlButton getScrollDownButton() {
			return tr.getScrollDownButton();
		}

		public Builder setScrollDownButton(HvlButton scrollDownButton) {
			tr.setScrollDownButton(scrollDownButton);
			return this;
		}

		public Builder setScrollBox(HvlArrangerBox scrollBox) {
			tr.setScrollBox(scrollBox);
			return this;
		}

		public HvlFontPainter2D getFont() {
			return tr.getFont();
		}

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public HvlComponentDrawable getItemBackgroundOff() {
			return tr.getItemBackgroundOff();
		}

		public Builder setItemBackgroundOff(HvlComponentDrawable itemBackgroundOff) {
			tr.setItemBackgroundOff(itemBackgroundOff);
			return this;
		}

		public HvlComponentDrawable getItemBackgroundHover() {
			return tr.getItemBackgroundHover();
		}

		public Builder setItemBackgroundHover(HvlComponentDrawable itemBackgroundHover) {
			tr.setItemBackgroundHover(itemBackgroundHover);
			return this;
		}

		public HvlComponentDrawable getItemBackgroundOn() {
			return tr.getItemBackgroundOn();
		}

		public Builder setItemBackgroundOn(HvlComponentDrawable itemBackgroundOn) {
			tr.setItemBackgroundOn(itemBackgroundOn);
			return this;
		}

		public float getItemHeight() {
			return tr.getItemHeight();
		}

		public Builder setItemHeight(float itemHeight) {
			tr.setItemHeight(itemHeight);
			return this;
		}

		public int getMaxVisibleItems() {
			return tr.getMaxVisibleItems();
		}

		public Builder setMaxVisibleItems(int maxVisibleItems) {
			tr.setMaxVisibleItems(maxVisibleItems);
			return this;
		}

		public boolean isAutoSize() {
			return tr.isAutoSize();
		}

		public Builder setAutoSize(boolean autoSize) {
			tr.setAutoSize(autoSize);
			return this;
		}

		public float getTextScale() {
			return tr.getTextScale();
		}

		public Builder setTextScale(float textScale) {
			tr.setTextScale(textScale);
			return this;
		}

		public Color getTextColor() {
			return tr.getTextColor();
		}

		public Builder setTextColor(Color textColor) {
			tr.setTextColor(textColor);
			return this;
		}

		public int getSelectedIndex() {
			return tr.getSelectedIndex();
		}

		public Builder setSelectedIndex(int selectedIndex) {
			tr.setSelectedIndex(selectedIndex);
			return this;
		}

		public boolean isFullBackground() {
			return tr.isFullBackground();
		}

		public Builder setFullBackground(boolean fullBackground) {
			tr.setFullBackground(fullBackground);
			return this;
		}

		public HvlComponentDrawable getBackground() {
			return tr.getBackground();
		}

		public Builder setBackground(HvlComponentDrawable background) {
			tr.setBackground(background);
			return this;
		}

		public int getSizeIntervalsForScroll() {
			return tr.getSizeIntervalsForScroll();
		}

		public Builder setSizeIntervalsForScroll(int sizeIntervalsForScroll) {
			tr.setSizeIntervalsForScroll(sizeIntervalsForScroll);
			return this;
		}

		public Object getSelectedItem() {
			return tr.getSelectedItem();
		}

		public Builder setSelectedItem(Object item) {
			tr.setSelectedItem(item);
			return this;
		}

		public Builder addItem(Object item) {
			tr.addItem(item);
			return this;
		}

		public int getItemCount() {
			return tr.getItemCount();
		}

		public Object getItem(int index) {
			return tr.getItem(index);
		}
		
		public HvlListBox build() {
			return tr;
		}
	}
}
