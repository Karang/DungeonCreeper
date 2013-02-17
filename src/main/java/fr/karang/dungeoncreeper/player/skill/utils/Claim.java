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
package fr.karang.dungeoncreeper.player.skill.utils;

import fr.karang.dungeoncreeper.component.entity.DungeonPlayer;
import fr.karang.dungeoncreeper.component.entity.HeartComponent;
import fr.karang.dungeoncreeper.event.BlockCause;
import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.player.skill.Skill;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

import org.spout.api.component.impl.InteractComponent;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.math.Rectangle;

public class Claim extends Skill {
	public Claim(byte id) {
		super(id, "claim");
	}

	@Override
	public void handle(Entity source) {
		System.out.println("Claim");
		Block block = source.get(InteractComponent.class).getTargetBlock();
		Entity hearth = source.get(DungeonPlayer.class).getTeam();
		HeartComponent heartComponent = hearth.get(HeartComponent.class);
		BlockCause cause = new BlockCause(source);

		if (heartComponent == null) {
			throw new IllegalStateException("Must have a team");
		}

		if (block != null) {
			DCMaterial material = (DCMaterial) block.getMaterial();

			if (material.isClaimedBy(block, heartComponent)) {
				if(source instanceof Player){
					System.out.println("Block already claimed.");
					((Player)source).sendMessage("Block already claimed.");
				}
				return;
			}

			if (!material.isNextClaimedBlock(block, heartComponent)) {
				if (source instanceof Player){
					System.out.println("No claimed adjacent block.");
					((Player)source).sendMessage("No claimed adjacent block.");
				}
				return;
			}

			World world = block.getWorld();
			int x = block.getX();
			int z = block.getZ();

			if (material.isClaimedBlockByOtherTeam(block, heartComponent)) {
				System.out.println("Declaim other team");
				if (block.getY() == DungeonGenerator.FLOOR_HEIGHT) {
					DCMaterials.DIRT.onPlacement(block, DCMaterials.DIRT.getData(), null, null, false, cause);
					heartComponent.getGame().setTerritory(block.getWorld(), x, z, null);
				} else {
					DCMaterials.DIRT.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), DCMaterials.DIRT.getData(), null, null, false,  cause);
					DCMaterials.DIRT.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), DCMaterials.DIRT.getData(), null, null, false,  cause);
					heartComponent.getGame().setTerritory(block.getWorld(), x, z, null);
				}
			} else {
				System.out.println("Claim");
				if (block.getY() == DungeonGenerator.FLOOR_HEIGHT) {
					DCMaterials.FLOOR.onPlacement(block, DCMaterials.FLOOR.getData(), null, null, false,  cause);
					heartComponent.getGame().setTerritory(block.getWorld(), x, z, heartComponent.getColor());
				} else {
					DCMaterials.FLOOR.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), DCMaterials.FLOOR.getData(), null, null, false,  cause);
					DCMaterials.WALL.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), DCMaterials.WALL.getData(), null, null, false,  cause);
					heartComponent.getGame().setTerritory(block.getWorld(), x, z, heartComponent.getColor());
				}
			}
		} else {
			System.out.println("No target block");
		}
	}

	@Override
	public Rectangle getUv() {
		return new Rectangle(96f / 256f, 0, 32f / 256f, 32f / 256f);
	}
}
