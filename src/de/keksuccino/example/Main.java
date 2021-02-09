package de.keksuccino.example;

import de.keksuccino.simpleevents.EventHandler;

public class Main {

	public static EventHandler handler = new EventHandler();
	
	public static void main(String[] args) {
		
		//##### NOTES #####
		
		// - All events need to be a subclass of EventBase (take a look at TestEvent.java for an example)
		// - Event methods have to be annotated with @EventSubscriber and needs to have the event type 
		//   specified as parameter (Events.java has some examples) 
		
		
		
		
		//This will register all public (static and non-static) event methods
		//of an instance OR all public static event methods of a class.
		handler.registerEventsFrom(new Events());
		
		
		//This is an event trigger (new event instance + callEventsFor()).
		//It will execute all registered events for this event type.
		TestEvent e = new TestEvent();
		handler.callEventsFor(e);
		
		
		//EventBase has an event cancellation handling.
		if (e.isCanceled()) {
			return;
		}
		
		
		//String value is changed in Events.testEventTwo()
		System.out.println(e.getString());
		
	}

}
