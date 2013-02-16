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
package fr.karang.dungeoncreeper.protocol.handler;

import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.entity.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

public class PlayerChatHandler extends MessageHandler<PlayerChatMessage> {
	@Override
	public void handleServer(Session session, PlayerChatMessage message) {
		if (!session.hasPlayer()) {
			return;
		}

		Player player = session.getPlayer();
		String text = message.getMessage();
		text = text.trim();

		if (text.length() > 100) {
			//session.disconnect("Chat message is too long."); TODO Don't disconnect people...
			text = text.substring(0, 99);
		}
		String command;
		ChatArguments args;
		if (text.startsWith("/")) {
			int spaceIndex = text.indexOf(" ");
			if (spaceIndex != -1) {
				command = text.substring(1, spaceIndex);
				text = text.substring(spaceIndex + 1);
			} else {
				command = text.substring(1);
				text = "";
			}
		} else {
			command = "say";
		}

		args = ChatArguments.fromString(text);
		player.processCommand(command, args);
	}

	@Override
	public void handleClient(Session session, PlayerChatMessage message) {
		if (!session.hasPlayer()) {
			return;
		}
		
		ChatArguments text = ChatArguments.fromString(message.getMessage());
		((Client)Spout.getEngine()).getScreenStack().getConsole().addMessage(text);
	}
}
