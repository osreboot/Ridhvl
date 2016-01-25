package com.osreboot.ridhvl.test;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlDebugUtil;
import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.HvlMapRaytraceResult;
import com.osreboot.ridhvl.map.collection.HvlSimpleCollisionProfiles;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;
import com.osreboot.ridhvl.painter.HvlShader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TileTest extends HvlTemplateInteg2D {

	public static TestTilemapEntity ent;

	public HvlMap map;

	public HvlFontPainter2D font;

	public HvlCamera camera;

	public HvlRenderFrame frame = null;
	public HvlShader shader;
	
	private static float[] pixels;
	private static final int pixelsWidth = 80, pixelsHeight = 45;
	
	public TileTest() {
		super(60, 1280, 720, "Ridhvl Tilemap Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Tilemap");
		getTextureLoader().loadResource("Icon");
		getTextureLoader().loadResource("Font");

		camera = new HvlCamera(0, 0, 0, 1f, new HvlCoord());

		font = new HvlFontPainter2D(getTextureLoader().getResource(2), HvlFontUtil.DEFAULT, 192, 256);

		pixels = new float[(int)(pixelsHeight * pixelsWidth)];
		
		map = HvlMap.load("res/TestLoadMap", 0, 0, 64, 64, getTexture(0), false);
		map.setCollisionDebugDraw(true);
		map.setOverdrawAmount(1.0f);
		map.mapTileToCollision(9, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(11, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(2, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(18, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(10, new HvlSimpleCollisionProfiles.Square(0));
		map.mapTileToCollision(1,
				new HvlSimpleCollisionProfiles.CustomMiddle(false, true, false, true, false, false, false, false, 0));
		map.mapTileToCollision(3,
				new HvlSimpleCollisionProfiles.CustomMiddle(true, false, false, true, false, false, false, false, 0));
		map.mapTileToCollision(17,
				new HvlSimpleCollisionProfiles.CustomMiddle(false, true, true, false, false, false, false, false, 0));
		map.mapTileToCollision(19,
				new HvlSimpleCollisionProfiles.CustomMiddle(true, false, true, false, false, false, false, false, 0));

		map.save("res/TestSaveMap");

		ent = new TestTilemapEntity(0, 0, map);

		try{
			frame = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
		}catch(FBOUnsupportedException e){
			e.printStackTrace();
		}
		shader = new HvlShader("shader\\ShadowMap.hvlfg");
		camera.setAlignment(HvlCamera.ALIGNMENT_CENTER);
		System.out.println(shader.getFragLog());
	}
	
	@Override
	public void update(final float delta) {
		map.update(delta);
		ent.update(delta);
		camera.setPosition(ent.getX(), ent.getY());
		HvlRenderFrame.setCurrentRenderFrame(frame);
		camera.doTransformed(new HvlAction0(){
			@Override
			public void run(){
				map.draw(delta);
				List<HvlMapRaytraceResult> colls = map.raytrace(new HvlCoord(ent.getX(), ent.getY()),
						new HvlCoord(HvlCursor.getCursorX() + camera.getX() - (Display.getWidth() / 2),
								HvlCursor.getCursorY() + camera.getY() - (Display.getHeight() / 2)));
				
				for(int i = 0; i < 3600; i++){
					float x = (float)(i%pixelsWidth)/(float)pixelsWidth*Display.getWidth();
					float y = (float)(i/pixelsWidth)/(float)pixelsHeight*Display.getHeight();
					pixels[i] = map.raytrace(new HvlCoord(ent.getX(), ent.getY()), 
							new HvlCoord(ent.getX() + x - (Display.getWidth() / 2),
									ent.getY() + y - (Display.getHeight() / 2))
					).isEmpty() ? 0f : 1f;
				}
				
				if (!colls.isEmpty()) {

					HvlCoord coll = colls.get(0).getLocation();
					
					HvlPainter2D.hvlDrawLine(coll.x - 8, coll.y - 8, coll.x + 8, coll.y + 8, Color.green, 4.0f);
					HvlPainter2D.hvlDrawLine(coll.x - 8, coll.y + 8, coll.x + 8, coll.y - 8, Color.green, 4.0f);
				}
				HvlPainter2D.hvlDrawQuadc(ent.getX(), ent.getY(), 20, 20, Color.orange);
				HvlDebugUtil.drawFPSCounter(font, camera.getX() - (Display.getWidth() / 2), camera.getY() - (Display.getHeight() / 2), 0.4f, Color.red);
			}
		});
		HvlRenderFrame.setCurrentRenderFrame(null);
		HvlShader.setCurrentShader(shader);
		shader.sendFloatArray("pixels", pixels);
		HvlPainter2D.hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), frame);
		HvlShader.setCurrentShader(null);
	}
}
