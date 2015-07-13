package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeResizable;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
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
	private HvlListBox testListBox;

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

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);

		HvlComponentDefault.setDefault(new HvlTextButton.Builder().setOffDrawable(new HvlTextureDrawable(textureLoader.getResource(2)))
				.setHoverDrawable(new HvlTextureDrawable(textureLoader.getResource(3))).setOnDrawable(new HvlTextureDrawable(textureLoader.getResource(4)))
				.setFont(fontPainter).setTextScale(0.5f).setTextColor(Color.red).setxAlign(0.5f).setyAlign(0.5f).build());

		HvlComponentDefault.setDefault(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.VERTICAL).setAlign(0.5f).build());

		HvlComponentDefault.setDefault(new HvlCheckbox.Builder().setOffDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.blue)))
				.setOffHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.cyan)))
				.setOnDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.red)))
				.setOnHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.pink))).build());

		HvlComponentDefault.setDefault(new HvlSlider.Builder().setWidth(32).setHeight(512).setDirection(SliderDirection.VERTICAL).setHandleWidth(16)
				.setHandleHeight(16).setValue(0.0f).setHandleUpDrawable(new HvlTextureDrawable(textureLoader.getResource(6)))
				.setHandleDownDrawable(new HvlTextureDrawable(textureLoader.getResource(7)))
				.setBackground(new HvlTextureDrawable(textureLoader.getResource(5))).setHandleStartOffset(8).setHandleEndOffset(8).setSnapInterval(0.1f)
				.build());

		HvlComponentDefault.setDefault(new HvlListBox.Builder()
				.setScrollBar(new HvlSlider.Builder().setWidth(32).setHeight(512).setDirection(SliderDirection.VERTICAL).build()).setFont(fontPainter)
				.setItemBackgroundOff(new HvlTextureDrawable(textureLoader.getResource(5)))
				.setItemBackgroundHover(new HvlTextureDrawable(textureLoader.getResource(6)))
				.setItemBackgroundOn(new HvlTextureDrawable(textureLoader.getResource(7))).setItemHeight(32.0f).setMaxVisibleItems(5).setTextScale(0.25f)
				.setAutoSize(true).setBackground(new HvlTextureDrawable(textureLoader.getResource(2))).setFullBackground(true).build());

		HvlComponentDefault.setDefault(new HvlTextBox.Builder()
				.setFocusedDrawable(
						new HvlTiledRectDrawable(new HvlTiledRect(textureLoader.getResource(2), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16)))
				.setUnfocusedDrawable(
						new HvlTiledRectDrawable(new HvlTiledRect(textureLoader.getResource(4), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16)))
				.setFont(fontPainter).setTextScale(0.4f).setForceLowercase(true).setOffsetX(12f).setOffsetY(12f).build());

		HvlComponentDefault.setDefault(new HvlLabel.Builder().setFont(fontPainter).setColor(Color.red).build());

		testMenu = new HvlMenu() {

		};
		testLabel = new HvlLabel.Builder().setFont(fontPainter).setText("testing!").setColor(Color.red).build();

		testCheck = new HvlCheckbox.Builder().setWidth(64).setHeight(64).build();

		testButton = new HvlTextButton.Builder().setWidth(256).setHeight(128).setText("hey!").build();

		testTextBox = new HvlTextBox.Builder().setWidth(512).setHeight(96).setText("hey").setMaxCharacters(10).build();

		testListBox = new HvlListBox.Builder().setWidth(512).setHeight(256).addItem("hey").addItem("nice to meet you").addItem("nice to be you")
				.addItem("derp!").addItem("derp2!").addItem("derp3!").addItem("derp4!").addItem("derp5!").addItem("derp6!").addItem("derp7!").build();

		testArranger = new HvlArrangerBox.Builder().setX(64).setY(64).setWidth(Display.getWidth() - 64).setHeight(720).add(testLabel).add(testCheck)
				.add(testButton).add(testTextBox).add(testListBox).build();
		testMenu.add(testArranger);

		HvlMenu.setCurrent(testMenu);
	}

	@Override
	public void update(float delta) {
		// testArranger.setWidth(Display.getWidth());
		// testArranger.setHeight(Display.getHeight());
		HvlMenu.updateMenus(delta);
	}

}
