package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ExpandingRectangleTest extends HvlTemplate2D {
	
	static {
		HvlControllerProfile.setDebugOutput(true);

		AccessController.doPrivileged(new PrivilegedAction<Object>(){//TODO add this to an initialize method in HvlController
			public Object run(){
				String os = System.getProperty("os.name", "").trim();
				if(os.startsWith("Windows 8")){
					System.setProperty("jinput.useDefaultPlugin", "false");
					System.setProperty("net.java.games.input.plugins", "net.java.games.input.DirectAndRawInputEnvironmentPlugin");
				}
				return null;
			}
		});
	}
	
	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Ridhvl Expanding Rectangle Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	HvlTiledRect testRect;
	
	Color color;

	HvlCPG_Gamepad profile;
	
	@Override
	public void initialize() {
		profile = new HvlCPG_Gamepad();
		textureLoader.loadResource("Icon");
		color = Color.white;
		testRect = new HvlTiledRect(textureLoader.getResource(0), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 16, 16);
	}

	@Override
	public void update(float delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, getWhite512(), color);

		testRect.setTotalWidth(testRect.getTotalWidth() + profile.getValue(HvlCPG_Gamepad.TRIGGER_LEFT) * 256 * delta);
		testRect.setTotalHeight(testRect.getTotalHeight() + profile.getValue(HvlCPG_Gamepad.TRIGGER_RIGHT) * 256 * delta);

		testRect.setX(640 - (testRect.getTotalWidth()/2));
		testRect.setY(360 - (testRect.getTotalHeight()/2));

		testRect.draw();
	}

}