package com.osreboot.ridhvl.menu;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.menu.component.HvlButton;

public class HvlButtonMenuLink extends HvlAction1<HvlButton> {

	private HvlMenu link;
	
	public HvlButtonMenuLink(HvlMenu linkArg) {
		this.link = linkArg;
	}

	@Override
	public void run(HvlButton callingButton) {
		HvlMenu.setCurrent(link);
	}

}
