package com.osreboot.ridhvl.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.HvlGradient;
import com.osreboot.ridhvl.painter.HvlGradient.Style;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;
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

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(0), HvlFontUtil.DEFAULT, 112, 144);

		try{
			frame = new HvlRenderFrame(1280, 720);
		}catch(FBOUnsupportedException e){
			e.printStackTrace();
		}

		shader = new HvlShader(HvlShader.VERTEX_DEFAULT, HvlShader.FRAGMENT_SIMPLE_NEGATIVE);
	}

	@Override
	public void update(float delta) {
		frame.doCapture(new HvlAction0(){
			@Override
			public void run(){
				HvlPainter2D.hvlDrawQuad(0, 0, 0, 0, gradient);
//				HvlPainter2D.hvlForceRefresh();
//				HvlTextureUtil.getColoredRect(1, 1, Color.transparent).bind();
				HvlPainter2D.hvlDrawQuad(0, 0, 720, 720, gradient);
				HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, gradient);
			}
		});
//		HvlShader.setCurrentShader(shader);
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, frame);
//		HvlShader.setCurrentShader(null);
	}

}
