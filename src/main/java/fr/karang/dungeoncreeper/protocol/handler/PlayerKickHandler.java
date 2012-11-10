package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerKickMessage;

public class PlayerKickHandler extends MessageHandler<PlayerKickMessage> {
	@Override
	public void handleClient(Session session, PlayerKickMessage message) {
		
	}
}
