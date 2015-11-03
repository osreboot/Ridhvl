package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.getWhite512;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2D;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ExpandingRectangleTest extends HvlTemplate2D {
	
	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Ridhvl Expanding Rectangle Test", new HvlDisplayModeDefault());
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	HvlTiledRect testRect;
	
	Color color;
	
	@Override
	public void initialize() {
		textureLoader.loadResource("Icon");
		color = Color.white;
		testRect = new HvlTiledRect(textureLoader.getResource(0), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 512, 256, 16, 16);
//		HvlInputSeriesAction.PRIMARY.setPressedAction(new HvlAction1<HvlInput>(){
//			@Override
//			public void run(HvlInput inputArg){
//				color = Color.orange;
//			}
//		});
//		HvlInputSeriesAction.PRIMARY.setReleasedAction(new HvlAction1<HvlInput>(){
//			@Override
//			public void run(HvlInput inputArg){
//				color = Color.yellow;
//			}
//		});
//		try{
//			Controllers.create();
//		}catch(LWJGLException e){
//			e.printStackTrace();
//		}
	}

	@Override
	public void update(float delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, getWhite512(), color);
	
//		if(Controllers.isCreated()){
//			Controllers.poll();
//			System.out.println("total: " + Controllers.getControllerCount());
//			for(int i = 0; i < Controllers.getControllerCount(); i++){
//				if(Controllers.getController(i).getName().contains("Controller")){
//					Controller c = Controllers.getController(i);
//					ControllerEnvironment.getDefaultEnvironment().getControllers();
//					System.out.println(i + ":" + c.getButtonCount() + ":" + c.getName());
//					testRect.setTotalWidth(testRect.getTotalWidth() + ((Math.abs(c.getXAxisValue()) < c.getXAxisDeadZone() * 4 ? 0 : c.getXAxisValue()) * 500 * delta));
//					testRect.setTotalHeight(testRect.getTotalHeight() + ((Math.abs(c.getYAxisValue()) < c.getYAxisDeadZone() * 4 ? 0 : c.getYAxisValue()) * 500 * delta));
//					System.out.println(c.getYAxisValue() + ":::" + c.getYAxisDeadZone());
//				}
//				
//			}
//		}
		
		//  http://stackoverflow.com/questions/17413690/java-jinput-rescan-reload-controllers
		
		
		ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		System.out.println(":::");
		for(Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()){
			System.out.println(c.getName());
			System.out.println(c.getType().toString());
		}
		
		//testRect.setTotalWidth(testRect.getTotalWidth() + HvlInputSeriesAction.RIGHT.getCurrentOutput() * 256 * delta);
		//testRect.setTotalWidth(testRect.getTotalWidth() - HvlInputSeriesAction.LEFT.getCurrentOutput() * 256 * delta);
		//testRect.setTotalHeight(testRect.getTotalHeight() - HvlInputSeriesAction.DOWN.getCurrentOutput() * 256 * delta);
		//testRect.setTotalHeight(testRect.getTotalHeight() + HvlInputSeriesAction.UP.getCurrentOutput() * 256 * delta);
		
		testRect.setX(640 - (testRect.getTotalWidth()/2));
		testRect.setY(360 - (testRect.getTotalHeight()/2));
		
		testRect.draw();
	}

}