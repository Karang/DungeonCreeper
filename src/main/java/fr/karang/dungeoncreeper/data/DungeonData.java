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
package fr.karang.dungeoncreeper.data;

import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;

public class DungeonData {
	public static final DefaultedKey<Integer> TEAM = new DefaultedKeyImpl<Integer>("team", 0);
	public static final DefaultedKey<Integer> HEALTH = new DefaultedKeyImpl<Integer>("health", 1);
	public static final DefaultedKey<Integer> MAX_HEALTH = new DefaultedKeyImpl<Integer>("max_health", 1);
	public static final DefaultedKey<Integer> GOLD_AMOUNT = new DefaultedKeyImpl<Integer>("gold_amount", 0);
	public static final DefaultedKey<Integer> DAMAGES = new DefaultedKeyImpl<Integer>("Damages", 0);
	public static final DefaultedKey<Integer> SKILLSLOT = new DefaultedKeyImpl<Integer>("skillslot", 0);
	public static final DefaultedKey<Integer> LEVEL = new DefaultedKeyImpl<Integer>("level", 1);
	public static final DefaultedKey<Integer> XP = new DefaultedKeyImpl<Integer>("xp", 0);
	public static final DefaultedKey<Integer> MANA = new DefaultedKeyImpl<Integer>("mana", 1);
	public static final DefaultedKey<Integer> MAX_MANA = new DefaultedKeyImpl<Integer>("max_mana", 1);
	public static final DefaultedKey<Long> CAST_TIME = new DefaultedKeyImpl<Long>("cast_time", 0L);
	public static final DefaultedKey<Integer> CAST_TYPE = new DefaultedKeyImpl<Integer>("cast_type", 0);
	
	//Chunk data
	public static final DefaultedKey<byte[][]> TERRITORY = new DefaultedKeyImpl<byte[][]>("TERRITORY", new byte[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE]);
}
