package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.HashMap;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlPanel;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyUpdate;

public class HvlMenuInteractor {
	
	public static final int UPDATE_CHRONOLOGY = HvlChronology.UPDATE_CHRONOLOGY_PRE_EARLY + 1;

	@HvlChronologyUpdate(chronology = UPDATE_CHRONOLOGY)
	public static final HvlAction1<Float> UPDATE_ACTION = new HvlAction1<Float>(){
		@Override
		public void run(Float delta){
			update(delta);
		}
	};
	
	private static boolean enabled = false, clicking = false;
	private static HvlComponent selected;
	private static HvlInput up, down, select, toggle;
	private static HashMap<HvlMenu, ArrayList<HvlComponent>> tabOrder = new HashMap<>();
	private static int x = -1, y = -1;
	
	private static void update(float delta){
		if(HvlCursor.getCursorX() != x || HvlCursor.getCursorY() != y){
			enabled = false;
			x = HvlCursor.getCursorX();
			y = HvlCursor.getCursorY();
		}
		if(enabled){
			if(!getCurrentComponents().contains(selected)){
				selected = null;
				if(getCurrentComponents().size() > 0) selected = getCurrentComponents().get(0);
			}
		}
	}
	
	public static void setFilters(HvlInput.InputFilter upArg, HvlInput.InputFilter downArg, HvlInput.InputFilter selectArg, HvlInput.InputFilter toggleArg){
		up = new HvlInput(upArg);
		up.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				ArrayList<HvlComponent> current = tabOrder.containsKey(HvlMenu.getCurrent()) ? tabOrder.get(HvlMenu.getCurrent()) : getCurrentComponents();
				if(getCurrentComponents().size() > 0) selected = current.get(Math.max(current.indexOf(selected) - 1, 0));
			}
		});
		down = new HvlInput(downArg);
		down.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				ArrayList<HvlComponent> current = tabOrder.containsKey(HvlMenu.getCurrent()) ? tabOrder.get(HvlMenu.getCurrent()) : getCurrentComponents();
				if(getCurrentComponents().size() > 0) selected = current.get(Math.min(current.indexOf(selected) + 1, current.size() - 1));
			}
		});
		select = new HvlInput(selectArg);
		select.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				clicking = true;
			}
		});
		select.setReleasedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				clicking = false;
			}
		});
		toggle = new HvlInput(toggleArg);
		toggle.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				if(!enabled){
					enabled = true;
					if(getCurrentComponents().size() > 0) selected = (tabOrder.containsKey(HvlMenu.getCurrent()) ? tabOrder.get(HvlMenu.getCurrent()) : getCurrentComponents()).get(0);
				}
			}
		});
	}
	
	private static ArrayList<HvlComponent> getCurrentComponents(){
		ArrayList<HvlComponent> current = new ArrayList<>(), temp = new ArrayList<>();
		for(HvlComponent c : HvlMenu.getCurrent().getComponents()) temp.add(c);
		boolean contains = false;
		do{
			contains = false;
			ArrayList<HvlComponent> newTemp = new ArrayList<>();
			for(HvlComponent c : temp){
				if(c.isEnabled() && (c instanceof HvlButton || c instanceof HvlCheckbox)) current.add(c);//TODO add support for other types
				if(c instanceof HvlPanel){
					newTemp.addAll(((HvlPanel)c).getChildren());
					contains = true;
				}
			}
			temp.clear();
			for(HvlComponent c : newTemp) temp.add(c);
		}while(contains);
		return current;
	}

	public static void addTabOrder(HvlMenu menuArg, ArrayList<HvlComponent> componentArgs){//TODO implement
		tabOrder.put(menuArg, componentArgs);
	}
	
	public static boolean isHovering(HvlComponent component){
		return enabled && selected == component;
	}
	
	public static boolean isBeingClicked(HvlComponent component){
		return enabled && selected == component && clicking;
	}
	
	public static boolean isEnabled(){
		return enabled;
	}

	public static HvlComponent getSelected(){
		return selected;
	}
	
}
