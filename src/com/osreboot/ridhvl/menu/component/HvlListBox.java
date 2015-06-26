package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlListBox extends HvlComponent {

	private HvlSlider scrollBar;
	private HvlButton scrollUpButton, scrollDownButton;
	private HvlArrangerBox scrollBox;
	private HvlFontPainter2D font;
	private Texture itemBackgroundOff, itemBackgroundHover, itemBackgroundOn;
	private List<Object> items;
	private float itemHeight;
	private int maxVisibleItems;
	private boolean autoSize;

	public HvlListBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, HvlSlider scrollArg, HvlButton upArg,
			HvlButton downArg, HvlFontPainter2D fontArg,
			Texture itemBackgroundOffArg, Texture itemBackgroundOnArg, float itemHeightArg,
			int maxVisibleItemsArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		scrollBar = scrollArg;
		scrollUpButton = upArg;
		scrollDownButton = downArg;
		font = fontArg;
		itemBackgroundOff = itemBackgroundOffArg;
		itemBackgroundHover = itemBackgroundOffArg;
		itemBackgroundOn = itemBackgroundOnArg;
		itemHeight = itemHeightArg;
		maxVisibleItems = maxVisibleItemsArg;
		
		scrollBox = new HvlArrangerBox(0, 0, 0, 0, heightInversionArg,
				ArrangementStyle.VERTICAL);
		scrollBox.addChild(upArg);
		scrollBox.addChild(scrollArg);
		scrollBox.addChild(downArg);
		scrollBox.setAlign(0.5f);
		items = new LinkedList<>();
		autoSize = false;
	}

	@Override
	public void update(float delta) {
		if (autoSize)
			setHeight(itemHeight * maxVisibleItems);
		
		scrollUpButton.setEnabled(!scrollBar.isBeingHeld());
		scrollDownButton.setEnabled(!scrollBar.isBeingHeld());
		
		scrollBar.setEnabled(items.size() > maxVisibleItems);
		if (items.size() == 0)
		{
			scrollBar.setSnapInterval(0.0f);
		}
		else
		{
			scrollBar.setSnapInterval(1.0f / (items.size() - maxVisibleItems));
		}
		
		layoutUpdate();
		scrollBox.update(delta);
		
	}

	@Override
	public void draw(float delta) {
		scrollBox.draw(delta);
		
		int topItem = (int)(scrollBar.getValue() * items.size());
		
		for (int i = topItem; i < Math.min(items.size(), topItem + maxVisibleItems); i++)
		{
			
		}
	}

	private void layoutUpdate() {
		scrollBox.setY(getY());
		scrollBox.setWidth(Math.max(
				Math.max(scrollBar.getWidth(), scrollUpButton.getWidth()),
				scrollDownButton.getWidth()));
		scrollBox.setX(getX() + getWidth() - scrollBox.getWidth());
		scrollBox.setHeight(getHeight());
		scrollBar.setHeight(getHeight() - scrollUpButton.getHeight() - scrollDownButton.getHeight());
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

	public void addItem(Object item)
	{
		items.add(item);
	}
	
	public void removeItem(Object item)
	{
		items.remove(item);
	}
}
