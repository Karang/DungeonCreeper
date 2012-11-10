package fr.karang.dungeoncreeper.protocol;

import org.spout.api.protocol.HandlerLookupService;

import fr.karang.dungeoncreeper.protocol.handler.*;
import fr.karang.dungeoncreeper.protocol.message.*;

public class DungeonHandlerLookupService extends HandlerLookupService {
	public DungeonHandlerLookupService() {
		try {
			// Lobby messages
			bind(WorldListMessage.class, WorldListHandler.class); // 0x00

			// Players messages
			bind(PlayerSpawnMessage.class, PlayerSpawnHandler.class); // 0x10
			//bind(PlayerMoveCodec.class); // 0x11
			//bind(PlayerDamageCodec.class); // 0x12
			//bind(PlayerSkillCodec.class); // 0x13
			bind(PlayerChangeClassMessage.class, PlayerChangeClassHandler.class); // 0x14
			//bind(PlayerChatCodec.class); // 0x15

			// Team messages
			//bind(TeamColorCodec.class); // 0x20
			//bind(TeamGoldCodec.class); // 0x21

			// World messages
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
