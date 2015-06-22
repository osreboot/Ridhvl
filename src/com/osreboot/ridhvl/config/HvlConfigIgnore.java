package com.osreboot.ridhvl.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HvlConfigIgnore {
	public enum IgnoreType
	{
		SAVE, LOAD, BOTH
	}
	
	public IgnoreType value() default IgnoreType.BOTH;
}
