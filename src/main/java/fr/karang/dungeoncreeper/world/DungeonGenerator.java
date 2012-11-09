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
package fr.karang.dungeoncreeper.world;

import org.spout.api.generator.GeneratorPopulator;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;
import org.spout.api.util.cuboid.CuboidShortBuffer;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class DungeonGenerator implements WorldGenerator {
	// Dungeon size (in chunks)
	private final int dungeonWidth;
	private final int dungeonHeight = 1; // The dungeon is 1 chunk high
	private final int dungeonLength;
	
	public DungeonGenerator(int width, int length) {
		this.dungeonWidth = width;
		this.dungeonLength = length;
	}
	
	public void generate(CuboidShortBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		if (chunkX<0 || chunkY<0 || chunkZ<0 || chunkX>dungeonWidth || chunkY>dungeonHeight || chunkZ>dungeonLength) { 
			return; // Chunk out of bound
		}
		int xx = chunkX<<4, zz = chunkZ<<4;
		
		for (int x = xx ; x < xx+16 ; x++) {
			for (int z = zz ; z < zz+16 ; z++) {
				for (int y = 0 ; y < 16 ; y++) {
					if ((x==0 && chunkX==0) || (x==15 && chunkX==dungeonWidth) || (z==0 && chunkZ==dungeonLength) || (z==15 && chunkZ==dungeonLength)) {
						blockData.set(x, y, z, DCMaterials.UNBREAKABLE_DIRT.getId());
					} else {
						if (y < 2) { // floor layer
							blockData.set(x, y, z, DCMaterials.UNCLAIMED_FLOOR.getId());
						} else if (y < 4) { // play layer
							blockData.set(x, y, z, DCMaterials.DIRT.getId());
						} else if (y == 4) { // protection layer
							blockData.set(x, y, z, DCMaterials.UNBREAKABLE_DIRT.getId());
						}
					}
				}
			}
		}
	}

	public Point getSpectatorSpawn(World world) {
		return new Point(world, dungeonWidth*16/2, 5, dungeonHeight*16/2);
	}
	
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}

	public Populator[] getPopulators() {
		return new Populator[0];
	}

	public GeneratorPopulator[] getGeneratorPopulators() {
		return new GeneratorPopulator[0];
	}

	public String getName() {
		return "Dungeon";
	}

}
