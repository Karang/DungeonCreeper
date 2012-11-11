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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.chat.ChatArguments;
import org.spout.api.command.Command;
import org.spout.api.exception.UnknownPacketException;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageCodec;
import org.spout.api.protocol.Protocol;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;
import fr.karang.dungeoncreeper.protocol.message.PlayerKickMessage;
import fr.karang.dungeoncreeper.protocol.message.conn.PlayerHandshakeMessage;

public class DungeonProtocol extends Protocol {

	public static final int defaultPort = 25565;
	
	public DungeonProtocol() {
		super("Dungeon", defaultPort, new DungeonCodecLookupService(), new DungeonHandlerLookupService());
	}

	@Override
	public MessageCodec<?> readHeader(ChannelBuffer buf) throws UnknownPacketException {
		int opcode = buf.readUnsignedByte();
		MessageCodec<?> codec = getCodecLookupService().find(opcode);
		if (codec == null) {
			throw new UnknownPacketException(opcode);
		}
		return codec;
	}

	@Override
	public ChannelBuffer writeHeader(MessageCodec<?> codec, ChannelBuffer data) {
		ChannelBuffer buffer = ChannelBuffers.buffer(1);
		buffer.writeByte(codec.getOpcode());
		return buffer;
	}

	@Override
	public Message getKickMessage(ChatArguments message) {
		return new PlayerKickMessage(message.toFormatString());
	}

	@Override
	public Message getCommandMessage(Command command, ChatArguments arguments) {
		if (command.getPreferredName().equals("kick")) {
			return getKickMessage(arguments);
		} else if (command.getPreferredName().equals("say")) {
			return new PlayerChatMessage(arguments.toFormatString());
		}
		return new PlayerChatMessage('/' + command.getPreferredName() + ' ' + arguments.toFormatString());
	}

	@Override
	public Message getIntroductionMessage(String playerName) {
		return new PlayerHandshakeMessage(playerName, "localhost", 25556);//TODO : Configure host & port
	}

	@Override
	public void initializeSession(Session session) {
		// TODO Auto-generated method stub
		
	}

}
