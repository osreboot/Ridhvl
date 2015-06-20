package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.collection.HvlColorCheckbox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureButton;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureTextBox;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ComponentsTest extends HvlTemplate2DBasic{
	
	private HvlMenu testMenu;
	private HvlArrangerBox testArranger;
	private HvlLabel testLabel;
	private HvlCheckbox testCheck;
	private HvlButton testButton;
	private HvlTextureTextBox testTextBox;
	
	public ComponentsTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	
	public static void main(String[] args)
	{
		new ComponentsTest().start();
	}
	
	@Override
	public void initialize(){
		Keyboard.enableRepeatEvents(true);		
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");
		textureLoader.loadResource("ButtonUp");
		textureLoader.loadResource("ButtonHover");
		textureLoader.loadResource("ButtonDown");
		
		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
	
		testMenu = new HvlMenu() {
			
		};
		testLabel = new HvlLabel(0, 0, 720, fontPainter, "testing!", Color.red);
		testCheck = new HvlColorCheckbox(0, 0, 64, 64, 720, Color.blue, Color.cyan, Color.red, Color.pink, textureLoader.getResource(0))
		{			
			public void onChanged(boolean state)
			{
				System.out.println(state);
			}
		};	
		testButton = new HvlTextureButton(64, 64, 128, 128, 720, textureLoader.getResource(2), textureLoader.getResource(3), textureLoader.getResource(4))
		{
			@Override
			public void onTriggered()
			{
				System.out.println("BUTTTTOOONN!");
			}
		};
		testTextBox = new HvlTextureTextBox(0, 0, 512, 96, 720, "hey", textureLoader.getResource(2), textureLoader.getResource(4), fontPainter);
		testTextBox.setTextScale(0.5f);
		testTextBox.setMaxCharacters(5);
		testTextBox.setForceLowercase(true);
		testTextBox.setOffsetX(12f);
		testTextBox.setOffsetY(12f);
		
		testArranger = new HvlArrangerBox(0, 0, 1280, 720, 720, ArrangementStyle.VERTICAL);
		testArranger.add(testLabel);
		testArranger.add(testCheck);
		testArranger.add(testButton);
		testArranger.add(testTextBox);
		testArranger.setAlign(0.0f);
		testMenu.add(testArranger);
		
		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta){
		HvlMenu.updateMenus(delta);
	}
	
}
