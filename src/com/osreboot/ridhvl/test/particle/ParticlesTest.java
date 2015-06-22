package com.osreboot.ridhvl.test.particle;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.particle.collection.HvlRadialParticleSystem;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ParticlesTest extends HvlTemplate2DBasic{
	
	public ParticlesTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}
	
	HvlRadialParticleSystem particles;
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	
	@Override
	public void initialize(){		
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");
		
		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 40, 50, 51);
		particles = new HvlRadialParticleSystem(1280 / 2, 720 / 2, textureLoader.getResource(0), 16, 16);
		particles.setSpawnRadius(16.0f);
		particles.setMinScale(.25f);
		particles.setMaxScale(1.0f);
		particles.setMinRotVel(-360);
		particles.setMaxRotVel(360);
		particles.setMinLifetime(5f);
		particles.setMaxLifetime(10f);
		particles.setMinYVel(-32.0f);
		particles.setMaxYVel(-8.0f);
		particles.setMinTimeToSpawn(0.1f);
		particles.setMaxTimeToSpawn(0.1f);
		particles.setParticlesPerSpawn(5);
		particles.setStartColorOne(Color.lightGray);
		particles.setStartColorTwo(Color.darkGray);
		particles.setEndColorOne(Color.lightGray);
		particles.setEndColorTwo(Color.darkGray);
	}

	@Override
	public void update(float delta){
		particles.draw(delta);
	}
}
