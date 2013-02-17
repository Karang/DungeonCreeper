package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityDieMessage implements Message {

	private final int id;
	
	public EntityDieMessage(int id) {
		this.id = id;
	}
	
	public int getEntityId() {
		return id;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}
}
