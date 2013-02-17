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
package fr.karang.dungeoncreeper.protocol.codec.world;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.world.BlockPlaceMessage;


public class BlockPlaceCodec  extends MessageCodec<BlockPlaceMessage>{

	public BlockPlaceCodec() {
		super(BlockPlaceMessage.class, 0x32);
	}
	
	@Override
	public BlockPlaceMessage decode(ChannelBuffer buffer) throws IOException {
		int x = buffer.readInt();
		int z = buffer.readInt();
		byte type = buffer.readByte();
		byte data = buffer.readByte();
		boolean isGround = buffer.readByte() == 0;
		return new BlockPlaceMessage(x, z, type, data, isGround);
	}

	@Override
	public ChannelBuffer encode(BlockPlaceMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getX());
		buffer.writeInt(message.getZ());
		buffer.writeByte(message.getType());
		buffer.writeByte(message.getData());
		buffer.writeByte(message.isGround()?0:1);
		return buffer;
	}

}
