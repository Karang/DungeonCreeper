package fr.karang.dungeoncreeper.protocol.message.team;

import org.spout.api.protocol.Message;

public class TeamJoinMessage implements Message {
	private final byte teamId;
	
	public TeamJoinMessage(byte teamId) {
		this.teamId = teamId;
	}
	
	public byte getTeamId() {
		return teamId;
	}

	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}
}
