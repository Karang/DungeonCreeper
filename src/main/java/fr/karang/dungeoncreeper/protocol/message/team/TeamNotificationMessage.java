package fr.karang.dungeoncreeper.protocol.message.team;

import org.spout.api.protocol.Message;

public class TeamNotificationMessage implements Message {

	private final String message;
	
	public TeamNotificationMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
