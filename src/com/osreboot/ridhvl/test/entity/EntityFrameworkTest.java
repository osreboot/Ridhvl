package com.osreboot.ridhvl.test.entity;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.entity.HvlState;
import com.osreboot.ridhvl.entity.HvlStateManager;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class EntityFrameworkTest extends HvlTemplate2D{
	
	HvlStateManager stateManager;
	
	public EntityFrameworkTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize(){
		stateManager = new HvlStateManager();
		
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
		
		stateManager.addStateUniqueName("TestState", new HvlState()
		{
			@Override
			public void initialize()
			{
				System.out.println("Initializing state!");
			}
			
			@Override
			public void update(float delta)
			{
				System.out.println("Updating state!");
			}
			
			@Override
			public void draw(float delta)
			{
				System.out.println("Drawing state!");
			}
		});
	}

	@Override
	public void update(float delta){
		stateManager.update(delta);
		stateManager.draw(delta);
	}
}
