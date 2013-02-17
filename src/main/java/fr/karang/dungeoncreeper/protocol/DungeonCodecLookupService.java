/*
 * This file is part of DungeonCreeper.
 *
 * Copyright (c) 2012-2012, Karang <http://arthur.hennequin.free.fr/>
 * DungeonCreeper is licensed under the SpoutDev License Version 1.
 *
 * DungeonCreeper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * DungeonCreeper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package fr.karang.dungeoncreeper.protocol;

import fr.karang.dungeoncreeper.protocol.codec.PlayerChatCodec;
import fr.karang.dungeoncreeper.protocol.codec.PlayerKickCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntityDieCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntityMoveCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntitySkillBlockCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntitySkillCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntitySpawnCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntityTeleportCodec;
import fr.karang.dungeoncreeper.protocol.codec.entity.EntitySkillEntityCodec;
import fr.karang.dungeoncreeper.protocol.codec.game.TeamGoldUpdateCodec;
import fr.karang.dungeoncreeper.protocol.codec.game.TeamJoinCodec;
import fr.karang.dungeoncreeper.protocol.codec.game.TeamNotificationCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.PlayerHandshakeCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.PlayerListCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.WorldListCodec;
import fr.karang.dungeoncreeper.protocol.codec.world.ChunkDataCodec;

import org.spout.api.protocol.CodecLookupService;

public class DungeonCodecLookupService extends CodecLookupService {
	public DungeonCodecLookupService() {
		super(512);
		
		try {
			// Lobby messages
			bind(WorldListCodec.class); // 0x00
			bind(PlayerListCodec.class); // 0x01
			bind(PlayerHandshakeCodec.class); // 0x02

			//Entity messages
			bind(EntitySpawnCodec.class); // 0x10
			bind(EntityMoveCodec.class); //0x11
			bind(EntityTeleportCodec.class); //0x12
			bind(EntitySkillCodec.class); //0x13
			bind(EntitySkillBlockCodec.class); //0x14
			bind(EntitySkillEntityCodec.class); //0x15
			bind(EntityDieCodec.class); //0x16
			
			// Team messages
			//bind(TeamColorCodec.class); // 0x20
			bind(TeamGoldUpdateCodec.class); // 0x21
			bind(TeamJoinCodec.class); // 0x22
			bind(TeamNotificationCodec.class); // 0x23

			// World messages
			bind(ChunkDataCodec.class); // 0x30
			//bind(BlockBreakCodec.class); // 0x31 //removed
			//bind(BlockPlaceCodec.class); // 0x32 //removed
			
			// Players messages
			bind(PlayerChatCodec.class); // 0x41
			bind(PlayerKickCodec.class); // 0x42

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
