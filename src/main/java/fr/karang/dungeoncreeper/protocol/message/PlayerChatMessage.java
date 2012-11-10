package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.protocol.Message;

public class PlayerChatMessage implements Message {
	
	private String message;
	
	public PlayerChatMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
