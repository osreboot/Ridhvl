package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;
import com.osreboot.ridhvl.painter.HvlShader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class ShaderTest extends HvlTemplateInteg2D{
	
	public ShaderTest(){
		super(60, 1280, 720, "Ridhvl Shader Test", new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;
	
	HvlShader shader;
	HvlRenderFrame frame;
	HvlAnimatedTexture texture;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");
		getTextureLoader().loadResource("Icon");
		
		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(0), HvlFontUtil.DEFAULT, 112, 144);
		
		try{
			frame = new HvlRenderFrame(1280, 720);
		}catch(FBOUnsupportedException e){
			e.printStackTrace();
		}
		
		shader = new HvlShader(HvlShader.VERTEX_DEFAULT, HvlShader.FRAGMENT_NEWSPAPER);
		
		texture = new HvlAnimatedTextureArray(new Texture[]{
				getTextureLoader().getResource(0),
				getTextureLoader().getResource(1),
				getTextureLoader().getResource(2)}, 0.5f);
	}

	@Override
	public void update(float delta){
		frame.doCapture(new HvlAction0(){
			@Override
			public void run(){
				hvlDrawQuad(0, 0, 1280, 720, getTextureLoader().getResource(2));
				
				hvlRotate((getWidth()/2), (getHeight()/2), getNewestInstance().getTimer().getTotalTime()/2*360f);
				hvlDrawQuadc(getWidth()/2, getHeight()/2, 400, 400, texture.getCurrentTexture());
				hvlResetRotation();
			}
		});
		
		shader.doShade(new HvlAction0(){
			@Override
			public void run(){
				//shader.sendFloat("blurAmount", 0.002f);
				hvlDrawQuad(0, 0, 1280, 720, frame);
			}
		});
	}

}
