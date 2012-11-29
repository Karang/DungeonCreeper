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

import fr.karang.dungeoncreeper.protocol.codec.PlayerChangeClassCodec;
import fr.karang.dungeoncreeper.protocol.codec.PlayerChatCodec;
import fr.karang.dungeoncreeper.protocol.codec.PlayerKickCodec;
import fr.karang.dungeoncreeper.protocol.codec.PlayerSkillCodec;
import fr.karang.dungeoncreeper.protocol.codec.PlayerSpawnCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.PlayerHandshakeCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.PlayerListCodec;
import fr.karang.dungeoncreeper.protocol.codec.lobby.WorldListCodec;

import org.spout.api.protocol.CodecLookupService;

public class DungeonCodecLookupService extends CodecLookupService {
	public DungeonCodecLookupService() {
		try {
			// Lobby messages
			bind(WorldListCodec.class); // 0x00
			bind(PlayerListCodec.class); // 0x01
			bind(PlayerHandshakeCodec.class); // 0x02

			// Players messages
			bind(PlayerSpawnCodec.class); // 0x10
			//bind(PlayerDamageCodec.class); // 0x12
			bind(PlayerSkillCodec.class); // 0x13
			bind(PlayerChangeClassCodec.class); // 0x14
			bind(PlayerChatCodec.class); // 0x15
			bind(PlayerKickCodec.class); // 0x16

			// Team messages
			//bind(TeamColorCodec.class); // 0x20
			//bind(TeamGoldCodec.class); // 0x21

			// EntityMessages

			// World messages

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
