package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;

public class PlayerChatHandler extends MessageHandler<PlayerChatMessage> {
	@Override
	public void handleServer(Session session, PlayerChatMessage message) {
		
	}
	
	@Override
	public void handleClient(Session session, PlayerChatMessage message) {
		
	}
}
