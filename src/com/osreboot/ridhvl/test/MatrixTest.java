package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMatrix2D;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class MatrixTest extends HvlTemplateInteg2D{
	
	public MatrixTest(){
		super(60, 1280, 720, "Ridhvl Matrix2D Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		
	}
	
	float progress = 0;
	float value = 0;

	@Override
	public void update(float delta){
		progress += delta/2;
		if(progress > 1) progress = 0;
		value = (float)Math.sin(progress*Math.PI*2);
		
		
		//9-box complete test
		drawModel(new HvlCoord2D(0, -value * 20), new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(0, value * 20), new HvlCoord2D(100, 100));
		drawModel(new HvlCoord2D(-value * 20, 0), new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(-value * 20, 0), new HvlCoord2D(500, 100));
		drawModel(new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(0, value * 20), new HvlCoord2D(0, value * 20), new HvlCoord2D(900, 100));
		drawModel(new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(value * 20, 0), new HvlCoord2D(-value * 20, 0), new HvlCoord2D(100, 300));
		drawModel(new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(500, 300));
		drawModel(new HvlCoord2D(0, -value * 20), new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(0, -value * 20), new HvlCoord2D(900, 300));
		drawModel(
				new HvlCoord2D((-50*(float)Math.cos(value*Math.PI*0.2))-(-50*(float)Math.sin(value*Math.PI*0.2))+50, (-50*(float)Math.cos(value*Math.PI*0.2))+(-50*(float)Math.sin(value*Math.PI*0.2))+50),
				new HvlCoord2D((50*(float)Math.cos(value*Math.PI*0.2))-(-50*(float)Math.sin(value*Math.PI*0.2))-50, (-50*(float)Math.cos(value*Math.PI*0.2))+(50*(float)Math.sin(value*Math.PI*0.2))+50),
				new HvlCoord2D((50*(float)Math.cos(value*Math.PI*0.2))-(50*(float)Math.sin(value*Math.PI*0.2))-50, (50*(float)Math.cos(value*Math.PI*0.2))+(50*(float)Math.sin(value*Math.PI*0.2))-50),
				new HvlCoord2D((-50*(float)Math.cos(value*Math.PI*0.2))-(50*(float)Math.sin(value*Math.PI*0.2))+50, (50*(float)Math.cos(value*Math.PI*0.2))+(-50*(float)Math.sin(value*Math.PI*0.2))-50),
				new HvlCoord2D(100, 500));
		drawModel(new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(value * 20, -value * 20), new HvlCoord2D(value * 20, value * 20), new HvlCoord2D(-value * 20, value * 20), new HvlCoord2D(500, 500));
		drawModel(new HvlCoord2D(-value * 20, -value * 20), new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(), new HvlCoord2D(900, 500));
		
		
		
		/*//Single uniform box test
		HvlCoord s1 = new HvlCoord(100, 100);
		HvlCoord s2 = new HvlCoord(300, 100);
		HvlCoord s3 = new HvlCoord(300, 300);
		HvlCoord s4 = new HvlCoord(100, 300);
		
		HvlCoord e1 = new HvlCoord(400, 400);
		HvlCoord e2 = new HvlCoord(600, 400);
		HvlCoord e3 = new HvlCoord(600, 600);
		HvlCoord e4 = new HvlCoord(400, 600);
		
		HvlMatrix2D m = new HvlMatrix2D(s1, s2, s3, s4, e1, e2, e3, e4);
		
		HvlCoord p = m.map(new HvlCoord(HvlCursor.getCursorX(), HvlCursor.getCursorY()));
		hvlDrawLine(s1.x, s1.y, s2.x, s2.y, Color.white);
		hvlDrawLine(s2.x, s2.y, s3.x, s3.y, Color.white);
		hvlDrawLine(s3.x, s3.y, s4.x, s4.y, Color.white);
		hvlDrawLine(s4.x, s4.y, s1.x, s1.y, Color.white);
		hvlDrawLine(e1.x, e1.y, e2.x, e2.y, Color.orange);
		hvlDrawLine(e2.x, e2.y, e3.x, e3.y, Color.orange);
		hvlDrawLine(e3.x, e3.y, e4.x, e4.y, Color.orange);
		hvlDrawLine(e4.x, e4.y, e1.x, e1.y, Color.orange);
		hvlDrawQuadc(p.x, p.y, 10, 10, Color.green);
		*/
		
		
		//Bounds test
		/*HvlCoord2D s1 = new HvlCoord2D(100, 120);
		HvlCoord2D s2 = new HvlCoord2D(300, 100);
		HvlCoord2D s3 = new HvlCoord2D(500, 400);
		HvlCoord2D s4 = new HvlCoord2D(HvlCursor.getCursorX(), HvlCursor.getCursorY());
		
		HvlCoord2D e1 = new HvlCoord2D(-1, -1);
		HvlCoord2D e2 = new HvlCoord2D(1, -1);
		HvlCoord2D e3 = new HvlCoord2D(1, 1);
		HvlCoord2D e4 = new HvlCoord2D(-1, 1);
		
		HvlMatrix2D m = new HvlMatrix2D(s1, s2, s3, s4, e1, e2, e3, e4);
		
		hvlDrawPolygon(0, 0, new HvlCoord2D[]{s1, s2, s3, s4}, Color.red);
		
		for(float x = 0; x < 1; x += 1f/100f){
			for(float y = 0; y < 1; y += 1f/100f){
				HvlCoord2D c = new HvlCoord2D(x*500, y*500);
				HvlCoord2D p = m.map(c);
				boolean inside = Math.abs(p.x) >= 1 || Math.abs(p.y) >= 1;
				hvlDrawQuadc(c.x, c.y, 1f, 1f, inside ? Color.orange : Color.blue);
			}
		}*/
		
	}
	
	private void drawModel(HvlCoord2D dv1, HvlCoord2D dv2, HvlCoord2D dv3, HvlCoord2D dv4, HvlCoord2D loc){
		hvlDrawQuad(
				new HvlCoord2D(loc.x + dv1.x, loc.y + dv1.y), new HvlCoord2D(loc.x + 100 + dv2.x, loc.y + dv2.y),
				new HvlCoord2D(loc.x + 100 + dv3.x, loc.y + 100 + dv3.y), new HvlCoord2D(loc.x + dv4.x, loc.y + 100 + dv4.y), Color.darkGray);
		
		HvlMatrix2D mat = new HvlMatrix2D(
				new HvlCoord2D(loc.x + dv1.x, loc.y + dv1.y), new HvlCoord2D(loc.x + 100 + dv2.x, loc.y + dv2.y),
				new HvlCoord2D(loc.x + 100 + dv3.x, loc.y + 100 + dv3.y), new HvlCoord2D(loc.x + dv4.x, loc.y + 100 + dv4.y),
				new HvlCoord2D(loc.x + 200, loc.y), new HvlCoord2D(loc.x + 300, loc.y), new HvlCoord2D(loc.x + 300, loc.y + 100), new HvlCoord2D(loc.x + 200, loc.y + 100));
		
		float value = 1/12f;
		
		for(float x = 0; x < 1; x += value){
			for(float y = 0; y < 1; y += value){
				HvlCoord2D fc = new HvlCoord2D(loc.x - 10 + (x*120), loc.y - 10 + (y*120));
				HvlCoord2D tc = mat.map(fc);
				hvlDrawQuadc(fc.x, fc.y, 5f, 5f, new Color(x, 0f, y));
				hvlDrawQuadc(tc.x, tc.y, 5f, 5f, new Color(x, 0f, y));
			}
		}
	}

}
