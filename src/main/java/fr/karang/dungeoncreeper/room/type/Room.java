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
package fr.karang.dungeoncreeper.room.type;

import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.math.Rectangle;

public abstract class Room {
	public enum Rooms {
		CASINO(new Casino()),
		COMBAT_PIT(new CombatPit()),
		GRAVEYARD(new Graveyard()),
		GUARD_ROOM(new GuardRoom()),
		HATCHERY(new Hatchery()),
		LAIR(new Lair()),
		LIBRARY(new Library()),
		PRISON(new Prison()),
		TORTURE_CHAMBER(new TortureChamber()),
		TRAINING_ROOM(new TrainingRoom()),
		TREASURY(new Treasury()),
		UNHOLY_TEMPLE(new UnholyTemple()),
		WORKSHOP(new Workshop());
		
		private Room room;

		Rooms(Room room) {
			this.room = room;
		}

		public Room getRoom() {
			return room;
		}
	}

	private int buyPrice = 0;
	private int sellPrice = 0;

	public int getBuyPrice() {
		return buyPrice;
	}

	protected void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	protected void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public boolean hasRequired(Team team) {
		// TODO : Implement in each room type
		return true;
	}

	public Rectangle getUv() {
		// Fallback
		return new Rectangle(0f, 0f, 1f, 1f);
	}

	public static boolean validToBuild(World world, Rectangle rect, Team team) {
		for (int x = (int) rect.getX(); x < rect.getX() + rect.getHeight(); x++) {
			for (int z = (int) rect.getY(); z < rect.getY() + rect.getHeight(); z++) {
				Block block = world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z);
				if (!((DCMaterial) block.getMaterial()).isClaimedBy(block, team) || block.getMaterial() != DCMaterials.FLOOR) {
					return false;
				}
			}
		}
		return true;
	}
}
