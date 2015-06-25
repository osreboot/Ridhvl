package com.osreboot.ridhvl.test.particle;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlRadialParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlRectangularParticleSystem;
import com.osreboot.ridhvl.particle.correlation.collection.HvlParticleVelocityToAngleCorrelator;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ParticlesTest extends HvlTemplate2DBasic {

	public ParticlesTest() {
		super(600, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	// HvlRadialParticleSystem particles;
	List<HvlParticleSystem> particles;

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	
	@Override
	public void initialize() {
		particles = new LinkedList<>();

		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1),
				HvlFontUtil.DEFAULT, 2048, 2048, 40, 50, 51);

		final int count = 25;

		for (int i = 0; i < count; i++) {
			HvlRectangularParticleSystem toAdd = new HvlRectangularParticleSystem(
					1280 / 2, 720 / 2, 16, 64, 0, 0, Display.getWidth(),
					Display.getHeight(), textureLoader.getResource(0), textureLoader.getResource(1));
			toAdd.setMaxParticles(250);
			toAdd.setMinScale(0.25f);
			toAdd.setMaxScale(1);
			toAdd.setxVelDecay(-0.1f);
			toAdd.setyVelDecay(-0.1f);
			toAdd.setScaleDecay(-0.25f);
			toAdd.setMinRot(0f);
			toAdd.setMaxRot(360f);
			toAdd.setMinRotVel(-360);
			toAdd.setMaxRotVel(360);
			toAdd.setMinLifetime(2f);
			toAdd.setMaxLifetime(6f);
			toAdd.setMinYVel(-128);
			toAdd.setMaxYVel(-64);
			toAdd.setMinXVel(-32);
			toAdd.setMaxXVel(32);
			toAdd.setMinTimeToSpawn(1.5f);
			toAdd.setMaxTimeToSpawn(0.5f);
			toAdd.setParticlesPerSpawn(10);
			toAdd.setStartColorOne(Color.black);
			toAdd.setStartColorTwo(Color.white);
			toAdd.setEndColorOne(Color.blue);
			toAdd.setEndColorTwo(Color.blue);
			toAdd.setX(i * (Display.getWidth() / count));
			toAdd.setY(Display.getHeight());
			toAdd.setColorCoordinated(false);
			toAdd.addCorrelator(new HvlParticleVelocityToAngleCorrelator());
			particles.add(toAdd);
		}

		for (int i = 0; i < 0; i++) {
			HvlRadialParticleSystem toAdd = new HvlRadialParticleSystem(
					1280 / 2, 720 / 2, 16, 16, textureLoader.getResource(0));
			toAdd.setSpawnRadius(24.0f);
			toAdd.setMinScale(.25f);
			toAdd.setMaxScale(0.725f);
			toAdd.setxVelDecay(0.99f);
			toAdd.setyVelDecay(1.0f);
			toAdd.setScaleDecay(0.99f);
			toAdd.setMinRotVel(-360);
			toAdd.setMaxRotVel(360);
			toAdd.setMinLifetime(2f);
			toAdd.setMaxLifetime(6f);
			toAdd.setMinYVel(-16);
			toAdd.setMaxYVel(-8);
			toAdd.setMinXVel(0);
			toAdd.setMaxXVel(32);
			toAdd.setMinTimeToSpawn(1.5f);
			toAdd.setMaxTimeToSpawn(0.5f);
			toAdd.setParticlesPerSpawn(5);
			toAdd.setStartColorOne(Color.red);
			toAdd.setStartColorTwo(Color.orange);
			toAdd.setEndColorOne(Color.orange);
			toAdd.setEndColorTwo(Color.yellow);
			toAdd.setX(i * (Display.getWidth() / count));
			toAdd.setY(Display.getHeight());
			particles.add(toAdd);
		}

		for (int i = 0; i < 0; i++) {
			HvlRadialParticleSystem toAdd = new HvlRadialParticleSystem(
					1280 / 2, 720 / 2, 16, 16, textureLoader.getResource(0));
			toAdd.setSpawnRadius(24.0f);
			toAdd.setMinScale(.15f);
			toAdd.setMaxScale(0.725f);
			toAdd.setxVelDecay(0.99f);
			toAdd.setyVelDecay(1.0f);
			toAdd.setScaleDecay(0.99f);
			toAdd.setMinRotVel(-360);
			toAdd.setMaxRotVel(360);
			toAdd.setMinLifetime(2f);
			toAdd.setMaxLifetime(6f);
			toAdd.setMinYVel(-128);
			toAdd.setMaxYVel(-8);
			toAdd.setMinXVel(0);
			toAdd.setMaxXVel(32);
			toAdd.setMinTimeToSpawn(1.5f);
			toAdd.setMaxTimeToSpawn(0.5f);
			toAdd.setParticlesPerSpawn(5);
			toAdd.setStartColorOne(Color.darkGray);
			toAdd.setStartColorTwo(Color.gray);
			toAdd.setEndColorOne(Color.gray);
			toAdd.setEndColorTwo(Color.lightGray);
			toAdd.setX(i * (Display.getWidth() / count));
			toAdd.setY(Display.getHeight());
			particles.add(toAdd);
		}
	}

	@Override
	public void update(float delta) {
		// particles.setX(HvlCursor.getCursorX());
		// particles.setY(HvlCursor.getCursorY());

		for (HvlParticleSystem ps : particles) {
			ps.draw(delta);
		}
	}
}
