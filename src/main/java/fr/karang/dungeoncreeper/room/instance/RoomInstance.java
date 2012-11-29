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
package fr.karang.dungeoncreeper.room.instance;

import fr.karang.dungeoncreeper.room.type.Room.Rooms;

import org.spout.api.math.Rectangle;

public class RoomInstance {
	private final Rooms room;
	private final Rectangle rect;
	private final int surface;

	public RoomInstance(Rooms room, Rectangle rect) {
		this.room = room;
		this.rect = rect;
		surface = (int) (rect.getHeight() * rect.getWidth());
	}

	public Rooms getRoom() {
		return room;
	}

	public Rectangle getRect() {
		return rect;
	}

	public int getSurface() {
		return surface;
	}

	public int getSellPrice() {
		return room.getRoom().getSellPrice() * surface;
	}
}
