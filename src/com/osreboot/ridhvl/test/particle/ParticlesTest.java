package com.osreboot.ridhvl.test.particle;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ParticlesTest extends HvlTemplate2DBasic{
	
	public ParticlesTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}
	
	TestSmokeParticleSystem particles;
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	long gradient = 0;
	
	@Override
	public void initialize(){		
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");
		
		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 40, 50, 51);
		particles = new TestSmokeParticleSystem(1280 / 2, 720 / 2, textureLoader.getResource(0));
	}

	@Override
	public void update(float delta){
		particles.draw(delta);
	}
}
