package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.protocol.Message;

public class PlayerSpawnMessage implements Message {
	private String name;
	private float x, y, z;
	
	public PlayerSpawnMessage(String name, float x, float y, float z) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String getName() {
		return name;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
}
