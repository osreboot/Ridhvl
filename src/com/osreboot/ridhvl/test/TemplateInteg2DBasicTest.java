package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TemplateInteg2DBasicTest extends HvlTemplateInteg2D {
	public TemplateInteg2DBasicTest() {
		super(60, 1280, 720, "Ridhvl TemplateInteg2D Test", new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D font;

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");

		font = new HvlFontPainter2D(getTextureLoader().getResource(0), HvlFontUtil.DEFAULT, 192, 256, 10, 0.25f);

		HvlCursor.setNativeHidden(true);
		HvlCursor.setTexture(getTextureLoader().getResource(1));
		HvlCursor.setOffset(-6);
		HvlCursor.applyScale(0.5f);
	}

	@Override
	public void update(float delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			System.exit(0);

		float gradient = getNewestInstance().getTimer().getTotalTime() * 1000f % 1280f;

		for (int i = 0; i < 360; i++) {
			hvlDrawQuad(0, i * 2, gradient - (i * 2), 2, new Color(1f, ((float) i - 180) / 180, (float) i / 360));
		}

		hvlRotate(300f, 300f, gradient / 1280f * 360f);
		hvlDrawQuad(250, 250, 100, 100, getTextureLoader().getResource(0));
		hvlResetRotation();

		String text = "hello hi\nhows it going?\nits going good.\nniiiice!";
		font.drawWordc(text, Display.getWidth() / 2, Display.getHeight() / 2, Color.white, 1f, 1f, 0.0f);
//		fontPainter.drawWord("test of the most\nbasic\ntemplate!", 10, 10, 200, 100,
//				new Color(gradient / 1280f, gradient / 1280f, gradient / 1280f));
//		fontPainter.drawWord("and rotation!\nyay?", 10, 150, 1f,
//				new Color(1 - (gradient / 1280f), 1 - (gradient / 1280f), 1 - (gradient / 1280f)));
	}

}
