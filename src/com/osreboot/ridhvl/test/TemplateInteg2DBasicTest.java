package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TemplateInteg2DBasicTest extends HvlTemplateInteg2D {

	public TemplateInteg2DBasicTest() {
		super(60, 1280, 720, "Ridhvl TemplateInteg2D Test", new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");

		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(0), HvlFontUtil.DEFAULT, 2048, 2048, 192, 256,
				10);

		HvlCursor.setNativeHidden(true);
		HvlCursor.setTexture(getTextureLoader().getResource(1));
		HvlCursor.setOffset(-6);
		HvlCursor.applyScale(0.5f);
	}

	@Override
	public void update(float delta) {
		HvlCamera.setZoom(2f);

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			System.exit(0);

		float gradient = getNewestInstance().getTimer().getTotalTime() * 1000f % 1280f;

		for (int i = 0; i < 360; i++) {
			hvlDrawQuad(0, i * 2, gradient - (i * 2), 2, new Color(1f, ((float) i - 180) / 180, (float) i / 360));
		}

		hvlRotate(300f, 300f, gradient / 1280f * 360f);
		hvlDrawQuad(250, 250, 100, 100, getTextureLoader().getResource(0));
		hvlResetRotation();

		fontPainter.drawWord("test of the most basic template!", 10, 10, 1260, 100,
				new Color(gradient / 1280f, gradient / 1280f, gradient / 1280f));
		fontPainter.drawWord("and rotation! yay?", 10, 100, 0.5f,
				new Color(1 - (gradient / 1280f), 1 - (gradient / 1280f), 1 - (gradient / 1280f)));
	}

}
