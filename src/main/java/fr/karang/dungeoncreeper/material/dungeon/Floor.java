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
package fr.karang.dungeoncreeper.material.dungeon;

import org.spout.api.material.Material;

public class Floor extends DungeonMaterial {

	public static Floor FLOOR_NEUTRAL = new Floor("Neutral Bridge","model://DungeonCreeper/resources/block/dungeon/floor/floor.spm");
	public static Floor FLOOR_RED = new Floor("Red Bridge", 1,FLOOR_NEUTRAL, "model://DungeonCreeper/resources/block/dungeon/floor/floor.spm");
	public static Floor FLOOR_BLUE = new Floor("Blue Floor", 2, FLOOR_NEUTRAL, "model://DungeonCreeper/resources/block/dungeon/floor/floor.spm");
	public static Floor FLOOR_YELLOW = new Floor("Yellow Floor", 3, FLOOR_NEUTRAL, "model://DungeonCreeper/resources/block/dungeon/floor/floor.spm");
	public static Floor FLOOR_GREEN = new Floor("Green Floor", 4, FLOOR_NEUTRAL, "model://DungeonCreeper/resources/block/dungeon/floor/floor.spm");
	
	public Floor(String name, String model) {
		super(name, model);
	}
	
	public Floor(String name, int data, Material parent, String model) {
		super(name, data, parent, model);
	}

	public boolean isClaimable(){
		return true;
	}
}
