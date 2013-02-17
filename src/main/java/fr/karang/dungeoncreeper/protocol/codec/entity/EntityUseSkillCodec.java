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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityUseSkillMessage;

public class EntityUseSkillCodec extends MessageCodec<EntityUseSkillMessage> {

	public EntityUseSkillCodec() {
		super(EntityUseSkillMessage.class, 0x14);
	}

	@Override
	public EntityUseSkillMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readInt();
		byte skill = buffer.readByte();
		return new EntityUseSkillMessage(id, skill);
	}

	@Override
	public ChannelBuffer encode(EntityUseSkillMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getEntityId());
		buffer.writeByte(message.getSkillId());
		return buffer;
	}

}
