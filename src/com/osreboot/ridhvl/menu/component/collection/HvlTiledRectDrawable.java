package com.osreboot.ridhvl.menu.component.collection;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;

public class HvlTiledRectDrawable extends HvlComponentDrawable {

	private HvlTiledRect rect;
	
	public HvlTiledRectDrawable(HvlTiledRect rectArg) {
		rect = rectArg;
	}

	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		rect.setX(x);
		rect.setY(y);
		rect.setTotalWidth(width);
		rect.setTotalHeight(height);
		rect.draw();
	}

}
