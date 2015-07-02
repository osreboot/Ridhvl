package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeResizable;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.HvlListBox;
import com.osreboot.ridhvl.menu.component.HvlSlider;
import com.osreboot.ridhvl.menu.component.HvlSlider.SliderDirection;
import com.osreboot.ridhvl.menu.component.HvlTextBox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextButton;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureDrawable;
import com.osreboot.ridhvl.menu.component.collection.HvlTiledRectDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ComponentsTest extends HvlTemplate2D {

	private HvlMenu testMenu;
	private HvlArrangerBox testArranger;
	private HvlLabel testLabel;
	private HvlCheckbox testCheck;
	private HvlTextButton testButton;
	private HvlTextBox testTextBox;
	private HvlSlider testSlider;
	private HvlListBox testListBox;

	public static void main(String[] args)
	{
		new ComponentsTest();
	}
	
	public ComponentsTest() {
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeResizable());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(10);
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize() {
		Keyboard.enableRepeatEvents(true);
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");
		textureLoader.loadResource("ButtonUp");
		textureLoader.loadResource("ButtonHover");
		textureLoader.loadResource("ButtonDown");
		textureLoader.loadResource("SliderBG");
		textureLoader.loadResource("SliderHandleOff");
		textureLoader.loadResource("SliderHandleOn");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1),
				HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);

		testMenu = new HvlMenu() {

		};
		testLabel = new HvlLabel(0, 0, fontPainter, "testing!", Color.red);
		testCheck = new HvlCheckbox(0, 0, 64, 64, new HvlTextureDrawable(
				HvlTextureUtil.getColoredRect(64, 64, Color.blue)),
				new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64,
						Color.cyan)), new HvlTextureDrawable(
						HvlTextureUtil.getColoredRect(64, 64, Color.red)),
				new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64,
						Color.pink))) {
			public void onChanged(boolean state) {
				System.out.println(state);
			}
		};
		testButton = new HvlTextButton(64, 64, 256, 128, new HvlTextureDrawable(
				textureLoader.getResource(2)), new HvlTextureDrawable(
				textureLoader.getResource(3)), new HvlTextureDrawable(
				textureLoader.getResource(4)), fontPainter, "hey!") {
			@Override
			public void onTriggered() {
				System.out.println("BUTTTTOOONN!");
			}
		};
		testButton.setTextScale(0.5f);
		testButton.setTextColor(Color.red);
		testButton.setxAlign(0.5f);
		testButton.setyAlign(0.5f);
		
		testTextBox = new HvlTextBox(0, 0, 512, 96, "hey",
				new HvlTiledRectDrawable(new HvlTiledRect(textureLoader
						.getResource(2), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0,
						0, 0, 16, 16)), new HvlTiledRectDrawable(
						new HvlTiledRect(textureLoader.getResource(4), 0.125f,
								0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16)),
				fontPainter);
		testTextBox.setTextScale(0.4f);
		testTextBox.setMaxCharacters(10);
		testTextBox.setForceLowercase(true);
		testTextBox.setOffsetX(12f);
		testTextBox.setOffsetY(12f);
		testSlider = new HvlSlider(0, 0, 32, 512,
				SliderDirection.VERTICAL, 16, 16, 0.0f,
				new HvlTextureDrawable(textureLoader.getResource(6)), new HvlTextureDrawable(textureLoader.getResource(7)),
				new HvlTextureDrawable(textureLoader.getResource(5)));
		testSlider.setHandleStartOffset(8);
		testSlider.setHandleEndOffset(8);
		testSlider.setSnapInterval(0.1f);

//		testListBox = new HvlListBox(0, 0, 512, 256, testSlider, new HvlButton(
//				64, 64, 32, 32, new HvlTextureDrawable(
//						textureLoader.getResource(2)), new HvlTextureDrawable(
//						textureLoader.getResource(3)), new HvlTextureDrawable(
//						textureLoader.getResource(4))), new HvlButton(64, 64,
//				32, 32, new HvlTextureDrawable(textureLoader.getResource(2)),
//				new HvlTextureDrawable(textureLoader.getResource(3)),
//				new HvlTextureDrawable(textureLoader.getResource(4))),
//				new HvlFontPainter2D(textureLoader.getResource(1),
//						HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18),
//				new HvlTextureDrawable(textureLoader.getResource(5)),
//				new HvlTextureDrawable(textureLoader.getResource(6)),
//				new HvlTextureDrawable(textureLoader.getResource(7)), 32, 5);
		testListBox = new HvlListBox(0, 0, 512, 256, testSlider, null, null,
				new HvlFontPainter2D(textureLoader.getResource(1),
						HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18),
				new HvlTextureDrawable(textureLoader.getResource(5)),
				new HvlTextureDrawable(textureLoader.getResource(6)),
				new HvlTextureDrawable(textureLoader.getResource(7)), 32, 5);
		testListBox.setTextScale(0.25f);
		testListBox.setAutoSize(true);
		testListBox.setBackground(new HvlTextureDrawable(textureLoader.getResource(2)));
		testListBox.setFullBackground(true);
		testListBox.addItem("hey");
		testListBox.addItem("nice to meet you");
		testListBox.addItem("nice to be you");
		testListBox.addItem("derp!");
		testListBox.addItem("derp2!");
		testListBox.addItem("derp3!");
		testListBox.addItem("derp4!");
		testListBox.addItem("derp5!");
		testListBox.addItem("derp6!");
		testListBox.addItem("derp7!");

		testArranger = new HvlArrangerBox(0, 0, 1280, 720,
				ArrangementStyle.VERTICAL);
		testArranger.add(testLabel);
		testArranger.add(testCheck);
		testArranger.add(testButton);
		testArranger.add(testTextBox);
		// testArranger.addChild(testSlider);
		testArranger.add(testListBox);
		testArranger.setAlign(0.5f);
		testMenu.add(testArranger);

		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta) {
		testArranger.setWidth(Display.getWidth());
		testArranger.setHeight(Display.getHeight());
		HvlMenu.updateMenus(delta);
	}

}
