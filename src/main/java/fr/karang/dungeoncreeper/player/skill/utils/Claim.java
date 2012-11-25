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
package fr.karang.dungeoncreeper.player.skill.utils;

import org.spout.api.component.components.HitBlockComponent;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.component.entity.TeamComponent;
import fr.karang.dungeoncreeper.event.BlockCause;
import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.player.skill.Skill;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class Claim extends Skill {

	public Claim(int id) {
		super(id, "claim");
	}

	@Override
	public void handle(Entity source) {
		System.out.println("Claim");
		Block block = source.get(HitBlockComponent.class).getTargetBlock();
		Team team = source.get(TeamComponent.class).getTeam();
		BlockCause cause = new BlockCause(source);

		if(team == null){
			throw new IllegalStateException("Must have a team");
		}
		
		if (block != null){
			DCMaterial material = (DCMaterial) block.getMaterial();

			if (material.isClaimedBy(block, team)) {
				if(source instanceof Player){
					System.out.println("Block déjà claim");
					((Player)source).sendMessage("Block déjà claim");
				}
				return;
			}

			if (!material.isNextClaimedBlock(block, team)) {
				if(source instanceof Player){
					System.out.println("Pas de block voisin claim");
					((Player)source).sendMessage("Pas de block voisin claim");
				}
				return;
			}

			World world = block.getWorld();
			int x = block.getX();
			int z = block.getZ();

			if (material.isClaimedBlockByOtherTeam(block, team)) {
				System.out.println("Declaim other team");
				if (block.getY() == DungeonGenerator.FLOOR_HEIGHT) {
					DCMaterials.DIRT.onPlacement(block,DCMaterials.DIRT.getData(), cause);
					team.getGame().setTerritory(x, z, null);
				} else {
					DCMaterials.DIRT.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), DCMaterials.DIRT.getData(), cause);
					DCMaterials.DIRT.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), DCMaterials.DIRT.getData(), cause);
					team.getGame().setTerritory(x, z, null);
				}
			} else {
				System.out.println("Claim");
				if (block.getY() == DungeonGenerator.FLOOR_HEIGHT) {
					DCMaterials.FLOOR.onPlacement(block, DCMaterials.FLOOR.getData(), cause);
					team.getGame().setTerritory(x, z, team.getColor());
				} else {
					DCMaterials.FLOOR.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), DCMaterials.FLOOR.getData(), cause);
					DCMaterials.WALL.onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), DCMaterials.WALL.getData(), cause);
					team.getGame().setTerritory(x, z, team.getColor());
				}
			}
		}else{
			System.out.println("No target block");
		}
	}

	@Override
	public Rectangle getUv() {
		return new Rectangle(96f/256f, 0, 32f/256f, 32f/256f);
	}

}
