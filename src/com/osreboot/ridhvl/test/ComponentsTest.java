package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeResizable;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.HvlSlider.SliderDirection;
import com.osreboot.ridhvl.menu.component.collection.HvlColorCheckbox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureButton;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureListBox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureSlider;
import com.osreboot.ridhvl.menu.component.collection.HvlTiledRectTextBox;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ComponentsTest extends HvlTemplate2DBasic {

	private HvlMenu testMenu;
	private HvlArrangerBox testArranger;
	private HvlLabel testLabel;
	private HvlCheckbox testCheck;
	private HvlButton testButton;
	private HvlTiledRectTextBox testTextBox;
	private HvlTextureSlider testSlider;
	private HvlTextureListBox testListBox;

	public static void main(String[] args) {
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
		testCheck = new HvlColorCheckbox(0, 0, 64, 64, Color.blue, Color.cyan,
				Color.red, Color.pink, textureLoader.getResource(0)) {
			public void onChanged(boolean state) {
				System.out.println(state);
			}
		};
		testButton = new HvlTextureButton(64, 64, 32, 32,
				textureLoader.getResource(2), textureLoader.getResource(3),
				textureLoader.getResource(4)) {
			@Override
			public void onTriggered() {
				System.out.println("BUTTTTOOONN!");
			}
		};
		testTextBox = new HvlTiledRectTextBox(0, 0, 512, 96, "hey",
				new HvlTiledRect(textureLoader.getResource(2), 0.125f, 0.875f,
						0.125f, 0.875f, 0, 0, 0, 0, 16, 16), new HvlTiledRect(
						textureLoader.getResource(4), 0.125f, 0.875f, 0.125f,
						0.875f, 0, 0, 0, 0, 16, 16), fontPainter);
		testTextBox.setTextScale(0.4f);
		testTextBox.setMaxCharacters(10);
		testTextBox.setForceLowercase(true);
		testTextBox.setOffsetX(12f);
		testTextBox.setOffsetY(12f);
		testSlider = new HvlTextureSlider(0, 0, 32, 512,
				SliderDirection.VERTICAL, 16, 16, 0.0f,
				textureLoader.getResource(6), textureLoader.getResource(7),
				textureLoader.getResource(5));
		testSlider.setHandleStartOffset(8);
		testSlider.setHandleEndOffset(8);
		testSlider.setSnapInterval(0.1f);

		testListBox = new HvlTextureListBox(0, 0, 512, 256, testSlider,
				new HvlTextureButton(64, 64, 32, 32, textureLoader
						.getResource(2), textureLoader.getResource(3),
						textureLoader.getResource(4)), new HvlTextureButton(64,
						64, 32, 32, textureLoader.getResource(2),
						textureLoader.getResource(3),
						textureLoader.getResource(4)), new HvlFontPainter2D(
						textureLoader.getResource(1), HvlFontUtil.DEFAULT,
						2048, 2048, 112, 144, 18),
				textureLoader.getResource(5), textureLoader.getResource(6),
				textureLoader.getResource(7), 32, 5);
		testListBox.setTextScale(0.25f);
		testListBox.setAutoSize(true);
		testListBox.setBackground(textureLoader.getResource(2));
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
		testArranger.addChild(testLabel);
		testArranger.addChild(testCheck);
		testArranger.addChild(testButton);
		testArranger.addChild(testTextBox);
		// testArranger.addChild(testSlider);
		testArranger.addChild(testListBox);
		testArranger.setAlign(0.5f);
		testMenu.add(testArranger);

		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta) {
		HvlMenu.updateMenus(delta);
	}

}
