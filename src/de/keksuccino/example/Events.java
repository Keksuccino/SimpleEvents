package de.keksuccino.example;

import de.keksuccino.simpleevents.EventSubscriber;

public class Events {
	
	@EventSubscriber
	public void testEvent(TestEvent e) {
		System.out.println("test");
	}
	
	@EventSubscriber
	public void testEventTwo(TestEvent e) {
		e.setString("very cool value");
		
		System.out.println("test 2");
	}
	
	public void notAnEvent(TestEvent e) {
	}
	
	public void notAnEventTwo() {
	}

}
