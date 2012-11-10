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
			//bind(PlayerMoveMessage.class, PlayerMoveHandler.class); // 0x11
			//bind(PlayerDamageMessage.class, PlayerDamageHandler.class); // 0x12
			bind(PlayerSkillMessage.class, PlayerSkillHandler.class); // 0x13
			bind(PlayerChangeClassMessage.class, PlayerChangeClassHandler.class); // 0x14
			bind(PlayerChatMessage.class, PlayerChatHandler.class); // 0x15
			bind(PlayerKickMessage.class, PlayerKickHandler.class); // 0x16
			
			// Team messages
			//bind(TeamColorMessage.class, TeamColorHandler.class); // 0x20
			//bind(TeamGoldMessage.class, TeamGoldHandler.class); // 0x21

			// World messages
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
