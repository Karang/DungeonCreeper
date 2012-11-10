package fr.karang.dungeoncreeper.protocol.handler;

import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerSkillMessage;

public class PlayerSkillHandler extends MessageHandler<PlayerSkillMessage> {
	@Override
	public void handleServer(Session session, PlayerSkillMessage message) {
		
	}
	
	@Override
	public void handleClient(Session session, PlayerSkillMessage message) {
		
	}
}
