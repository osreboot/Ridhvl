package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;


public abstract class HvlCheckbox extends HvlComponent {

	private boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	
	public void onChanged(boolean state){}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
	}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		checked = checkedArg;
	}
	
	public final void update(float delta) {
		previousHover = currentHover;
		previousPressed = currentPressed;
		currentHover = isHovering();
		currentPressed = isBeingPressed(0);
		
		// This allows you to drag off of the checkbox without triggering it.
		if (previousHover && !currentHover)
		{
			previousPressed = false;
			currentPressed = false;
		}
		
		if (previousPressed && !currentPressed)
		{
			checked = !checked;
			onChanged(checked);
		}
		
		draw(delta);
	}
	
	public boolean getChecked(){
		return checked;
	}
	
	public void setChecked(boolean checkedArg){
		checked = checkedArg;
	}
}
