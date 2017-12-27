package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class ProfilerTest extends HvlTemplateInteg2D{

	public ArrayList<Float> frameTimes;

	public static final float SWITCH_RATE = 1, QUAD_SIZE = 1, QUAD_SPACE = 1, QUAD_COUNT = 200, REPEAT = 8;

	public ProfilerTest() {
		super(60, 512, 512, "Ridhvl2 Drawing Profiler Test", new HvlDisplayModeDefault());
	}

	private float timer = 0;
	private boolean printed = false;

	@Override
	public void initialize() {
		frameTimes = new ArrayList<>((int)(60 * SWITCH_RATE));
	}

	@Override
	public void update(float delta) {
		timer += delta;
		for(int i = 0; i < REPEAT; i++){
			for(float x = 0; x < QUAD_COUNT; x++){
				for(float y = 0; y < QUAD_COUNT; y++){
					//if(timer > SWITCH_RATE){
						drawObject(new HvlQuad(x * (QUAD_SIZE + QUAD_SPACE) + QUAD_SPACE, y * (QUAD_SIZE + QUAD_SPACE) + QUAD_SPACE, QUAD_SIZE, QUAD_SIZE), new HvlPaint(Color.white));
					//}else{
						//hvlDrawQuad(x * (QUAD_SIZE + QUAD_SPACE) + QUAD_SPACE, y * (QUAD_SIZE + QUAD_SPACE) + QUAD_SPACE, QUAD_SIZE, QUAD_SIZE, Color.white);
					//}
				}
			}
		}
		frameTimes.add(getTimer().getUpdateRate());
		hvlDrawQuad(Display.getWidth() - 30, 10, 20, 20, timer > SWITCH_RATE ? Color.green : Color.red);
		if(timer > SWITCH_RATE && !printed){
			printed = true;
			logFramerate(false);
		}
		if(timer > SWITCH_RATE * 2){
			timer = 0;
			printed = false;
			logFramerate(true);
		}
	}

	private void logFramerate(boolean inst){
		inst = true;
		float avg = 0;
		for(float f : frameTimes) avg += f;
		avg /= frameTimes.size();
		frameTimes.clear();
		System.out.println("Avg framerate " + (inst ? "(with inst)" : "(without inst)") + ": " + avg);
	}

	private void drawObject(HvlQuad quad, HvlPaint paint){
		hvlDrawQuad(quad.x, quad.y, quad.xl, quad.yl, paint.color);
	}

	class HvlQuad{

		public float x, y, xl, yl;

		public HvlQuad(float xArg, float yArg, float xlArg, float ylArg){
			x = xArg;
			y = yArg;
			xl = xlArg;
			yl = ylArg;
		}

	}

	class HvlPaint{

		public Color color;

		public HvlPaint(Color colorArg){
			color = colorArg;
		}

	}

}
