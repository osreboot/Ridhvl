package com.osreboot.ridhvl.display;


public class HvlDisplay {
	
	private static HvlDisplayMode displayMode;
	
	public static void setDisplayMode(HvlDisplayMode displayModeArg){
		displayMode = displayModeArg;
		displayMode.configureDisplay();
	}
	
	public static HvlDisplayMode getDisplayMode(){
		return displayMode;
	}
	
	public static void update(){
		displayMode.update();
	}
	
	@Deprecated
	public static float getTransformedX(float xArg){
		return displayMode.getTransformedX(xArg);
		/*
		case ARLOCK: return xArg*Display.getWidth()*aspectRatio;//TODO work with blank space
		case SCALABLE: return xArg*Display.getWidth()*aspectRatio;
		}*/
	}
	
	@Deprecated
	public static float getTransformedY(float yArg){
		return displayMode.getTransformedY(yArg);
		/*
		case ARLOCK: return yArg*Display.getHeight()*(1/aspectRatio);//TODO work with blank space
		case SCALABLE: return yArg*Display.getHeight()*(1/aspectRatio);
		}*/
	}
	
	@Deprecated
	public static float getScaledX(float xArg){
		return displayMode.getScaledX(xArg);
		/*
		case ARLOCK: return xArg*Display.getWidth()*aspectRatio;
		case SCALABLE: return xArg*Display.getWidth()*aspectRatio;
		}*/
	}
	
	@Deprecated
	public static float getScaledY(float yArg){
		return displayMode.getScaledY(yArg);
		/*
		case ARLOCK: return yArg*Display.getHeight()*(1/aspectRatio);
		case SCALABLE: return yArg*Display.getHeight()*(1/aspectRatio);
		}*/
	}
	
	@Deprecated
	public static float getUnscaledX(float xArg){
		return displayMode.getUnscaledX(xArg);
		/*
		case ARLOCK: return xArg/Display.getWidth()/aspectRatio;
		case SCALABLE: return xArg/Display.getWidth()/aspectRatio;
		}*/
	}
	
	@Deprecated
	public static float getUnscaledY(float yArg){
		return displayMode.getUnscaledY(yArg);
		/*
		case ARLOCK: return yArg/Display.getHeight()/(1/aspectRatio);
		case SCALABLE: return yArg/Display.getHeight()/(1/aspectRatio);
		}*/
	}
	
}
