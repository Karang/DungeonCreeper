package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityYawMessage implements Message {

	private final int id;
	private final float yaw;
	
	public EntityYawMessage(int id, float yaw) {
		this.id = id;
		this.yaw = yaw;
	}
	
	public int getEntityId() {
		return id;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}
}
