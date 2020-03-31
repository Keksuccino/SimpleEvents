//Copyright (c) 2020 Keksuccino
package de.keksuccino.simpleevents;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
	
	private Map<String, List<EventContainer>> events = new HashMap<String, List<EventContainer>>();
	
	/**
	 * This will register all all public (static and non-static) methods of the given object instance annotated with {@link EventSubscriber}.
	 */
	public void registerEventsFrom(Object instance) {
		for (Method m : instance.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(m.getModifiers()) && m.isAnnotationPresent(EventSubscriber.class)) {
				for (Class<?> cc : m.getParameterTypes()) {
					if (EventBase.class.isAssignableFrom(cc)) {
						if (!eventsExistForType((Class<? extends EventBase>) cc)) {
							events.put(cc.getName(), new ArrayList<EventContainer>());
						}
						events.get(cc.getName()).add(new EventContainer(cc.getName(), m, instance));
						System.out.println("Registered event '" + m.getName() + "' (" + cc.getTypeName() + ") from '" + instance.getClass().getName() + "'!");
						break;
					}
				}
			}
		}
	}
	
	/**
	 * This will register all all public static methods of the given class annotated with {@link EventSubscriber}.
	 */
	public void registerEventsFrom(Class<?> c) {
		for (Method m : c.getDeclaredMethods()) {
			if (Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()) && m.isAnnotationPresent(EventSubscriber.class)) {
				for (Class<?> cc : m.getParameterTypes()) {
					if (EventBase.class.isAssignableFrom(cc)) {
						if (!eventsExistForType((Class<? extends EventBase>) cc)) {
							events.put(cc.getName(), new ArrayList<EventContainer>());
						}
						events.get(cc.getName()).add(new EventContainer(cc.getName(), m, c));
						System.out.println("Registered static event '" + m.getName() + "' (" + cc.getTypeName() + ") from '" + c.getName() + "'!");
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Needs to be called when all events for a specific event type needs to be executed.<br><br>
	 * 
	 * <b>Usage:</b><br><br>
	 * 
	 * {@code ExampleEvent e = new ExampleEvent();}<br>
	 * {@code EventHandler.callEventsFor(e);}
	 */
	public void callEventsFor(EventBase listener) {
		if (eventsExistForType(listener.getClass())) {
			for (EventContainer c : events.get(listener.getClass().getName())) {
				try {
					c.event.invoke(c.instance, listener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean eventsExistForType(Class<? extends EventBase> listenerType) {
		return (events.get(listenerType.getName()) != null);
	}

}
