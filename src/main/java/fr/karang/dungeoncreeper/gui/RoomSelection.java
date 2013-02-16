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
package fr.karang.dungeoncreeper.gui;

import java.util.ArrayList;
import java.util.List;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.room.type.Room;
import fr.karang.dungeoncreeper.room.type.Room.Rooms;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.RenderPartsHolderComponent;
import org.spout.api.plugin.Platform;

public class RoomSelection extends Screen {
	private Widget selectionScreen = ((Client) Spout.getEngine()).getScreenStack().createWidget();

	public RoomSelection(Team team) {
		if (Spout.getPlatform() != Platform.CLIENT) {
			throw new IllegalStateException("Only clients can have an RoomSelection screen.");
		}

		this.setTakesInput(true);

		selectionScreen.add(RenderPartsHolderComponent.class);

		List<Room> rooms = getAvaivableRoom(team);

		buildSelectionScreen(rooms);

		this.attachWidget(DungeonCreeper.getInstance(), selectionScreen);
	}

	public List<Room> getAvaivableRoom(Team team) {
		List<Room> list = new ArrayList<Room>();

		for (Rooms room : Room.Rooms.values()) {
			if (room.getRoom().hasRequired(team)) {
				list.add(room.getRoom());
			}
		}

		return list;
	}

	public void buildSelectionScreen(List<Room> rooms) {
		
	}
}
