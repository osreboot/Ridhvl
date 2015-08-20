package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlParticlePositionProvider;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticleSystem;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ParticlesTest extends HvlTemplate2D {

	BufferedImage image;
	
	boolean triggered = false, pTriggered = false;
	
//	float scale = 0.25f;

	float tileWidth, tileHeight;

	public static void main(String[] args) {
		new ParticlesTest();
	}

	public ParticlesTest() {
		super(60, 1280, 720, "Ridhvl Particle Test", new HvlDisplayModeDefault());
	}

	// HvlRadialParticleSystem particles;
	List<HvlParticleSystem> particles;

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize() {
		try {
			image = ImageIO.read(new File("res/IconSmall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		particles = new LinkedList<>();

		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(0), HvlFontUtil.DEFAULT, 2048, 2048, 40, 50, 51);

		// final int count = 25;

		tileWidth = 720 / image.getWidth();
		tileHeight = 720 / image.getHeight();

		HvlSimpleParticleSystem test = new HvlSimpleParticleSystem((Display.getWidth() / 2) - (720 / 2), (Display.getHeight() / 2) - (720 / 2), tileWidth, tileHeight, new HvlParticlePositionProvider() {

			int last = 0;
			
			@Override
			public HvlCoord getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
				int tile = last++;
				return new HvlCoord(spawnerArg.getX() + (tileWidth / 2) + (tile % image.getHeight()) * tileWidth, spawnerArg.getY() + (tileHeight / 2) + (int)((tile / image.getHeight())) * tileHeight);
			}
		}, getWhite512());
		test.setMaxParticles(image.getWidth() * image.getHeight());
		test.setScale(1.0f);
		test.setXVel(0.0f);
		test.setYVel(0.0f);
		test.setRot(0);
		test.setMinLifetime(15f);
		test.setMaxLifetime(25f);
		test.setTimeToSpawn(0.01f);
		test.setParticlesPerSpawn(100);
		test.setSpawnOnTimer(false);
		test.addCorrelator(new HvlParticleCorrelator() {
			{
				setContinuous(false);
			}

			@Override
			public void correlate(HvlParticle in, float delta) {
				HvlSimpleParticle p = (HvlSimpleParticle) in;

				int x = (int) ((in.getX() - (tileWidth / 2) - in.getParent().getX()) / tileWidth);
				int y = (int) ((in.getY() - (tileHeight / 2) - in.getParent().getY()) / tileHeight);

				int pixel = image.getRGB(x, y);

				float alpha = (pixel >> 24) & 0xff;
				float red = (pixel >> 16) & 0xff;
				float green = (pixel >> 8) & 0xff;
				float blue = (pixel) & 0xff;

				p.setColor(new Color(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f));
			}
		});
		test.addCorrelator(new HvlParticleCorrelator() {			
			{
				setContinuous(true);
			}
			
			@Override
			public void correlate(HvlParticle in, float delta) {
				HvlSimpleParticle p = (HvlSimpleParticle) in;
				
				if (!pTriggered && triggered)
				{					
					float angle = (float) Math.atan2(p.getY() - ((Display.getHeight() / 2)), p.getX() - ((Display.getWidth() / 2)));
					
					float length = new HvlCoord(p.getX() - ((Display.getWidth() / 2)), p.getY() - ((Display.getHeight() / 2))).length();
					
					p.setxVel((float) Math.cos(angle) * 1.0f / Math.min(length / 720, 1.0f));
					p.setyVel((float) Math.sin(angle) * 1.0f / Math.min(length / 720, 1.0f));
				}
			}
		});
		test.spawnAllParticles();
		particles.add(test);

		// for (int i = 0; i < count; i++) {
		// HvlSimpleParticleSystem toAdd = new HvlSimpleParticleSystem(0, 0, 16,
		// 64, new HvlRectanglePositionProvider(0, Display.getWidth(), 0,
		// Display.getHeight()), getWhite512(), textureLoader.getResource(0));
		// toAdd.setMaxParticles(250);
		// toAdd.setMinScale(0.25f);
		// toAdd.setMaxScale(1);
		// toAdd.setxVelDecay(-0.1f);
		// toAdd.setyVelDecay(-0.1f);
		// toAdd.setScaleDecay(-0.25f);
		// toAdd.setMinRot(0f);
		// toAdd.setMaxRot(360f);
		// toAdd.setMinRotVel(-360);
		// toAdd.setMaxRotVel(360);
		// toAdd.setMinLifetime(2f);
		// toAdd.setMaxLifetime(6f);
		// toAdd.setMinYVel(-128);
		// toAdd.setMaxYVel(-64);
		// toAdd.setMinXVel(-32);
		// toAdd.setMaxXVel(32);
		// toAdd.setMinTimeToSpawn(1.5f);
		// toAdd.setMaxTimeToSpawn(0.5f);
		// toAdd.setParticlesPerSpawn(10);
		// toAdd.setStartColorOne(Color.black);
		// toAdd.setStartColorTwo(Color.white);
		// toAdd.setEndColorOne(Color.blue);
		// toAdd.setEndColorTwo(Color.blue);
		// toAdd.setColorCoordinated(false);
		// toAdd.addCorrelator(new HvlParticleVelocityToAngleCorrelator());
		// particles.add(toAdd);
		// }
		//
		// for (int i = 0; i < 0; i++) {
		// HvlSimpleParticleSystem toAdd = new HvlSimpleParticleSystem(1280 / 2,
		// 720 / 2, 16, 16, new HvlRadialPositionProvider(24.0f),
		// textureLoader.getResource(0));
		// toAdd.setMinScale(.25f);
		// toAdd.setMaxScale(0.725f);
		// toAdd.setxVelDecay(0.99f);
		// toAdd.setyVelDecay(1.0f);
		// toAdd.setScaleDecay(0.99f);
		// toAdd.setMinRotVel(-360);
		// toAdd.setMaxRotVel(360);
		// toAdd.setMinLifetime(2f);
		// toAdd.setMaxLifetime(6f);
		// toAdd.setMinYVel(-16);
		// toAdd.setMaxYVel(-8);
		// toAdd.setMinXVel(0);
		// toAdd.setMaxXVel(32);
		// toAdd.setMinTimeToSpawn(1.5f);
		// toAdd.setMaxTimeToSpawn(0.5f);
		// toAdd.setParticlesPerSpawn(5);
		// toAdd.setStartColorOne(Color.red);
		// toAdd.setStartColorTwo(Color.orange);
		// toAdd.setEndColorOne(Color.orange);
		// toAdd.setEndColorTwo(Color.yellow);
		// toAdd.setX(i * (Display.getWidth() / count));
		// toAdd.setY(Display.getHeight());
		// particles.add(toAdd);
		// }
		//
		// for (int i = 0; i < 0; i++) {
		// HvlSimpleParticleSystem toAdd = new HvlSimpleParticleSystem(1280 / 2,
		// 720 / 2, 16, 16, new HvlRadialPositionProvider(24.0f),
		// textureLoader.getResource(0));
		// toAdd.setMinScale(.15f);
		// toAdd.setMaxScale(0.725f);
		// toAdd.setxVelDecay(0.99f);
		// toAdd.setyVelDecay(1.0f);
		// toAdd.setScaleDecay(0.99f);
		// toAdd.setMinRotVel(-360);
		// toAdd.setMaxRotVel(360);
		// toAdd.setMinLifetime(2f);
		// toAdd.setMaxLifetime(6f);
		// toAdd.setMinYVel(-128);
		// toAdd.setMaxYVel(-8);
		// toAdd.setMinXVel(0);
		// toAdd.setMaxXVel(32);
		// toAdd.setMinTimeToSpawn(1.5f);
		// toAdd.setMaxTimeToSpawn(0.5f);
		// toAdd.setParticlesPerSpawn(5);
		// toAdd.setStartColorOne(Color.darkGray);
		// toAdd.setStartColorTwo(Color.gray);
		// toAdd.setEndColorOne(Color.gray);
		// toAdd.setEndColorTwo(Color.lightGray);
		// toAdd.setX(i * (Display.getWidth() / count));
		// toAdd.setY(Display.getHeight());
		// particles.add(toAdd);
		// }
	}

	@Override
	public void update(float delta) {
		pTriggered = triggered;
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
		{
			triggered = true;
		}
		
		// particles.setX(HvlCursor.getCursorX());
		// particles.setY(HvlCursor.getCursorY());

		for (HvlParticleSystem ps : particles) {
			ps.draw(delta);
		}
	}
}
