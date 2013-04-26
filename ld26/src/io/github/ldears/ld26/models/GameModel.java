package io.github.ldears.ld26.models;

import io.github.ldears.ld26.events.InputEventHandler;
import io.github.ldears.ld26.events.ReactionEventHandler;

import static io.github.ldears.ld26.events.ReactionEventHandler.ReactionEvent;

/**
 * @author dector
 */
public class GameModel implements InputEventHandler {

	// Work here with model

	/**
	 * Call reactionHandler.handleEvent(ReactionEvent.XXX)
	 * to process some gameplay reaction at screen
	 */
	private ReactionEventHandler reactionHandler;

	public GameModel(ReactionEventHandler reactionHandler) {
		this.reactionHandler = reactionHandler;
	}

	@Override
	public void handleEvent(InputEvent event) {

		// TODO MOCKUP
		System.out.println("GameModel: " + event + " received");
		reactionHandler.handleEvent(ReactionEvent.TEST);
	}
}
