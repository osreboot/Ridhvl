package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.Stack;

import com.osreboot.ridhvl.action.HvlEvent2;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;

public class HvlMenu implements HvlComponentContainer {

	private static HvlMenu current;
	private static Stack<HvlMenu> popups;
	private static Stack<Boolean> blocks;
	
	public static final HvlEvent2<HvlMenu, HvlMenu> EVENT_MENU_CHANGED = new HvlEvent2<>();
	
	private static float transitionPeriodIn = 0, transitionPeriodOut = 0, transitionCurrent = 0;
	private static HvlMenu transitionGoal = null;

	public static float getTransitionPeriodIn(){
		return transitionPeriodIn;
	}

	public static void setTransitionPeriodIn(float transitionPeriodInArg){
		transitionPeriodIn = transitionPeriodInArg;
	}

	public static float getTransitionPeriodOut(){
		return transitionPeriodOut;
	}

	public static void setTransitionPeriodOut(float transitionPeriodOutArg){
		transitionPeriodOut = transitionPeriodOutArg;
	}

	public static void setTransitionPeriod(float transitionPeriodArg){
		transitionPeriodIn = transitionPeriodArg;
		transitionPeriodOut = transitionPeriodArg;
	}

	public static float getTransitionProgress(){
		return (transitionGoal != null ? (1 - (transitionCurrent/transitionPeriodOut)) : 0) + (current != null ? 1 - (Math.min(current.getTotalTime(), transitionPeriodIn)/transitionPeriodIn) : 0);
	}
	
	public static float getTransitionProgressOut(){
		return transitionGoal != null ? (1 - (transitionCurrent/transitionPeriodOut)) : 0;
	}
	
	public static float getTransitionProgressIn(){
		return current != null ? 1 - (Math.min(current.getTotalTime(), transitionPeriodIn)/transitionPeriodIn) : 0;
	}
	
	static {
		popups = new Stack<>();
		blocks = new Stack<>();
	}

	public static HvlMenu getCurrent(){
		return current;
	}

	public static void setCurrent(HvlMenu currentArg){
		if(transitionPeriodOut == 0) metaSetCurrent(currentArg); else{
			if(transitionCurrent == 0){
				transitionCurrent = transitionPeriodOut;
				transitionGoal = currentArg;
			}
		}
	}
	
	private static final void metaSetCurrent(HvlMenu currentArg){
		EVENT_MENU_CHANGED.trigger(current, currentArg);
		current = currentArg;
		current.setTotalTime(0);
	}

	public static void updateMenus(float delta) {
		if(transitionCurrent > 0 && transitionCurrent - delta <= 0 && transitionGoal != null){
			metaSetCurrent(transitionGoal);
			transitionGoal = null;
		}
		transitionCurrent = Math.max(transitionCurrent - delta, 0);
		
		boolean blocked = false;

		// Go from the top down (highest popup to lowest).
		for (int i = popups.size() - 1; i >= 0; i--) {
			HvlMenu popup = popups.get(i);
			boolean block = blocks.get(i);

			// If nothing else has blocked updating, we can update this.
			if (!blocked) {
				popup.update(delta);
			}

			// If this blocks lower popups, then say that we are blocked.
			if (block && !blocked)
				blocked = true;
		}

		if (!blocked) {
			current.update(delta);
		}

		// Draw in reverse order: draw the main menu...
		current.draw(delta);

		// ... and then the popups from the bottom up.
		for (int i = 0; i < popups.size(); i++) {
			popups.get(i).draw(delta);
		}
	}

	private ArrayList<HvlComponent> components;
	
	private boolean interactable;
	
	private float totalTime;

	public HvlMenu() {
		components = new ArrayList<HvlComponent>();
		interactable = true;
	}

	public ArrayList<HvlComponent> getComponents() {
		return components;
	}

	@SuppressWarnings("unchecked")
	public <T> T getChild(int index) {
		if (index >= components.size())
			return null;
		
		return (T) components.get(index);
	}
	
	public HvlArrangerBox getFirstArrangerBox(){
		return getFirstOfType(HvlArrangerBox.class);
	}
	
	public void update(float delta) {
		totalTime += delta;
		for (HvlComponent c : components)
		{
			c.setContainer(this);
			c.metaUpdate(delta);
		}
	}

	public void draw(float delta) {
		for (HvlComponent c : components) {
			if (c.isVisible())
				c.metaDraw(delta);
		}
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTimeArg) {
		totalTime = totalTimeArg;
	}

	public static void addPopup(HvlMenu popup, boolean blocking) {
		popups.push(popup);
		blocks.push(blocking);
	}

	public static void removePopup() {
		if (popups.isEmpty())
			return;

		popups.pop();
		blocks.pop();
	}

	public static void removePopup(HvlMenu remove, boolean allAbove) {
		if (popups.isEmpty())
			return;

		if (!popups.contains(remove))
			return;

		int index = popups.indexOf(remove);

		if (allAbove) {
			while (popups.peek() != remove) {
				popups.pop();
				blocks.pop();
			}
			popups.pop();
			blocks.pop();
		} else {
			popups.remove(index);
			blocks.remove(index);
		}
	}

	public static boolean hasPopup() {
		return !popups.isEmpty();
	}
	
	public static boolean isBlocked(HvlMenu menu) {		
		for (int i = popups.size() - 1; i >= 0; i--) {
			// If we've found the menu (and haven't been blocked already) return false
			// (before we check for blocks)
			if (popups.get(i) == menu)
				return false;
			
			// If this one is blocking (and isn't the target: see above) return true
			if (blocks.get(i))
				return true;
		}
		
		// Either this is the base menu (and therefore unblocked if we've gotten this far)
		// or isn't actually a current menu (so return false).
		return false;
	}

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactableArg) {
		interactable = interactableArg;
	}

	@Override
	public void add(HvlComponent control) {
		components.add(control);
	}

	@Override
	public void add(HvlComponent componentArg, int indexArg) {
		components.add(indexArg, componentArg);
	}

	@Override
	public void remove(HvlComponent componentArg) {
		components.remove(componentArg);
	}

	@Override
	public void remove(int indexArg) {
		components.remove(indexArg);
	}

	@Override
	public void clear() {
		components.clear();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends HvlComponent> T get(int i) {
		return (T) components.get(i);
	}

	@Override
	public <T extends HvlComponent> T getFirstOfType(Class<? extends T> type) {
		return getChildOfType(type, 0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends HvlComponent> T getChildOfType(Class<? extends T> type, int i) {
		int current = -1;
		
		for (HvlComponent c : components) {
			if (c.getClass().isAssignableFrom(type)) {
				current++;
				if (current == i) return (T) c;
			}
		}
		
		return null;
	}

	@Override
	public int getChildCount() {
		return components.size();
	}
}
