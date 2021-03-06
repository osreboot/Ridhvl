package com.osreboot.ridhvl.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface HvlPollValue {
	public String component();
	public String custom() default ""; //TODO magic numbers
	public float max() default 1.0f;
	public float min() default -1.0f;
	public float amplifier() default 1.0f;
	public float deadZone() default 0.0f;
}
