package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityVelocityMessage implements Message {
	private final int id;
	private final int velocityX, velocityY, velocityZ;
	
	public EntityVelocityMessage(int id, int velocityX, int velocityY, int velocityZ) {
		this.id = id;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
	}

	public int getId() {
		return id;
	}
	
	public int getVelocityX() {
		return velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public int getVelocityZ() {
		return velocityZ;
	}
	
	public int getChannelId() {
		return 0;
	}
}
