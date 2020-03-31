//Copyright (c) 2020 Keksuccino
package de.keksuccino.simpleevents;

import java.lang.reflect.Method;

public class EventContainer {
	
	public final Method event;
	public final Object instance;
	public final String identifier;

	public EventContainer(String identifier, Method event, Object instance) {
		this.event = event;
		this.instance = instance;
		this.identifier = identifier;
	}
}
