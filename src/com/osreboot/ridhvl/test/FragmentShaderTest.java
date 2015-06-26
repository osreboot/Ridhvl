package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.shader.HvlShader;
import com.osreboot.ridhvl.painter.shader.HvlShaderFragment;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;

public class FragmentShaderTest extends HvlTemplateInteg2DBasic{
	
	public FragmentShaderTest(){
		super(60, 1280, 720, "Unnamed", 5, 5, new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;
	float gradient = 0;
	
	HvlShaderFragment shader;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("White");
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");
		
		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
		
		shader = new HvlShaderFragment("shader\\NegativeBasic.hvlfg");
	}

	@Override
	public void update(float delta){
		gradient = gradient < 1280 ? gradient + (delta*500) : 0;
		
		hvlDrawQuad(0, 0, 1280, 720, getTextureLoader().getResource(1));
		
		HvlShader.setCurrentShader(shader.getID());
		hvlRotate((getWidth()/2), (getHeight()/2), gradient/1280f*360f);
		hvlDrawQuad((getWidth()/2) - 200, (getHeight()/2) - 200, 400, 400, getTextureLoader().getResource(1));
		hvlResetRotation();
		HvlShader.setCurrentShader(0);
	}

}
