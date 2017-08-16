package com.osreboot.ridhvl.menu;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.HvlTextBox;
import com.osreboot.ridhvl.menu.component.collection.HvlLabeledButton;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlMenuDefault {

	public static final String MISSING_TEXT = "MiSsInG tExT";

	public static final HvlAction1<HvlFontPainter2D> MD_OSMINIMAL = new HvlAction1<HvlFontPainter2D>(){
		@Override
		public void run(HvlFontPainter2D aArg){
			HvlArrangerBox defaultArrangerBox = new HvlArrangerBox.Builder().setStyle(ArrangementStyle.VERTICAL)
					.setWidth(Display.getWidth()).setHeight(Display.getHeight()).setxAlign(0.5f).setyAlign(0.5f).build();
			defaultArrangerBox.setBorderU(4f);
			defaultArrangerBox.setBorderD(4f);
			HvlComponentDefault.setDefault(defaultArrangerBox);

			HvlComponentDefault.setDefault(new HvlLabel(aArg, MISSING_TEXT, Color.white));

			HvlLabeledButton defaultLabeledButton = new HvlLabeledButton(128, 32, new HvlComponentDrawable(){
				@Override
				public void draw(float deltaArg, float xArg, float yArg, float widthArg, float heightArg){
					hvlDrawQuad(xArg, yArg, widthArg, heightArg, Color.darkGray);
				}
			}, new HvlComponentDrawable(){
				@Override
				public void draw(float deltaArg, float xArg, float yArg, float widthArg, float heightArg) {
					hvlDrawQuad(xArg, yArg, widthArg, heightArg, Color.gray);
				}
			}, aArg, MISSING_TEXT, Color.white);
			defaultLabeledButton.setAlign(0.5f);
			HvlComponentDefault.setDefault(defaultLabeledButton);

			HvlTextBox defaultTextBox = new HvlTextBox(256, 32, new HvlComponentDrawable(){
				@Override
				public void draw(float deltaArg, float xArg, float yArg, float widthArg, float heightArg){
					hvlDrawQuad(xArg, yArg, widthArg, heightArg, Color.darkGray);
				}
			}, new HvlComponentDrawable(){
				@Override
				public void draw(float deltaArg, float xArg, float yArg, float widthArg, float heightArg) {
					hvlDrawQuad(xArg, yArg, widthArg, heightArg, Color.gray);
				}
			}, 1f, Color.white, aArg, MISSING_TEXT);
			HvlComponentDefault.setDefault(defaultTextBox);
		}
	};

}
