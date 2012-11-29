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
package fr.karang.dungeoncreeper.world;

import java.util.ArrayList;
import java.util.List;

import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.world.populator.DungeonPopulator;

import org.spout.api.generator.GeneratorPopulator;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;
import org.spout.api.render.Texture;
import org.spout.api.util.cuboid.CuboidShortBuffer;

public class DungeonGenerator implements WorldGenerator {
	//  Floor altitude
	public final static int FLOOR_HEIGHT = 1;
	// Dungeon size (in chunks)
	private final int dungeonWidth;
	private final int dungeonHeight = 1; // The dungeon is 1 chunk high
	private final int dungeonLength;
	// Dungeon size (in blocks)
	private final int dungeonBlockWidth;
	private final int dungeonBlockLength;
	private Texture textureMap;
	private final DungeonGame game;
	private List<Populator> populators = new ArrayList<Populator>();

	public DungeonGenerator(DungeonGame game, Texture textureMap) {
		this.game = game;
		this.textureMap = textureMap;
		this.dungeonWidth = textureMap.getImage().getWidth() / Chunk.BLOCKS.SIZE;
		this.dungeonLength = textureMap.getImage().getHeight() / Chunk.BLOCKS.SIZE;
		this.dungeonBlockWidth = textureMap.getImage().getWidth();
		this.dungeonBlockLength = textureMap.getImage().getHeight();

		populators.add(new DungeonPopulator());
	}

	public void generate(CuboidShortBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		if (!isChunkInDungeon(chunkX, chunkY, chunkZ)) {
			return; // Chunk out of bound
		}
		int xx = chunkX << 4, zz = chunkZ << 4;

		for (int x = xx; x < xx + Chunk.BLOCKS.SIZE; x++) {
			for (int z = zz; z < zz + Chunk.BLOCKS.SIZE; z++) {
				for (int y = 0; y < 3 /*Chunk.BLOCKS.SIZE*/ ; y++) {
					if ((x == 0) || (x == dungeonBlockWidth) || (z == 0) || (z == dungeonBlockLength)) {
						blockData.set(x, y, z, DCMaterials.SOLID_ROCK.getId());
					} else {
						if (y == 0 /*|| y==4*/) { // To obtain a good view from height
							blockData.set(x, y, z, DCMaterials.SOLID_ROCK.getId());
						} else if (y < 4) {
							blockData.set(x, y, z, DCMaterials.DIRT.getId());
						}
					}
				}
			}
		}
	}

	public int getColor(int x, int z) {
		if (x < 0 || x >= dungeonBlockWidth || z < 0 || z >= dungeonBlockLength) {
			return -1;
		}
		return textureMap.getImage().getRGB(x, z);
	}

	public boolean isChunkInDungeon(int chunkX, int chunkY, int chunkZ) {
		return !(chunkX<0 || chunkY<0 || chunkZ<0 || chunkX>=dungeonWidth || chunkY>=dungeonHeight || chunkZ>=dungeonLength);
	}

	public int getSize() {
		return dungeonWidth * dungeonHeight * dungeonLength;
	}

	public int getWidth() {
		return dungeonWidth;
	}

	public int getLength() {
		return dungeonLength;
	}

	public Point getSpectatorSpawn(World world) {
		return new Point(world, dungeonWidth * Chunk.BLOCKS.SIZE / 2, 5, dungeonHeight * Chunk.BLOCKS.SIZE / 2);
	}

	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}

	public Populator[] getPopulators() {
		return populators.toArray(new Populator[populators.size()]);
	}

	public GeneratorPopulator[] getGeneratorPopulators() {
		return new GeneratorPopulator[0];
	}

	public String getName() {
		return "Dungeon";
	}

	public DungeonGame getGame() {
		return game;
	}
}
