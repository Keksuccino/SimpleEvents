//Copyright (c) 2020 Keksuccino
package de.keksuccino.simpleevents;

/**
 * The base class for all events.
 */
public abstract class EventBase {
	
	private boolean canceled = false;

	public abstract boolean isCancleable();
	
	public void setCanceled() throws EventCancellationException {
		if (!this.isCancleable()) {
			throw new EventCancellationException("Event not cancleable: " + this.getClass().getName());
		}
		this.canceled = true;
	}
	
	public boolean isCanceled() {
		return this.canceled;
	}

}
