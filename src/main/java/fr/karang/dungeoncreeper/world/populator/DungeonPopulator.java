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
package fr.karang.dungeoncreeper.world.populator;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fr.karang.dungeoncreeper.component.entity.HeartComponent;
import fr.karang.dungeoncreeper.component.world.PartyComponent;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;

public class DungeonPopulator extends Populator {
	private static Map<Integer, WorldGeneratorObject> materials = new HashMap<Integer, WorldGeneratorObject>();

	static {
		materials.put(new Color(229, 0, 28).getRGB(), NaturalObject.LAVA);
		materials.put(new Color(255, 243, 10).getRGB(), NaturalObject.GOLD_ORE);
		materials.put(new Color(144, 48, 147).getRGB(), NaturalObject.GEM_ORE);
		materials.put(new Color(169, 102, 69).getRGB(), NaturalObject.DIRT);
		materials.put(new Color(108, 108, 108).getRGB(), NaturalObject.CAVERN);
		materials.put(new Color(0, 0, 0).getRGB(), NaturalObject.SOLID_ROCK);
		materials.put(new Color(70, 70, 225).getRGB(), NaturalObject.WATER);
	}

	@Override
	public void populate(Chunk chunk, Random random) {
		DungeonGenerator gen = (DungeonGenerator) chunk.getWorld().getGenerator();
		if (!gen.isChunkInDungeon(chunk.getX(), chunk.getY(), chunk.getZ())) {
			return; // Chunk out of bound
		}

		int startX = chunk.getX() << 4, startZ = chunk.getZ() << 4;
		for (int x = startX; x < startX + Chunk.BLOCKS.SIZE; x++) {
			for (int z = startZ; z < startZ + Chunk.BLOCKS.SIZE; z++) {
				int color = gen.getColor(x, z);

				if (color == -1) //out of the texture
				{
					continue;
				}

				WorldGeneratorObject obj = materials.get(color);

				if (obj == null) {
					PartyComponent game = chunk.getWorld().getComponentHolder().get(PartyComponent.class);
					HeartComponent hearth = game.createTeam(color, new Point(chunk.getWorld(), x, DungeonGenerator.FLOOR_HEIGHT + 1, z));
					if (hearth != null) {
						System.out.println("Add Heart : " + hearth.getColor());
						obj = new HearthRoomObject(hearth);
					}
				}

				if (obj == null) {
					continue;
				}

				if (obj.canPlaceObject(chunk.getWorld(), x, 1, z)) {
					obj.placeObject(chunk.getWorld(), x, 1, z);
				}
			}
		}
	}
}
