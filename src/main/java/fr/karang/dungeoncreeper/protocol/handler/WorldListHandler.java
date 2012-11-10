package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.protocol.message.WorldListMessage;

public class WorldListHandler extends MessageHandler<WorldListMessage> {
	@Override
	public void handleClient(Session session, WorldListMessage message) {
		DungeonCreeper.getInstance().getLobby().getScreen().addGame(message.getTitle());
	}
}
