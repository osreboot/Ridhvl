package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.getWhite512;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlRadialPositionProvider;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticleSystem;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;
import com.osreboot.ridhvl.template.HvlTemplate;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class DilationTest extends HvlTemplate2D {
	
	public DilationTest() {
		super(60, 1280, 720, "Ridhvl Time Dilation Test", new HvlDisplayModeDefault());
	}

	LinkedList<HvlParticleSystem> particles;

	@Override
	public void initialize() {
		particles = new LinkedList<>();

		for (int i = -8; i < 32; i++) {
			HvlSimpleParticleSystem ash = new HvlSimpleParticleSystem(i * 40, -64, 6, 6, new HvlRadialPositionProvider(32f), getWhite512());
			ash.setMinScale(0.5f);
			ash.setMaxScale(1.5f);
			ash.setScaleDecay(0f);
			ash.setMinRotVel(-360);
			ash.setMaxRotVel(360);
			ash.setLifetime(32f);
			ash.setMinYVel(32);
			ash.setMaxYVel(64);
			ash.setMinXVel(0);
			ash.setMaxXVel(16);
			ash.setMinTimeToSpawn(4f);
			ash.setMaxTimeToSpawn(2f);
			ash.setParticlesPerSpawn(1);
			ash.setColorCoordinated(true);
			ash.setStartColorOne(Color.darkGray);
			ash.setStartColorTwo(Color.gray);
			ash.setEndColorOne(Color.darkGray);
			ash.setEndColorTwo(Color.gray);
			ash.addCorrelator(new HvlParticleCorrelator() {

				{
					setContinuous(true);
				}

				@Override
				public void correlate(HvlParticle in, float delta) {
					HvlSimpleParticle p = (HvlSimpleParticle) in;
					p.setRotVel(300 + (1.5f - p.getScale() * 200));
					p.setxVel(30 + (1.5f - p.getScale() * 20));
				}
			});
			particles.add(ash);
		}

		for (int i = 0; i < 32; i++) {
			HvlSimpleParticleSystem ember = new HvlSimpleParticleSystem(i * 40, Display.getHeight() - 16, 6, 6, new HvlRadialPositionProvider(32f), getWhite512());
			ember.setMinScale(0.5f);
			ember.setMaxScale(1.5f);
			ember.setScaleDecay(-1f);
			ember.setMinRotVel(-360);
			ember.setMaxRotVel(360);
			ember.setLifetime(2f);
			ember.setMinYVel(-64);
			ember.setMaxYVel(-128);
			ember.setMinXVel(-32);
			ember.setMaxXVel(32);
			ember.setyVelDecay(0.1f);
			ember.setMinTimeToSpawn(16f);
			ember.setMaxTimeToSpawn(2f);
			ember.setParticlesPerSpawn(1);
			ember.setColorCoordinated(true);
			ember.setStartColorOne(new Color(200, 200, 0));
			ember.setStartColorTwo(new Color(100, 100, 0));
			ember.setEndColorOne(new Color(200, 200, 0));
			ember.setEndColorTwo(new Color(100, 100, 0));
			particles.add(ember);
		}

		HvlSimpleParticleSystem lava = new HvlSimpleParticleSystem(-64, Display.getHeight() - 8, 32, 32, new HvlRadialPositionProvider(32f), getWhite512());
		lava.setMinScale(0.5f);
		lava.setMaxScale(1.5f);
		lava.setScaleDecay(0f);
		lava.setMinRot(-10);
		lava.setMaxRot(10);
		lava.setMinRotVel(-10);
		lava.setMaxRotVel(10);
		lava.setLifetime(32f);
		lava.setXVel(48);
		lava.setMinTimeToSpawn(0.25f);
		lava.setMaxTimeToSpawn(0.5f);
		lava.setParticlesPerSpawn(12);
		lava.setColorCoordinated(true);
		lava.setStartColorOne(new Color(150, 100, 0));
		lava.setStartColorTwo(new Color(205, 155, 0));
		lava.setEndColorOne(new Color(150, 100, 0));
		lava.setEndColorTwo(new Color(205, 155, 0));
		lava.addCorrelator(new HvlParticleCorrelator() {

			{
				setContinuous(true);
			}

			@Override
			public void correlate(HvlParticle in, float delta) {
				HvlSimpleParticle p = (HvlSimpleParticle) in;
				p.setyVel((float) Math.sin(p.getX() / 60) * 4 * (4f - p.getScale()));
			}
		});
		particles.add(lava);
	}

	@Override
	public void update(float delta) {
		HvlTemplate.getNewestInstance().getTimer().setDilation(((float) HvlCursor.getCursorX() / (float) Display.getHeight()) + 0.5f);

		for (HvlParticleSystem particle : particles)
			particle.draw(delta);
	}

}
