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

import fr.karang.dungeoncreeper.protocol.handler.PlayerChangeClassHandler;
import fr.karang.dungeoncreeper.protocol.handler.PlayerChatHandler;
import fr.karang.dungeoncreeper.protocol.handler.PlayerKickHandler;
import fr.karang.dungeoncreeper.protocol.handler.PlayerSpawnHandler;
import fr.karang.dungeoncreeper.protocol.handler.lobby.PlayerHandshakeHandler;
import fr.karang.dungeoncreeper.protocol.handler.lobby.PlayerListHandler;
import fr.karang.dungeoncreeper.protocol.handler.lobby.WorldListHandler;
import fr.karang.dungeoncreeper.protocol.handler.world.ChunkDataHandler;
import fr.karang.dungeoncreeper.protocol.message.PlayerChangeClassMessage;
import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;
import fr.karang.dungeoncreeper.protocol.message.PlayerKickMessage;
import fr.karang.dungeoncreeper.protocol.message.entity.EntitySpawnMessage;
import fr.karang.dungeoncreeper.protocol.message.lobby.PlayerHandshakeMessage;
import fr.karang.dungeoncreeper.protocol.message.lobby.PlayerListMessage;
import fr.karang.dungeoncreeper.protocol.message.lobby.WorldListMessage;
import fr.karang.dungeoncreeper.protocol.message.world.ChunkDataMessage;

import org.spout.api.protocol.HandlerLookupService;

public class DungeonHandlerLookupService extends HandlerLookupService {
	public DungeonHandlerLookupService() {
		try {
			// Lobby messages
			bind(WorldListMessage.class, WorldListHandler.class); // 0x00
			bind(PlayerListMessage.class, PlayerListHandler.class); //0x01
			bind(PlayerHandshakeMessage.class, PlayerHandshakeHandler.class); // 0x02

			// Players messages
			bind(EntitySpawnMessage.class, PlayerSpawnHandler.class); // 0x10
			//bind(PlayerMoveMessage.class, PlayerMoveHandler.class); // 0x11
			bind(PlayerChangeClassMessage.class, PlayerChangeClassHandler.class); // 0x14
			bind(PlayerChatMessage.class, PlayerChatHandler.class); // 0x15
			bind(PlayerKickMessage.class, PlayerKickHandler.class); // 0x16

			// Team messages
			//bind(TeamColorMessage.class, TeamColorHandler.class); // 0x20
			//bind(TeamGoldMessage.class, TeamGoldHandler.class); // 0x21

			// World messages
			bind(ChunkDataMessage.class, ChunkDataHandler.class); //0x30
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
