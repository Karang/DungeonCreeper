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
import org.spout.api.material.BlockMaterial;
import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.component.entity.TeamComponent;
import fr.karang.dungeoncreeper.event.BlockCause;
import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.player.skill.Skill;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class Claim extends Skill {

	public Claim(int id) {
		super(id);
	}

	@Override
	public void handle(Entity source) {
		Block block = source.get(HitBlockComponent.class).getTargetBlock();
		Team team = source.get(TeamComponent.class).getTeam();
		BlockCause cause = new BlockCause(source);
		if (block!=null){
			World world  = source.getWorld();
			int x = block.getX();
			int z = block.getZ();

			if(isClaimedBlock(world, x, z, team)){
				if(source instanceof Player)
					((Player)source).sendMessage("Block déjà claim");
				return;
			}

			if(!isNextClaimedBlock(world, x, z, team)){
				if(source instanceof Player)
					((Player)source).sendMessage("Pas de block voisin claim");
				return;
			}

			if(isClaimedBlockByOtherTeam(world, x, z, team)){
				if(block.getY() == DungeonGenerator.FLOOR_HEIGHT){
					Team.TeamColor.NEUTRAL.getFloor().onPlacement(block,Team.TeamColor.NEUTRAL.getFloor().getData(), cause);
				}else{
					Team.TeamColor.NEUTRAL.getFloor().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), Team.TeamColor.NEUTRAL.getFloor().getData(), cause);
					Team.TeamColor.NEUTRAL.getWall().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), Team.TeamColor.NEUTRAL.getWall().getData(), cause);
					Team.TeamColor.NEUTRAL.getWall().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 2, z), Team.TeamColor.NEUTRAL.getWall().getData(), cause);
				}
			}else{
				if(block.getY() == DungeonGenerator.FLOOR_HEIGHT){
					team.getColor().getFloor().onPlacement(block, team.getColor().getFloor().getData(), cause);
				}else{
					team.getColor().getFloor().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z), team.getColor().getFloor().getData(), cause);
					team.getColor().getWall().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 1, z), team.getColor().getWall().getData(), cause);
					team.getColor().getWall().onPlacement(world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT + 2, z), team.getColor().getWall().getData(), cause);
				}
			}
		}
	}

	@Override
	public Rectangle getUv() {
		return new Rectangle(96f/256f, 0, 32f/256f, 32f/256f);
	}

	private boolean isClaimedBlock(World world, int x, int z, Team team){
		BlockMaterial material = world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z).getMaterial();
		if(material instanceof DCMaterial){
			return ((DCMaterial)material).getOwner() == team.getColor();
		}
		return false;
	}

	private boolean isClaimedBlockByOtherTeam(World world, int x, int z, Team team){
		BlockMaterial material = world.getBlock(x, DungeonGenerator.FLOOR_HEIGHT, z).getMaterial();
		if(material instanceof DCMaterial){
			return ((DCMaterial)material).isClaimable() && ((DCMaterial)material).getOwner() != team.getColor();
		}
		return false;
	}

	private boolean isNextClaimedBlock(World world, int x, int z, Team team){

		if(isClaimedBlock(world, x + 1, z, team))
			return true;
		if(isClaimedBlock(world, x - 1, z, team))
			return true;
		if(isClaimedBlock(world, x , z + 1, team))
			return true;
		if(isClaimedBlock(world, x , z - 1, team))
			return true;

		return true;
	}

}
