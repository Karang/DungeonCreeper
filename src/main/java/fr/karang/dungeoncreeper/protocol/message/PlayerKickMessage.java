package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.protocol.Message;

public class PlayerKickMessage implements Message {
	
	private String reason;
	
	public PlayerKickMessage(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
}
