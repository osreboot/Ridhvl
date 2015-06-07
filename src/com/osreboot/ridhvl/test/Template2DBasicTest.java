package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.template.HvlColorButton;
import com.osreboot.ridhvl.menu.component.template.HvlTextureButton;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class Template2DBasicTest extends HvlTemplate2DBasic{
	
	private HvlMenu testMenu;
	private HvlCheckbox testCheck;
	private HvlButton testButton;
	
	public Template2DBasicTest(){
		super(60, 1280, 720, "Unnamed", HvlDisplayMode.DEFAULT);
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	long gradient = 0;
	
	@Override
	public void initialize(){
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");
		
		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontLayout.DEFAULT, 2048, 2048, 40, 50, 51);
	
		testMenu = new HvlMenu() {
			
		};
		testCheck = new HvlCheckbox(0, 0, 64, 64, 720, false)
		{
			@Override
			public void draw(float delta)
			{
				if (this.getChecked())
				{
					HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureLoader.getResource(0), Color.red);
				}
				else
				{
					HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureLoader.getResource(0), Color.blue);
				}
			}
			
			public void onChanged(boolean state)
			{
				System.out.println(state);
			}
		};
		testMenu.add(testCheck);
		
		testButton = new HvlTextureButton(64, 64, 64, 64, 720, textureLoader.getResource(0), textureLoader.getResource(1), textureLoader.getResource(0))
		{
			@Override
			public void onTriggered()
			{
				System.out.println("BUTTTTOOONN!");
			}
		};
		testMenu.add(testButton);
		
		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta){
		gradient = gradient < 1280 ? gradient + ((long)delta * 1000) : 0;
		
		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, textureLoader.getResource(0), new Color(1f, ((float)i - 180)/180, (float)i/360));
		}
		
		hvlRotate(300f, 300f, gradient/1280f*360f);
		hvlDrawQuad(250, 250, 100, 100, textureLoader.getResource(0));
		hvlResetRotation();
		
		fontPainter.hvlDrawWord("test of the most basic template!", 10, 10, new Color(gradient/1280f, gradient/1280f, gradient/1280f, 1f));
		fontPainter.hvlDrawWord("and rotation! yay?", 10, 100, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f), 1f));

		HvlMenu.updateMenus(delta);
	}

}
