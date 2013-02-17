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
package fr.karang.dungeoncreeper.protocol.codec.entity;

import java.io.IOException;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.entity.EntitySpawnMessage;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import org.spout.api.protocol.MessageCodec;

public class EntitySpawnCodec extends MessageCodec<EntitySpawnMessage> {
	public EntitySpawnCodec() {
		super(EntitySpawnMessage.class, 0x10);
	}

	@Override
	public EntitySpawnMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readInt();
		String name = ChannelBufferUtils.readString(buffer);
		byte type = buffer.readByte();
		byte team = buffer.readByte();
		float x = buffer.readFloat();
		float y = buffer.readFloat();
		float z = buffer.readFloat();
		return new EntitySpawnMessage(id, name, type, team, x, y, z);
	}

	@Override
	public ChannelBuffer encode(EntitySpawnMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getEntityId());
		ChannelBufferUtils.writeString(buffer, message.getName());
		buffer.writeByte(message.getType());
		buffer.writeByte(message.getTeamId());
		buffer.writeFloat(message.getX());
		buffer.writeFloat(message.getY());
		buffer.writeFloat(message.getZ());
		return buffer;
	}
}
