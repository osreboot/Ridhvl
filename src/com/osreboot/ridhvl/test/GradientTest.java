package com.osreboot.ridhvl.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.HvlGradient;
import com.osreboot.ridhvl.painter.HvlGradient.Style;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlShader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class GradientTest extends HvlTemplate2D {
	
	Texture gradient;
	HvlShader shader;
	HvlRenderFrame frame;
	
	public GradientTest() {
		super(60, 1280, 720, "Ridhvl Gradient Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize() {
		HvlGradient g = new HvlGradient(Style.CONIC);
		g.addStop(0, Color.red);
		g.addStop(0.5f, Color.green);
		g.addStop(1, Color.blue);
		gradient = g.toTexture(1024, 1024, 512, 512, 1024, 0);
		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(0), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);

		frame = new HvlRenderFrame(1280, 720);
		HvlPainter2D.TEXMAGBLUR.disable();

		shader = new HvlShader(HvlShader.VERTEX_DEFAULT, HvlShader.FRAGMENT_SIMPLE_NEGATIVE);
	}

	@Override
	public void update(float delta) {
		HvlRenderFrame.setCurrentRenderFrame(frame);
		HvlPainter2D.hvlDrawQuad(0, 0, 0, 0, gradient);
//		HvlPainter2D.hvlForceRefresh();
//		HvlTextureUtil.getColoredRect(1, 1, Color.transparent).bind();
		HvlPainter2D.hvlDrawQuad(0, 0, 720, 720, gradient);
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, gradient);
		HvlRenderFrame.setCurrentRenderFrame(null);
//		HvlShader.setCurrentShader(shader);
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, frame);
//		HvlShader.setCurrentShader(null);
	}

}
