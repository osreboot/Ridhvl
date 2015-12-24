package com.osreboot.ridhvl.configold;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Use this to mark a property that you don't want to be used by any of <code>HvlConfigUtil</code>'s methods.
 * @see HvlConfigUtil
 */
public @interface HvlConfigIgnore {
	/**
	 * Use this to specify what the ignore prevents.
	 * <code>SAVE</code> means that the property will not be saved.
	 * <code>LOAD</code> means that the property will not be loaded.
	 * <code>BOTH</code> means that the property will not be saved or loaded.
	 */
	public enum IgnoreType
	{
		SAVE, LOAD, BOTH
	}
	
	/**
	 * What actions this ignore prevents (defaults to <code>IgnoreType.BOTH</code>).
	 * @return What actions this ignore prevents.
	 */
	public IgnoreType value() default IgnoreType.BOTH;
}
