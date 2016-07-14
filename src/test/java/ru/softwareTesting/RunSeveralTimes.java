package ru.softwareTesting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunSeveralTimes {
	//public int NumberOfRun();
	public int value(); // удобнее указывать без явного указания имени
}
