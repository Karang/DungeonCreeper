package fr.karang.dungeoncreeper.protocol.message.team;

import org.spout.api.protocol.Message;

public class TeamGoldUpdateMessage implements Message {

	private final int amount;
	
	public TeamGoldUpdateMessage(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
