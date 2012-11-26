package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityTeleportMessage implements Message {
	private final int id;
	private final int X, Y, Z;
	
	public EntityTeleportMessage(int id, int X, int Y, int Z) {
		this.id = id;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}

	public int getId() {
		return id;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getZ() {
		return Z;
	}
	public int getChannelId() {
		return 0;
	}
}
