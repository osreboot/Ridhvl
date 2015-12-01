package com.osreboot.ridhvl.test;

import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlSequentialLoader;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlProgressBar;
import com.osreboot.ridhvl.menu.component.collection.HvlTextureDrawable;
import com.osreboot.ridhvl.painter.HvlGradient;
import com.osreboot.ridhvl.painter.HvlGradient.Style;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class SequentialLoaderTest extends HvlTemplateInteg2D {
	
	public TempMenu menu;
	
	public class TempMenu extends HvlMenu {
		public HvlProgressBar pb;
		
		public TempMenu() {
			pb = new HvlProgressBar.Builder().setWidth(Display.getWidth() - 8).setHeight(64).setX(4).setY(Display.getHeight() / 2 - 32).build();
			add(pb);
		}
	}
	
	HvlSequentialLoader seq;
	
	public SequentialLoaderTest() {
		super(60, 1280, 720, "Ridhvl Distributed Loader Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Icon");
		
		HvlComponentDefault.setDefault(new HvlProgressBar.Builder().setWidth(512).setHeight(32)
				.setBackground(new HvlTextureDrawable(new HvlGradient(Style.RADIAL).addStop(0.0f, Color.white)
						.addStop(0.75f, Color.white).addStop(1.0f, Color.red).toTexture(512, 512, 256, 256, 0, 0)))
				.setForeground(new HvlTextureDrawable(getTextureLoader().getResource(0)))
				.setDirection(HvlProgressBar.Direction.HORIZONTAL).setBorderL(4f).setBorderR(4f).setBorderU(4f)
				.setBorderD(4f).build());
		
		HashMap<String, HvlTextureLoader> toLoad = new HashMap<>();
		for (int i = 0; i < 375; i++) {
			toLoad.put(String.format("WASplash/WubSplash_%03d", i), getTextureLoader());
		}
		seq = new HvlSequentialLoader(toLoad);
		
		menu = new TempMenu();
		HvlMenu.setCurrent(menu);
	}

	@Override
	public void update(float delta) {
		seq.loadObject();
		menu.pb.setValue(seq.getProgress());
		HvlMenu.updateMenus(delta);
	}
}
