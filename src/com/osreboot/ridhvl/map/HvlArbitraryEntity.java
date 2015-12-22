package com.osreboot.ridhvl.map;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlReflectionUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlArbitraryEntity extends HvlEntity {

	public HvlArbitraryEntity(float xArg, float yArg, HvlMap mapArg, String className, String... params) {
		super(xArg, yArg, mapArg);
		this.className = className;
		parameters = new Object[params.length + 3];
		parameters[0] = xArg;
		parameters[1] = yArg;
		parameters[2] = mapArg;
		for (int i = 0; i < params.length; i++) {
			parameters[i + 3] = params[i];
		}
	}

	private String className;
	private Object[] parameters;
	
	public HvlEntity toActualEntity() {		
		try {
			Class<?> type = Class.forName(className);
			
			for (Constructor<?> constructor : type.getConstructors()) {
				if (constructor.getParameterCount() != parameters.length) continue;
				
				boolean isValid = true;
				
				Object[] proper = new Object[constructor.getParameterCount()];
				proper[0] = getRelX();
				proper[1] = getRelY();
				proper[2] = getMap();
				
				for (int i = 3; i < constructor.getParameterCount(); i++) {
					Object supposed = HvlReflectionUtil.genericParse(constructor.getParameterTypes()[i], parameters[i].toString());
					
					proper[i] = supposed;
					if (constructor.getParameterTypes()[i].isAssignableFrom(supposed.getClass())) continue;
					
					// Handle weird primitives problem (e.g. "float" vs "class java.lang.Float", should be equal but aren't)
					if (constructor.getParameterTypes()[i].isPrimitive() && constructor.getParameterTypes()[i].getName().toLowerCase().equals(supposed.getClass().getName().toLowerCase().replaceFirst("java.lang.", ""))) continue;
					
					isValid = false;
					break;
				}
				
				if (!isValid) continue;
				
				return (HvlEntity) constructor.newInstance(proper);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(float delta) {
		int hc = className.hashCode();
		float r = (float)((hc & 0xFF0000) >> 16) / 255;
		float g = (float)((hc & 0x00FF00) >> 8 ) / 255;
		float b = (float)(hc & 0x0000FF) / 255;
		
		HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileWidth(), getMap().getTileHeight(), new Color(r, g, b, 0.75f));
	}
	
	@Override
	public List<Object> getSaveParameters() {
		List<Object> tr = new LinkedList<>();
		tr.add(className);
		for (int i = 3; i < parameters.length; i++) {
			tr.add(parameters[i]);
		}
		return tr;
	}
}
