package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerChangeClassMessage;

public class PlayerChangeClassHandler extends MessageHandler<PlayerChangeClassMessage> {
	@Override
	public void handleServer(Session session, PlayerChangeClassMessage message) {
		
	}
	
	@Override
	public void handleClient(Session session, PlayerChangeClassMessage message) {
		
	}
}
