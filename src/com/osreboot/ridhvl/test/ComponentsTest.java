package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeResizable;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlCompMethodOverride;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlButton.OnClickedCommand;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.HvlListBox;
import com.osreboot.ridhvl.menu.component.HvlSlider;
import com.osreboot.ridhvl.menu.component.HvlSlider.SliderDirection;
import com.osreboot.ridhvl.menu.component.HvlTextBox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextButton;
import com.osreboot.ridhvl.menu.component.collection.HvlTextCheckbox;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureDrawable;
import com.osreboot.ridhvl.menu.component.collection.HvlTiledRectDrawable;
import com.osreboot.ridhvl.painter.HvlGradient;
import com.osreboot.ridhvl.painter.HvlGradient.Style;
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

	public ComponentsTest() {
		super(60, 1280, 720, "Ridhvl Components Test", new HvlDisplayModeResizable());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize() {
		Keyboard.enableRepeatEvents(true);
		textureLoader.loadResource("Icon");
		textureLoader.loadResource("Font");
		textureLoader.loadResource("Cursor");

		HvlGradient grad = new HvlGradient(Style.RADIAL);
		grad.addStop(0, Color.orange);
		grad.addStop(1, Color.darkGray);
		Texture gradient = grad.toTexture(512, 512, 256, 256, 0, 0);
		grad.addStop(0, Color.blue);
		grad.addStop(1, Color.lightGray);
		Texture gradient2 = grad.toTexture(512, 512, 256, 256, 0, 0);
		grad.addStop(0, Color.yellow);
		grad.addStop(1, Color.green);
		Texture gradient3 = grad.toTexture(512, 512, 256, 256, 0, 0);

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 192, 256, 10);

		HvlComponentDefault.setDefault(new HvlTextButton.Builder().setOffDrawable(new HvlTextureDrawable(gradient2))
				.setHoverDrawable(new HvlTextureDrawable(textureLoader.getResource(2))).setOnDrawable(new HvlTextureDrawable(gradient)).setFont(fontPainter)
				.setTextScale(0.5f).setTextColor(Color.red).setxAlign(0.5f).setyAlign(0.5f).build());

		HvlComponentDefault.setDefault(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.VERTICAL).setAlign(0.5f).build());

		HvlComponentDefault.setDefault(new HvlCheckbox.Builder().setOffDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.blue)))
				.setOffHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.cyan)))
				.setOnDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.red)))
				.setOnHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.pink))).build());

		HvlComponentDefault.setDefault(new HvlTextCheckbox.Builder().setOffDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.blue)))
				.setOffHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.cyan)))
				.setOnDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.red)))
				.setOnHoverDrawable(new HvlTextureDrawable(HvlTextureUtil.getColoredRect(64, 64, Color.pink))).setFont(fontPainter).setText("testing finally")
				.setColor(Color.magenta).setScale(0.25f).setSpacing(32f).build());

		HvlComponentDefault.setDefault(new HvlSlider.Builder().setWidth(32).setHeight(512).setDirection(SliderDirection.VERTICAL).setHandleWidth(16)
				.setHandleHeight(16).setValue(0.0f).setHandleUpDrawable(new HvlTextureDrawable(textureLoader.getResource(2)))
				.setHandleDownDrawable(new HvlTextureDrawable(textureLoader.getResource(2))).setBackground(new HvlTextureDrawable(getWhite512()))
				.setHandleStartOffset(8).setHandleEndOffset(8).setSnapInterval(0.1f).setTextureDirection(SliderDirection.HORIZONTAL).build());

		HvlComponentDefault.setDefault(new HvlListBox.Builder()
				.setScrollBar(new HvlSlider.Builder().setWidth(32).setHeight(128).setDirection(SliderDirection.VERTICAL).build()).setFont(fontPainter)
				.setItemBackgroundOff(new HvlTextureDrawable(textureLoader.getResource(2)))
				.setItemBackgroundHover(new HvlTextureDrawable(textureLoader.getResource(2)))
				.setItemBackgroundOn(new HvlTextureDrawable(textureLoader.getResource(2))).setItemHeight(48.0f).setMaxVisibleItems(5).setTextScale(0.25f)
				.setAutoSize(false).setBackground(new HvlTextureDrawable(gradient)).setFullBackground(true).build());

		HvlComponentDefault.setDefault(new HvlTextBox.Builder()
				.setFocusedDrawable(new HvlTiledRectDrawable(new HvlTiledRect(gradient3, 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16)))
				.setUnfocusedDrawable(new HvlTiledRectDrawable(new HvlTiledRect(gradient3, 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 0, 0, 16, 16)))
				.setFont(fontPainter).setTextScale(0.4f).setForceLowercase(true).setOffsetX(12f).setOffsetY(12f).build());

		HvlComponentDefault.setDefault(new HvlLabel.Builder().setFont(fontPainter).setColor(Color.red).build());

		testMenu = new HvlMenu() {

		};

		testLabel = new HvlLabel.Builder().setFont(fontPainter).setText("testing!").setColor(Color.red).build();

		testCheck = new HvlTextCheckbox.Builder().setWidth(64).setHeight(64).setDrawOverride(new HvlCompMethodOverride() {

			@Override
			public void run(HvlComponent calling, float delta) {
				calling.draw(delta);
				System.out.println("OVERRIDE!");

			}
		}).build();

		testButton = new HvlTextButton.Builder().setWidth(256).setHeight(128).setText("hey!").setClickedCommand(new OnClickedCommand() {
			@Override
			public void run(HvlButton callingButton) {
				HvlMenu.addPopup(new HvlMenu() {
					{
						add(new HvlTextButton.Builder().setWidth(256).setHeight(128).setText("hey!").setClickedCommand(new OnClickedCommand() {
							@Override
							public void run(HvlButton callingButton) {
								System.out.println("BUTTONCLICK1!");
								HvlMenu.removePopup();
							}
						}).build());
					}
				}, true);

				HvlMenu.addPopup(new HvlMenu() {
					{
						add(new HvlTextButton.Builder().setX(512).setY(256).setWidth(256).setHeight(128).setText("hey!")
								.setClickedCommand(new OnClickedCommand() {
									@Override
									public void run(HvlButton callingButton) {
										System.out.println("BUTTONCLICK2!");
										HvlMenu.removePopup();
									}
								}).build());
					}
				}, true);
			}
		}).build();

		testTextBox = new HvlTextBox.Builder().setWidth(512).setHeight(96).setText("hey").setMaxCharacters(10).build();

		testListBox = new HvlListBox.Builder().setWidth(512).setHeight(128).addItem("hey").addItem("nice to meet you").addItem("nice to be you")
				.addItem("derp!").addItem("derp2!").addItem("derp3!").addItem("derp4!").addItem("derp5!").addItem("derp6!").addItem("derp7!").build();

		testArranger = new HvlArrangerBox.Builder()
				.setX(0)
				.setY(0)
				.setWidth(Display.getWidth() - 64)
				.setHeight(720)
				.add(testLabel)
				.add(testCheck)
				.add(testButton)
				.add(testTextBox)
				.add(new HvlSlider.Builder().setWidth(256).setHeight(32).setDirection(SliderDirection.HORIZONTAL)
						.setTextureDirection(SliderDirection.HORIZONTAL).build()).add(testListBox).build();
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
