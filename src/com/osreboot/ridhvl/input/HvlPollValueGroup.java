package com.osreboot.ridhvl.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface HvlPollValueGroup {
	public abstract String[] value();
	public float max() default 1.0f;
	public float min() default -1.0f;
}
