package com.osreboot.ridhvl;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class HvlDebugUtil {
	public static void drawFPSCounter(HvlFontPainter2D font, float x, float y, float scale, Color color) {
		String text = Math.round(HvlTemplateInteg2D.getNewestInstance().getTimer().getUpdateRate()) + "";
		// (Display.getWidth() * xAlign) - (font.getLineWidth(text) * xAlign), (Display.getHeight() * yAlign) - (font.getFontHeight() * yAlign)
		font.drawWord(text, x, y, color, scale);
	}
}
