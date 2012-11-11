package fr.karang.dungeoncreeper.event;

import org.spout.api.entity.Entity;
import org.spout.api.event.Cause;

public class BlockCause implements Cause<Entity> {

	private final Entity source;
	
	public BlockCause(Entity source){
		this.source = source;
	}
	
	public Entity getSource() {
		return source;
	}

}
