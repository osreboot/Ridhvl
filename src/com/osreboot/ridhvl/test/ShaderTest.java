package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.HvlShader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class ShaderTest extends HvlTemplateInteg2D{

	public ShaderTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;
	
	HvlShader shader;
	HvlRenderFrame frame;
	HvlAnimatedTexture texture;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("White");
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");
		getTextureLoader().loadResource("Gradient");
		
		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
		
		frame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, 1280, 720);
		
		shader = new HvlShader(HvlShader.VERTEX_DEFAULT, HvlShader.FRAGMENT_QUADRUPLE_DISPLACEMENT_BLUR);
		HvlPainter2D.TEXMAGBLUR.disable();
		
		texture = new HvlAnimatedTextureArray(new Texture[]{
				getTextureLoader().getResource(0),
				getTextureLoader().getResource(1),
				getTextureLoader().getResource(2)}, 0.5f);
	}

	@Override
	public void update(float delta){
		HvlRenderFrame.setCurrentRenderFrame(frame);
		
		hvlDrawQuad(0, 0, 1280, 720, getTextureLoader().getResource(3));
		
		hvlRotate((getWidth()/2), (getHeight()/2), getNewestInstance().getTimer().getTotalTime()/2*360f);
		hvlDrawQuad((getWidth()/2) - 200, (getHeight()/2) - 200, 400, 400, texture.getCurrentTexture());
		hvlResetRotation();
		
		HvlRenderFrame.setCurrentRenderFrame(null);
		
		HvlShader.setCurrentShader(shader);
		shader.sendFloat("blurAmount", 0.002f);
		hvlDrawQuad(0, 0, 1280, 720, frame);
		HvlShader.setCurrentShader(null);
	}

}
