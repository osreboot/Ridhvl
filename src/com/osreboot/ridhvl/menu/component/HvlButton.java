package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;


public abstract class HvlButton extends HvlComponent{

	private boolean previousHover, currentHover, previousClick, currentClick;
	
	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg){
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
	}

	public void onPressing(int buttonArg){}
	public void onTriggered(){}
	public void draw(float delta){}

	public final void update(float delta){
		previousHover = currentHover;
		previousClick = currentClick;
		
		currentHover = isHovering();
		currentClick = isBeingPressed(0);
		
		// Allows "cancelling" by dragging off of the button
		if (previousHover && !currentHover)
		{
			previousClick = false;
			currentClick = false;
		}
		
		if (previousClick && !currentClick)
		{
			onTriggered();
		}
		else if (currentClick)
		{
			onPressing(0);
		}
		
//		draw(delta);
//		if(isBeingPressed(0)){
//			onPressing(0);
//			if(!triggered){
//				onTriggered();
//				triggered = true;
//			}
//		}else{
//			if(isBeingPressed(1)){
//				onPressing(1);
//			}
//			//triggered = false;
//		}
	}
}
