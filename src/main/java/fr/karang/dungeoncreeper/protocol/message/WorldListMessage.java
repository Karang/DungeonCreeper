package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.protocol.Message;

public class WorldListMessage implements Message {
	private String name;
	private int onlinePlayers;
	private int maxPlayers;
	
	public WorldListMessage(String name, int onlinePlayers, int maxPlayers) {
		this.name = name;
		this.onlinePlayers = onlinePlayers;
		this.maxPlayers = maxPlayers;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public ChatArguments getTitle() {
		return new ChatArguments(ChatStyle.BLUE, name, onlinePlayers, " / ", maxPlayers);
	}
}
