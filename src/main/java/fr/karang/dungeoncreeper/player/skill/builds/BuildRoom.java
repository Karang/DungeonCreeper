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
package fr.karang.dungeoncreeper.player.skill.builds;

import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.player.DungeonPlayer;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.room.instance.RoomInstance;
import fr.karang.dungeoncreeper.room.type.Room;
import fr.karang.dungeoncreeper.room.type.Room.Rooms;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.math.Rectangle;

public class BuildRoom extends BuildSkill {
	public BuildRoom(int id) {
		super(id, "build");
	}

	@Override
	public void handle(Entity source) {
		Rooms room =  source.get(Imp.class).getRoomClaim();
		Team team = source.get(DungeonPlayer.class).getTeam();
		Rectangle rect = source.get(Imp.class).getBuildRect();

		if (rect == null) {
			if (source instanceof Player) {
				((Player) source).sendMessage("Sélection incomplète !");
			}
			return;
		}

		int price = room.getRoom().getBuyPrice() * (int) (rect.getWidth() * rect.getHeight());

		if (team.getGold() < price) {
			if (source instanceof Player) {
				((Player) source).sendMessage("Votre équipe ne possède pas assez d'or");
			}
			return;
		}

		//TODO : Vérifier que la sélection est libre (pas de mur, pas de pièce)
		if (!Room.validToBuild(source.getWorld(), rect, team)) {
			if (source instanceof Player) {
				((Player) source).sendMessage("La sélection contient des zones non-capturées");
			}
			return;
		}

		team.addRoom(new RoomInstance(room, rect));
		team.setGold(team.getGold() - price);
		//TODO : Construire la pièce
	}

	@Override
	public Rectangle getUv() {
		return new Rectangle(96f / 256f, 0, 32f / 256f, 32f / 256f);
	}
}
