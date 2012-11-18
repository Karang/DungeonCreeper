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
import org.spout.api.geo.cuboid.Block;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.player.skill.Skill;

public class Dig extends Skill {

	public Dig(int id) {
		super(id, 200, "dig");
	}

	@Override
	public void handle(Entity source) {
		Block block = source.get(HitBlockComponent.class).getTargetBlock();
		if (block!=null) {
			if (block.getMaterial().isMaterial(DCMaterials.DIRT)) {
				block.setMaterial(BlockMaterial.AIR);
			} else if (block.getMaterial().isMaterial(DCMaterials.GEM_ORE)) {
				//TODO : Give gold
			} else if (block.getMaterial().isMaterial(DCMaterials.GOLD_BAG)) {
				//TODO : Give gold
				block.setMaterial(BlockMaterial.AIR);
			} else if (block.getMaterial().isMaterial(DCMaterials.GOLD_ORE)) {
				//TODO : Give gold
				block.setMaterial(BlockMaterial.AIR);
			}
		}
	}
}
