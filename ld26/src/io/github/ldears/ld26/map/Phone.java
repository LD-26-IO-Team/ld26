package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

/**
 * @author dector
 */
public class Phone extends GameObject {

	public Phone(int x, int y, String name, ObjectType type) {
		super(x, y, name, ObjectType.PHONE);
	}

	@Override
	public Action getAction(boolean hasItem) {
		return Action.CALL_PHONE;
	}
}
