package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureSeriesLoader;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class AnimationTest extends HvlTemplateInteg2D{
	
	public AnimationTest(){
		super(60, 1280, 720, "Ridhvl Animation Test", new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;
	
	HvlAnimatedTexture texture;
	HvlTextureSeriesLoader animationLoader;
	HvlAnimatedTexture animation;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("Icon");
		getTextureLoader().loadResource("Font");
		
		animationLoader = new HvlTextureSeriesLoader();
		//animationLoader.loadResource("animation\\WubSplash_%03d", 375, 2);
		
		animation = new HvlAnimatedTextureUV(getTextureLoader().getResource(1), 256, 128, 1f);
		//animation = new HvlAnimatedTextureArray(animationLoader.getResource(0), 0.02f);
		
		
		
	}

	@Override
	public void update(float delta){
		//hvlDrawQuad(0, 0, 1280, 720, 0.1875f, 0.32421875f, 0.8125f, 0.67578125f, animation.getCurrentTexture());
		hvlDrawQuad(0, 0, 512, 512, (HvlAnimatedTextureUV)animation);
	}

}