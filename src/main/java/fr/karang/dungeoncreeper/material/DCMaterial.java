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

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.player.Team.TeamColor;
import fr.karang.dungeoncreeper.world.DungeonGame;

import org.spout.api.Spout;
import org.spout.api.geo.LoadOption;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.material.BlockMaterial;
import org.spout.api.material.Material;
import org.spout.api.material.block.BlockFace;

public abstract class DCMaterial extends BlockMaterial {
	public DCMaterial(String name, String model) {
		super((short) 0, name, model);
	}

	public DCMaterial(short dataMask, String name, String model) {
		super(dataMask, name, model);
	}

	public DCMaterial(String name, int data, Material parent, String model) {
		super(name, data, parent, model);
	}

	public final DungeonGame getGame(Block block) {
		return block.getWorld().getComponentHolder().get(DungeonGame.class);
	}

	public final TeamColor getOwner(Block block) {
		//Spout.getScheduler().getSnapshotLock().readLock(DungeonCreeper.getInstance());
		byte[][] territory = block.getChunk().getDataMap().get(DungeonData.TERRITORY);
		//Spout.getScheduler().getSnapshotLock().readUnlock(DungeonCreeper.getInstance());
		return TeamColor.values()[territory[block.getX() & Chunk.BLOCKS.MASK][block.getZ() & Chunk.BLOCKS.MASK]];
	}

	public final TeamColor getOwner(World w, int x, int z) {
		Spout.getScheduler().getSnapshotLock().readLock(DungeonCreeper.getInstance());
		Chunk chunk = w.getChunk(x, 0, z);
		Spout.getScheduler().getSnapshotLock().readUnlock(DungeonCreeper.getInstance());
		byte[][] territory = chunk.getDataMap().get(DungeonData.TERRITORY);
		return TeamColor.values()[territory[x & Chunk.BLOCKS.MASK][z & Chunk.BLOCKS.MASK]];
	}

	public boolean isClaimedBy(Block block, Team team) {
		return getOwner(block) == team.getColor();
	}

	public boolean isClaimedBlockByOtherTeam(Block block, Team team) {
		TeamColor owner = getOwner(block);
		if (owner == null) {
			return false;
		}
		return owner != team.getColor();
	}

	public boolean isNextClaimedBlock(Block block, Team team) {
		if (isClaimedBy(block.translate(BlockFace.NORTH), team)) {
			return true;
		}
		if (isClaimedBy(block.translate(BlockFace.SOUTH), team)) {
			return true;
		}
		if (isClaimedBy(block.translate(BlockFace.WEST), team)) {
			return true;
		}
		if (isClaimedBy(block.translate(BlockFace.EAST), team)) {
			return true;
		}
		return false;
	}
}
