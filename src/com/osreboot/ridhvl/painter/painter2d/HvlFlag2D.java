package com.osreboot.ridhvl.painter.painter2d;

public abstract class HvlFlag2D {
	
	private boolean enabled;
	
	public HvlFlag2D(boolean enabledArg){
		enabled = enabledArg;
	}
	
	public abstract void enable();
	public abstract void disable();
	
	public void setEnabled(boolean enabledArg){
		if(enabled && !enabledArg) disable(); else if(!enabled && enabledArg) enable();
		enabled = enabledArg;
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
}
