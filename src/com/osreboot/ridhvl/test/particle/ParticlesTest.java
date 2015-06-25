package com.osreboot.ridhvl.test.particle;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlRadialParticleSystem;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ParticlesTest extends HvlTemplate2DBasic {

	public ParticlesTest() {
		super(600, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	public static void main(String[] args) {
		new ParticlesTest();
	}

	// HvlRadialParticleSystem particles;
	List<HvlRadialParticleSystem> particles;

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
			HvlRadialParticleSystem toAdd = new HvlRadialParticleSystem(1280 / 2, 720 / 2,
					textureLoader.getResource(0), 256, 4);
			toAdd.setMaxParticles(250);
			toAdd.setSpawnRadius(24.0f);
			toAdd.setMinScale(0.75f);
			toAdd.setMaxScale(1);
			toAdd.setxVelDecay(0.5f);
			toAdd.setyVelDecay(0.5f);
			toAdd.setScaleDecay(0f);
			toAdd.setMinRot(67.5f);
			toAdd.setMaxRot(67.5f);
			toAdd.setMinRotVel(0);
			toAdd.setMaxRotVel(0);
			toAdd.setMinLifetime(2f);
			toAdd.setMaxLifetime(6f);
			toAdd.setMinYVel(256);
			toAdd.setMaxYVel(1024);
			toAdd.setMinXVel(256);
			toAdd.setMaxXVel(1024);
			toAdd.setMinTimeToSpawn(1.5f);
			toAdd.setMaxTimeToSpawn(0.5f);
			toAdd.setParticlesPerSpawn(10);
			toAdd.setStartColorOne(Color.black);
			toAdd.setStartColorTwo(Color.white);
			toAdd.setEndColorOne(Color.blue);
			toAdd.setEndColorTwo(Color.blue);
			toAdd.setX(i * (Display.getWidth() / count) - 768);
			toAdd.setY(-256);
			toAdd.setColorCoordinated(false);
			particles.add(toAdd);
		}
		
		for (int i = 0; i < 0; i++) {
			HvlRadialParticleSystem toAdd = new HvlRadialParticleSystem(1280 / 2, 720 / 2,
					textureLoader.getResource(0), 16, 16);
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
			HvlRadialParticleSystem toAdd = new HvlRadialParticleSystem(1280 / 2, 720 / 2,
					textureLoader.getResource(0), 16, 16);
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

		for (HvlParticleSystem ps : particles)
		{
			ps.draw(delta);
		}
	}
}
