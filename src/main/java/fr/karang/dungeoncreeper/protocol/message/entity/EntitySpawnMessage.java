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
package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntitySpawnMessage implements Message {
	private final int id;
	private final String name;
	private final float x, y, z;
	private final byte type;
	private final byte team;

	public EntitySpawnMessage(int id, String name, byte type, byte team, float x, float y, float z) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.team = team;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getEntityId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public byte getType() {
		return type;
	}
	
	public byte getTeamId() {
		return team;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public int getChannelId() {
		return 0;
	}
	
	public boolean isAsync() {
		return false;
	}
}