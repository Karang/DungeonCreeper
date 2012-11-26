package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityMoveMessage implements Message {

	private final int id;
	private final int deltaX, deltaY, deltaZ;
	
	public EntityMoveMessage(int id, int deltaX, int deltaY, int deltaZ) {
		this.id = id;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
	}

	public int getId() {
		return id;
	}
	
	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public int getDeltaZ() {
		return deltaZ;
	}
	
	public int getChannelId() {
		return 0;
	}
}
