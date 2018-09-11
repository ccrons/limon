package com.limon.http.xml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface XmlNode {
	public String name() default "CM";
	public String cname() default "";
}
