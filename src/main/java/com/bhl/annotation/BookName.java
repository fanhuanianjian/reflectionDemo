package com.bhl.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;





@Retention(RUNTIME)
@Target(METHOD)
public @interface BookName {

	String bookName() default "";
	//String price() default "";
}

