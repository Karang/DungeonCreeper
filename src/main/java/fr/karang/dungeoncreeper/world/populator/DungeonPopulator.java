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
package fr.karang.dungeoncreeper.world.populator;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.cuboid.Chunk;

import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class DungeonPopulator extends Populator {
	
	private static Map<Integer, WorldGeneratorObject> materials = new HashMap<Integer, WorldGeneratorObject>();
	
	static {
		materials.put(new Color(237,28,36).getRGB(), NaturalObject.LAVA);
		materials.put(new Color(255,255,0).getRGB(), NaturalObject.GOLD_ORE);
		materials.put(new Color(163,92,112).getRGB(), NaturalObject.GEM_ORE);
		materials.put(new Color(185,122,87).getRGB(), NaturalObject.DIRT);
		materials.put(new Color(255,255,255).getRGB(), NaturalObject.CAVERN);
	}
	
	@Override
	public void populate(Chunk chunk, Random random) {
		DungeonGenerator gen = (DungeonGenerator) chunk.getWorld().getGenerator();
		int startX = chunk.getX()<<4, startZ = chunk.getZ()<<4;
		for (int x=startX ; x<startX+16 ; x++) {
			for (int z=startZ ; z<startZ+16 ; z++) {
				WorldGeneratorObject obj = materials.get(gen.getColor(x, z));
				if (obj == null)
					obj = NaturalObject.DIRT;
				obj.placeObject(chunk.getWorld(), x, 1, z);
			}
		}
	}

}
