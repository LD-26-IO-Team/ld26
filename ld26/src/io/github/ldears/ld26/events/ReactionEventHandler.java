package io.github.ldears.ld26.events;

/**
 * @author dector
 */
public interface ReactionEventHandler {

	public void handleEvent(ReactionEvent event);

	public static enum ReactionEvent {
		TEST
	}
}
