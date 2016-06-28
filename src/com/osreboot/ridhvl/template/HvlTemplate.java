package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlTimer;

/**
 * This ties HvlTimer to methods commonly used in game development. 
 * Extensions of this class may include references to additional utilities
 * and may provide more in-depth shortcuts.
 * 
 * @see com.osreboot.ridhvl.HvlTimer
 *
 */
public abstract class HvlTemplate{

	public static HvlTemplate newestInstance;
	
	/**
	 * Returns the last HvlTemplate created.
	 * 
	 * @return The last HvlTemplate created.
	 */
	public static HvlTemplate getNewestInstance(){
		return newestInstance;
	}
	
	private HvlTimer timer;

	/**
	 * Constructor that updates the static value in <code>getNewestInstance()</code>.
	 */
	public HvlTemplate(){
		newestInstance = this;
	}

	/**
	 * Runs the initialization method and starts the timer.
	 */
	final void start(){
		timer = new HvlTimer(){
			@Override
			public void update(float delta) {
				tick(delta);
			}
		};
		
		initialize();
		
		timer.start();
	}
	
	/**
	 * Returns the timer used to call the update methods.
	 * 
	 * @return The timer.
	 */
	public HvlTimer getTimer(){
		return timer;
	}
	
	/**
	 * Called before the timer is started, more commonly used to initialize variables and load resources.
	 */
	public abstract void initialize();

	/**
	 * Runs <code>preUpdate(float delta)</code>, <code>update(float delta)</code> and <code>postUpdate(float delta)</code> in that order.
	 * 
	 * @param delta The time (in seconds) since the last update.
	 */
	public final void tick(float delta){
		preUpdate(delta);
		update(delta);
		postUpdate(delta);
	}

	/**
	 * Called before <code>update(float delta)</code>.
	 * 
	 * @param delta The time (in seconds) since the last update.
	 */
	public void preUpdate(float delta){}
	
	/**
	 * Called after <code>preUpdate(float delta)</code> and before <code>postUpdate(float delta)</code>.
	 * 
	 * @param delta The time (in seconds) since the last update.
	 */
	public abstract void update(float delta);
	
	/**
	 * Called after <code>update(float delta)</code>.
	 * 
	 * @param delta The time (in seconds) since the last update.
	 */
	public void postUpdate(float delta){}

}
