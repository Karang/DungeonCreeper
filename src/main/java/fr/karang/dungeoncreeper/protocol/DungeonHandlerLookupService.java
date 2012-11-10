/*
 * This file is part of DungeonCreeper.
 *
 * Copyright (c) 2012-2012, ${project.organization.name} <${url}/>
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
