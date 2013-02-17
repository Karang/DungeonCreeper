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
package fr.karang.dungeoncreeper.protocol.handler.lobby;

import fr.karang.dungeoncreeper.protocol.DungeonProtocol;
import fr.karang.dungeoncreeper.protocol.message.entity.EntitySpawnMessage;
import fr.karang.dungeoncreeper.protocol.message.lobby.PlayerHandshakeMessage;

import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.event.player.PlayerConnectEvent;
import org.spout.api.geo.discrete.Point;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

public class PlayerHandshakeHandler extends MessageHandler<PlayerHandshakeMessage> {
	@Override
	public void handleServer(Session session, PlayerHandshakeMessage message) {
		System.out.println("Player handshake: " + message.getUsername());

		if (message.getProtocolVersion() != DungeonProtocol.PROTOCOL_VERSION) {
			session.disconnect(false, new Object[]{"Outdated version."});
		}

		if (session.getState() == Session.State.EXCHANGE_HANDSHAKE) {
			Player player = Spout.getEngine().getPlayer(message.getUsername(), true);

			if (player != null) {
				session.disconnect(false, new Object[]{"Pseudo already used."});
			}

			System.out.println("Player connected: "+message.getUsername());
			Spout.getEngine().getEventManager().callEvent(new PlayerConnectEvent(session, message.getUsername()));
		
			EntitySpawnMessage spawn = new EntitySpawnMessage(session.getPlayer().getId(), message.getUsername(), (byte)0, (byte)1, 0, 0, 0);
			for (Player p : session.getPlayer().getWorld().getPlayers()) {
				Session s = p.getSession();
				if (s!=null) {
					s.send(false, true, spawn);
				}
				Point pos = p.getScene().getPosition();
				session.send(false, true, new EntitySpawnMessage(p.getId(), p.getName(), (byte)0, (byte)1, pos.getX(), pos.getY(), pos.getZ()));
			}
		} else {
			session.disconnect(false, new Object[]{"Handshake already exchanged."});
		}
	}
}
