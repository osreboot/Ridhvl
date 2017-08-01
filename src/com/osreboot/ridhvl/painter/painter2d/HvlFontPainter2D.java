package com.osreboot.ridhvl.painter.painter2d;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;

public class HvlFontPainter2D {
	
	public static class Preset {
		
		public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY + 1;

		@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
		public static final HvlAction0 INIT_ACTION = new HvlAction0(){
			@Override
			public void run(){
				FP_INOFFICIAL.charEndUVs.put('1', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put('\'', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put('"', 40f/96f);
				FP_INOFFICIAL.charEndUVs.put(')', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put('(', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put(']', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put('[', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put('?', 64f/96f);
				FP_INOFFICIAL.charEndUVs.put('!', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put('.', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put(',', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put('*', 40f/96f);
				FP_INOFFICIAL.charEndUVs.put('^', 64f/96f);
				FP_INOFFICIAL.charEndUVs.put('-', 80f/96f);
				FP_INOFFICIAL.charEndUVs.put('+', 80f/96f);
				FP_INOFFICIAL.charEndUVs.put('=', 80f/96f);
				FP_INOFFICIAL.charEndUVs.put('|', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put(';', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put(':', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put('>', 70f/96f);
				FP_INOFFICIAL.charEndUVs.put('<', 70f/96f);
				FP_INOFFICIAL.charEndUVs.put('f', 48f/96f);
				FP_INOFFICIAL.charEndUVs.put('i', 16f/96f);
				FP_INOFFICIAL.charEndUVs.put('j', 48f/96f);
				FP_INOFFICIAL.charEndUVs.put('k', 80f/96f);
				FP_INOFFICIAL.charEndUVs.put('l', 32f/96f);
				FP_INOFFICIAL.charEndUVs.put('r', 48f/96f);
				FP_INOFFICIAL.charEndUVs.put('t', 48f/96f);
				
				FP_INOFFICIAL_Q.charEndUVs.put('1', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('\'', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('"', 40f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put(')', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('(', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put(']', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('[', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('?', 64f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('!', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('.', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put(',', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('*', 40f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('^', 64f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('-', 80f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('+', 80f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('=', 80f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('|', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put(';', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put(':', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('>', 70f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('<', 70f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('f', 48f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('i', 16f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('j', 48f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('k', 80f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('l', 32f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('r', 48f/96f);
				FP_INOFFICIAL_Q.charEndUVs.put('t', 48f/96f);
			}
		};
		
		public static final Preset FP_INOFFICIAL = new Preset(HvlFontUtil.COMPUTATIONALCASE, 96, 192, 21, 48, new HashMap<Character, Float>(), new HashMap<Character, Float>());
		public static final Preset FP_INOFFICIAL_Q = new Preset(HvlFontUtil.COMPUTATIONALCASE, 96/4, 192/4, 21, 48/4, new HashMap<Character, Float>(), new HashMap<Character, Float>());
		//TODO global font offset (for chars extending below write line, etc.)
		
		char[] chars;
		float charWidth, charHeight;
		int charsAcross;
		float spaceSize;
		Map<Character, Float> charStartUVs, charEndUVs;

		public Preset(char[] chars, float charWidth, float charHeight, int charsAcross) {
			this.chars = chars;
			this.charWidth = charWidth;
			this.charHeight = charHeight;
			this.charsAcross = charsAcross;
			this.spaceSize = charWidth;
			charStartUVs = new HashMap<>();
			charEndUVs = new HashMap<>();
			for (char c : chars) {
				charStartUVs.put(c, 0.0f);
				charEndUVs.put(c, 1.0f);
			}
		}

		public Preset(char[] chars, float charWidth, float charHeight, int charsAcross, float spaceSize) {
			this.chars = chars;
			this.charWidth = charWidth;
			this.charHeight = charHeight;
			this.charsAcross = charsAcross;
			this.spaceSize = spaceSize;
			charStartUVs = new HashMap<>();
			charEndUVs = new HashMap<>();
			for (char c : chars) {
				charStartUVs.put(c, 0.0f);
				charEndUVs.put(c, 1.0f);
			}
		}

		public Preset(char[] chars, float charWidth, float charHeight, int charsAcross, Map<Character, Float> charStartUVs, Map<Character, Float> charEndUVs) {
			this.chars = chars;
			this.charWidth = charWidth;
			this.charHeight = charHeight;
			this.charsAcross = charsAcross;
			this.spaceSize = charWidth;
			this.charStartUVs = charStartUVs;
			this.charEndUVs = charEndUVs;
			for (char c : chars) {
				if(!charStartUVs.containsKey(c)) charStartUVs.put(c, 0.0f);
				if(!charEndUVs.containsKey(c)) charEndUVs.put(c, 1.0f);
			}
		}

		public Preset(char[] chars, float charWidth, float charHeight, int charsAcross, float spaceSize, Map<Character, Float> charStartUVs, Map<Character, Float> charEndUVs) {
			this.chars = chars;
			this.charWidth = charWidth;
			this.charHeight = charHeight;
			this.charsAcross = charsAcross;
			this.spaceSize = spaceSize;
			this.charStartUVs = charStartUVs;
			this.charEndUVs = charEndUVs;
			for (char c : chars) {
				if(!charStartUVs.containsKey(c)) charStartUVs.put(c, 0.0f);
				if(!charEndUVs.containsKey(c)) charEndUVs.put(c, 1.0f);
			}
		}

		public char[] getChars() {
			return chars;
		}

		public float getCharWidth() {
			return charWidth;
		}

		public float getCharHeight() {
			return charHeight;
		}

		public int getCharsAcross() {
			return charsAcross;
		}

		public float getSpaceSize() {
			return spaceSize;
		}

		public Map<Character, Float> getCharStartUVs() {
			return charStartUVs;
		}

		public Map<Character, Float> getCharEndUVs() {
			return charEndUVs;
		}
	}

	private Texture texture;
	private char[] chars;
	private float charWidth, charHeight;
	private int charsAcross;
	private float scale;
	private float rowSpacing;
	private float charSpacing;
	private float spaceSize;
	private Map<Character, Float> charStartUVs, charEndUVs;

	public HvlFontPainter2D(Texture t, char[] chars, float charWidth, float charHeight) {
		texture = t;
		this.chars = chars;
		this.charWidth = charWidth;
		spaceSize = charWidth;
		this.charHeight = charHeight;
		this.charsAcross = (int) (t.getImageWidth() / charWidth);
		scale = 1.0f;
		rowSpacing = 0.0f;
		charSpacing = 0.0f;
		charStartUVs = new HashMap<>();
		charEndUVs = new HashMap<>();
		for (char c : chars) {
			charStartUVs.put(c, 0.0f);
			charEndUVs.put(c, 1.0f);
		}
	}

	public HvlFontPainter2D(Texture t, char[] chars, float charWidth, float charHeight, float scale) {
		texture = t;
		this.chars = chars;
		this.charWidth = charWidth;
		spaceSize = charWidth;
		this.charHeight = charHeight;
		this.charsAcross = (int) (t.getImageWidth() / charWidth);
		this.scale = scale;
		rowSpacing = 0.0f;
		charSpacing = 0.0f;
		charStartUVs = new HashMap<>();
		charEndUVs = new HashMap<>();
		for (char c : chars) {
			charStartUVs.put(c, 0.0f);
			charEndUVs.put(c, 1.0f);
		}
	}

	public HvlFontPainter2D(Texture t, char[] chars, float charWidth, float charHeight, float scale, float rowSpacing) {
		texture = t;
		this.chars = chars;
		this.charWidth = charWidth;
		spaceSize = charWidth;
		this.charHeight = charHeight;
		this.charsAcross = (int) (t.getImageWidth() / charWidth);
		this.scale = scale;
		this.rowSpacing = rowSpacing;
		charSpacing = 0.0f;
		charStartUVs = new HashMap<>();
		charEndUVs = new HashMap<>();
		for (char c : chars) {
			charStartUVs.put(c, 0.0f);
			charEndUVs.put(c, 1.0f);
		}
	}

	public HvlFontPainter2D(Texture t, Preset p) {
		texture = t;
		chars = p.chars;
		charWidth = p.charWidth;
		charHeight = p.charHeight;
		charsAcross = p.charsAcross;
		spaceSize = p.spaceSize;
		charStartUVs = p.charStartUVs;
		charEndUVs = p.charEndUVs;
		scale = 1.0f;
		charSpacing = 0.0f;
		rowSpacing = 0.0f;
	}

	public HvlFontPainter2D(Texture t, Preset p, float scale) {
		texture = t;
		chars = p.chars;
		charWidth = p.charWidth;
		charHeight = p.charHeight;
		charsAcross = p.charsAcross;
		spaceSize = p.spaceSize;
		charStartUVs = p.charStartUVs;
		charEndUVs = p.charEndUVs;
		this.scale = scale;
		charSpacing = 0.0f;
		rowSpacing = 0.0f;
	}

	public HvlFontPainter2D(Texture t, Preset p, float scale, float charSpacing, float rowSpacing) {
		texture = t;
		chars = p.chars;
		charWidth = p.charWidth;
		charHeight = p.charHeight;
		charsAcross = p.charsAcross;
		spaceSize = p.spaceSize;
		charStartUVs = p.charStartUVs;
		charEndUVs = p.charEndUVs;
		this.scale = scale;
		this.charSpacing = charSpacing;
		this.rowSpacing = rowSpacing;
	}

	public void drawWord(String text, float x, float y, Color c) {
		drawWord(text, x, y, c, 1.0f);
	}

	public void drawWord(String text, float x, float y, Color c, float s) {
		drawWord(text, x, y, c, s, s);
	}

	public void drawWord(String text, float x, float y, Color c, float sX, float sY) {
		drawWord(text, x, y, c, sX, sY, 0.0f);
	}

	public void drawWord(String text, float x, float y, Color c, float sX, float sY, float align) {
		String[] lines = text.split("\n");

		float largestWidth = getLineWidth(text);

		float currentY = y;

		for (String line : lines) {
			line.replace("\t", "    ");
			float lineWidth = 0f;
			for (char ch : line.toCharArray()) {
				if (HvlFontUtil.containsChar(chars, ch)) {
					lineWidth += charWidth * (charEndUVs.get(ch) - charStartUVs.get(ch)) * sX * scale + charSpacing * sX * scale;
				} else if (ch == ' ') {
					lineWidth += spaceSize * sX * scale + charSpacing * sX * scale;
				} else {
					lineWidth += charWidth * sX * scale + charSpacing * sX * scale;
				}
			}

			float currentX = x + ((largestWidth - lineWidth) * align);

			for (char ch : line.toCharArray()) {
				if (HvlFontUtil.containsChar(chars, ch)) {
					float uvWidth = charEndUVs.get(ch) - charStartUVs.get(ch);
					int cIndex = HvlFontUtil.indexOfChar(chars, ch);
					int fontX = cIndex % charsAcross;
					int fontY = cIndex / charsAcross;
					float uvX = charWidth / texture.getImageWidth();
					float uvY = charHeight / texture.getImageHeight();
					HvlPainter2D.hvlDrawQuad(currentX, currentY, charWidth * uvWidth * sX * scale, charHeight * sY * scale, (fontX * uvX) + (uvX * charStartUVs.get(ch)), fontY * uvY, (fontX * uvX) + (uvX * charEndUVs.get(ch)), (fontY + 1) * uvY, texture, c);
				}
				if (HvlFontUtil.containsChar(chars, ch)) {
					currentX += charWidth * (charEndUVs.get(ch) - charStartUVs.get(ch)) * sX * scale + charSpacing * sX * scale;
				} else if (ch == ' ') {
					currentX += spaceSize * sX * scale + charSpacing * sX * scale;
				} else {
					currentX += charWidth * sX * scale + charSpacing * sX * scale;
				}
			}

			currentY += charHeight * (1.0f + rowSpacing) * sY * scale;
		}
	}

	public void drawWordc(String text, float x, float y, Color c) {
		drawWord(text, x - (getLineWidth(text) / 2), y - (getLineHeight(text) / 2), c, 1.0f);
	}

	public void drawWordc(String text, float x, float y, Color c, float s) {
		drawWord(text, x - (getLineWidth(text) / 2 * s), y - (getLineHeight(text) / 2 * s), c, s, s);
	}

	public void drawWordc(String text, float x, float y, Color c, float sX, float sY) {
		drawWord(text, x - (getLineWidth(text) / 2 * sX), y - (getLineHeight(text) / 2 * sY), c, sX, sY, 0.0f);
	}

	public void drawWordc(String text, float x, float y, Color c, float sX, float sY, float align) {
		drawWord(text, x - (getLineWidth(text) / 2 * sX), y - (getLineHeight(text) / 2 * sY), c, sX, sY, align);
	}

	public void setCharWidth(Character c, Float startUV, Float endUV) {
		charStartUVs.put(c, startUV);
		charEndUVs.put(c, endUV);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getRowSpacing() {
		return rowSpacing;
	}

	public void setRowSpacing(float rowSpacing) {
		this.rowSpacing = rowSpacing;
	}

	public float getCharSpacing() {
		return charSpacing;
	}

	public void setCharSpacing(float charSpacing) {
		this.charSpacing = charSpacing;
	}

	public float getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(float spaceSize) {
		this.spaceSize = spaceSize;
	}

	public Texture getTexture() {
		return texture;
	}

	public char[] getChars() {
		return chars;
	}

	public float getCharWidth() {
		return charWidth;
	}

	public float getCharHeight() {
		return charHeight;
	}

	public int getCharsAcross() {
		return charsAcross;
	}

	public float getLineWidth(String text) {
		float largestWidth = 0.0f;

		for (String line : text.split("\n")) {
			float width = 0f;
			for (char ch : line.toCharArray()) {
				if (charStartUVs.containsKey(ch))
					width += charWidth * (charEndUVs.get(ch) - charStartUVs.get(ch)) * scale + charSpacing * scale;
				else if (ch == ' ')
					width += spaceSize * scale + charSpacing * scale;
				else
					width += charWidth * scale + charSpacing * scale;
			}
			largestWidth = Math.max(largestWidth, width);
		}

		return largestWidth;
	}

	public float getLineHeight(String text) {
		String[] lines = text.split("\n");

		return ((lines.length * charHeight) + ((lines.length - 1) * (rowSpacing * charHeight))) * scale;
	}
}
