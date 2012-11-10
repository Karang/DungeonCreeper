package fr.karang.dungeoncreeper.protocol;

import org.spout.api.protocol.CodecLookupService;

import fr.karang.dungeoncreeper.protocol.codec.*;

public class DungeonCodecLookupService extends CodecLookupService {

	public DungeonCodecLookupService() {
		try {
			// Lobby messages
			bind(WorldListCodec.class); // 0x00
			
			// Players messages
			bind(PlayerSpawnCodec.class); // 0x10
			//bind(PlayerMoveCodec.class); // 0x11
			//bind(PlayerDamageCodec.class); // 0x12
			//bind(PlayerSkillCodec.class); // 0x13
			bind(PlayerChangeClassCodec.class); // 0x14
			bind(PlayerChatCodec.class); // 0x15
			bind(PlayerKickCodec.class); // 0x16
			
			// Team messages
			//bind(TeamColorCodec.class); // 0x20
			//bind(TeamGoldCodec.class); // 0x21
			
			// World messages
			
		} catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
