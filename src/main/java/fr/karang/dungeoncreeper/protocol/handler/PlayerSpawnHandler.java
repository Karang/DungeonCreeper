package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerSpawnMessage;

public class PlayerSpawnHandler extends MessageHandler<PlayerSpawnMessage> {

	@Override
	public void handleServer(Session session, PlayerSpawnMessage message) {
		
	}
	
	@Override
	public void handleClient(Session session, PlayerSpawnMessage message) {
		
	}
}
