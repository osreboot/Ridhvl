package com.osreboot.ridhvl.menu;

import com.osreboot.ridhvl.menu.component.HvlButton;

public class HvlButtonMenuLink extends HvlButton.OnClickedCommand {

	private HvlMenu link;
	
	public HvlButtonMenuLink(HvlMenu linkArg) {
		this.link = linkArg;
	}

	@Override
	public void run(HvlButton callingButton) {
		HvlMenu.setCurrent(link);
	}

}
