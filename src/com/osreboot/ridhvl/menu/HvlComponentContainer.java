package com.osreboot.ridhvl.menu;

public interface HvlComponentContainer {
	void add(HvlComponent componentArg);
	
	void add(HvlComponent componentArt, int indexArg);
	
	void remove(HvlComponent componentArg);
	
	void remove(int indexArg);
	
	void clear();
	
	<T extends HvlComponent> T get(int i);
	
	<T extends HvlComponent> T getFirstOfType(Class<? extends T> type);
	
	int getChildCount();
}
