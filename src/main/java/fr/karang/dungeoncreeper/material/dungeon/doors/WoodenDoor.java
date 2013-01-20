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
package fr.karang.dungeoncreeper.material.dungeon.doors;

import fr.karang.dungeoncreeper.material.dungeon.DungeonMaterial;

public class WoodenDoor extends DungeonMaterial {
	public static WoodenDoor NS;
	public static WoodenDoor WE;
	public static WoodenDoor NS_CLOSED;
	public static WoodenDoor WE_CLOSED;
	
	static {
		NS = new WoodenDoor();
		WE = new WoodenDoor("WoodenDoor_WE", 1, NS, "model://DungeonCreeper/resources/block/dungeon/door/door1.spm");
		NS_CLOSED = new WoodenDoor("WoodenDoor_NS_closed", 2, NS, "model://DungeonCreeper/resources/block/dungeon/door/door2.spm");
		WE_CLOSED = new WoodenDoor("WoodenDoor_WE_closed", 3, NS, "model://DungeonCreeper/resources/block/dungeon/door/door1.spm");
	}
	
	public WoodenDoor() {
		super((short)0x07, "WoodenDoor_NS", "model://DungeonCreeper/resources/block/dungeon/door/door2.spm");
		this.setTransparent();
	}
	
	private WoodenDoor(String name, int data, WoodenDoor parent, String model) {
		super(name, data, parent, model);
		this.setTransparent();
	}
	
	public boolean isPlacementObstacle() {
		return false;
	}
	
}
