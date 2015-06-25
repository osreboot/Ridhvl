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
import com.osreboot.ridhvl.menu.component.HvlTextureSlider;
import com.osreboot.ridhvl.menu.component.HvlTextureSlider.SliderDirection;
import com.osreboot.ridhvl.menu.component.collection.HvlColorCheckbox;
import com.osreboot.ridhvl.menu.component.collection.HvlExpandableRectTextBox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureButton;
import com.osreboot.ridhvl.painter.painter2d.HvlExpandingRectangle;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ComponentsTest extends HvlTemplate2DBasic {

	private HvlMenu testMenu;
	private HvlArrangerBox testArranger;
	private HvlLabel testLabel;
	private HvlCheckbox testCheck;
	private HvlButton testButton;
	private HvlExpandableRectTextBox testTextBox;
	private HvlTextureSlider testSlider;

	public ComponentsTest() {
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
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
		testLabel = new HvlLabel(0, 0, 720, fontPainter, "testing!", Color.red);
		testCheck = new HvlColorCheckbox(0, 0, 64, 64, 720, Color.blue,
				Color.cyan, Color.red, Color.pink, textureLoader.getResource(0)) {
			public void onChanged(boolean state) {
				System.out.println(state);
			}
		};
		testButton = new HvlTextureButton(64, 64, 128, 128, 720,
				textureLoader.getResource(2), textureLoader.getResource(3),
				textureLoader.getResource(4)) {
			@Override
			public void onTriggered() {
				System.out.println("BUTTTTOOONN!");
			}
		};
		testTextBox = new HvlExpandableRectTextBox(0, 0, 512, 96, 720, "hey",
				new HvlExpandingRectangle(textureLoader.getResource(2), 0.125f,
						0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16),
				new HvlExpandingRectangle(textureLoader.getResource(4), 0.125f,
						0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16),
				fontPainter);
		testTextBox.setTextScale(0.4f);
		testTextBox.setMaxCharacters(10);
		testTextBox.setForceLowercase(true);
		testTextBox.setOffsetX(12f);
		testTextBox.setOffsetY(12f);
		testSlider = new HvlTextureSlider(0, 0, 512, 32, 720,
				textureLoader.getResource(5), textureLoader.getResource(6), textureLoader.getResource(7),
				SliderDirection.HORIZONTAL, 16, 16, 0.0f);
		testSlider.setHandleStartOffset(32);
		testSlider.setHandleEndOffset(32);
		testSlider.setSnapInterval(0.05f);
		
		
		
		testArranger = new HvlArrangerBox(0, 0, 1280, 720, 720,
				ArrangementStyle.VERTICAL);
		testArranger.add(testLabel);
		testArranger.add(testCheck);
		testArranger.add(testButton);
		testArranger.add(testTextBox);
		testArranger.add(testSlider);
		testArranger.setAlign(0.0f);
		testMenu.add(testArranger);

		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta) {
		HvlMenu.updateMenus(delta);
	}

}
