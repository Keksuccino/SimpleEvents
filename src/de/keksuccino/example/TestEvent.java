package de.keksuccino.example;

import de.keksuccino.simpleevents.EventBase;

public class TestEvent extends EventBase {

	private String s = null;
	
	@Override
	public boolean isCancleable() {
		return false;
	}
	
	public void setString(String s) {
		this.s = s;
	}
	
	public String getString() {
		return this.s;
	}

}
