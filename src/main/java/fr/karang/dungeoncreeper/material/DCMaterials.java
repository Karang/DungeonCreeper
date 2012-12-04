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
package fr.karang.dungeoncreeper.material;

import fr.karang.dungeoncreeper.material.dungeon.Bridge;
import fr.karang.dungeoncreeper.material.dungeon.DungeonHeart;
import fr.karang.dungeoncreeper.material.dungeon.Floor;
import fr.karang.dungeoncreeper.material.dungeon.GoldBag;
import fr.karang.dungeoncreeper.material.dungeon.Wall;
import fr.karang.dungeoncreeper.material.dungeon.doors.Barricade;
import fr.karang.dungeoncreeper.material.dungeon.doors.BracedDoor;
import fr.karang.dungeoncreeper.material.dungeon.doors.MagicDoor;
import fr.karang.dungeoncreeper.material.dungeon.doors.SecretDoor;
import fr.karang.dungeoncreeper.material.dungeon.doors.SteelDoor;
import fr.karang.dungeoncreeper.material.dungeon.doors.WoodenDoor;
import fr.karang.dungeoncreeper.material.dungeon.room.Hatchery;
import fr.karang.dungeoncreeper.material.dungeon.room.Lair;
import fr.karang.dungeoncreeper.material.natural.Dirt;
import fr.karang.dungeoncreeper.material.natural.GemOre;
import fr.karang.dungeoncreeper.material.natural.GoldOre;
import fr.karang.dungeoncreeper.material.natural.Lava;
import fr.karang.dungeoncreeper.material.natural.SolidRock;
import fr.karang.dungeoncreeper.material.natural.UnclaimedFloor;
import fr.karang.dungeoncreeper.material.natural.Water;

import org.spout.api.material.BlockMaterial;

public class DCMaterials {
	// Natural materials
	public static final BlockMaterial AIR = BlockMaterial.AIR;
	public static final SolidRock SOLID_ROCK = new SolidRock();
	public static final Dirt DIRT = new Dirt();
	public static final UnclaimedFloor UNCLAIMED_FLOOR = new UnclaimedFloor();
	public static final Lava LAVA = new Lava();
	public static final Water WATER = new Water();
	public static final GoldOre GOLD_ORE = new GoldOre();
	public static final GemOre GEM_ORE = new GemOre();
	
	// Dungeon materials
	public static final DungeonHeart DUNGEON_HEART = new DungeonHeart();
	public static final Wall WALL = new Wall();
	public static final Floor FLOOR = new Floor();
	public static final Bridge BRIDGE = new Bridge();
	
	// Dungeon doors
	public static final Barricade BARRICADE = new Barricade();
	public static final BracedDoor BRACED_DOOR = new BracedDoor();
	public static final MagicDoor MAGIC_DOOR = new MagicDoor();
	public static final SecretDoor SECRET_DOOR = new SecretDoor();
	public static final SteelDoor STEEL_DOOR = new SteelDoor();
	public static final WoodenDoor WOODEN_DOOR = new WoodenDoor();
	
	// Dungeon rooms
	public static final Lair LAIR = new Lair();
	public static final Hatchery HATCHERY = new Hatchery();
	
	// Dungeon decorations
	public static final GoldBag GOLD_BAG = new GoldBag();

	//Add BatchEffect
	static {

	}
}
