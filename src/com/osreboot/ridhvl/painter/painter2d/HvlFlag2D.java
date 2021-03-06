package com.osreboot.ridhvl.painter.painter2d;

import com.osreboot.ridhvl.action.HvlAction0;

public abstract class HvlFlag2D {
	
	private boolean enabled;
	
	public HvlFlag2D(boolean enabledArg){
		enabled = enabledArg;
	}
	
	@Deprecated
	/**
	 * This is an event, this does not change the flag's state. Please use setEnabled(boolean enabledArg);
	 */
	public abstract void onEnable();
	
	@Deprecated
	/**
	 * This is an event, this does not change the flag's state. Please use setEnabled(boolean enabledArg);
	 */
	public abstract void onDisable();
	
	public void setEnabled(boolean enabledArg){
		if(enabled && !enabledArg) onDisable(); else if(!enabled && enabledArg) onEnable();
		enabled = enabledArg;
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void doInverted(HvlAction0 actionArg){
		boolean wasEnabled = isEnabled();
		setEnabled(!wasEnabled);
		actionArg.run();
		setEnabled(wasEnabled);
	}
	
}
