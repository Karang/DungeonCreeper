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
package fr.karang.dungeoncreeper.player.skill.builds;

import java.util.Random;

import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.material.DCMaterials;

import org.spout.api.component.impl.InteractComponent;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.material.BlockMaterial;

public class Dig extends BuildSkill {
	protected final DefaultedKey<Long> TIME = new DefaultedKeyImpl<Long>("dig_time", 0L);
	
	private final Random rand = new Random();

	public Dig(byte id) {
		super(id, 200, "dig");
	}

	@Override
	public void handle(Entity source) {
		Block block = getBlock(source);
		if (block != null) {
			Imp cc = source.get(Imp.class);

			if (cc == null) {
				throw new IllegalStateException("Only Imps can dig.");
			}

			if (block.isMaterial(DCMaterials.DIRT)) {
				block.setMaterial(BlockMaterial.AIR);
				cc.addXp(1);

			} else if (block.isMaterial(DCMaterials.GEM_ORE)) {
				cc.addGold(rand.nextInt(20) + 5);
				cc.addXp(5);

			} else if (block.isMaterial(DCMaterials.GOLD_ORE)) {
				cc.addGold(rand.nextInt(20) + 5);
				cc.addXp(5);
				block.setMaterial(BlockMaterial.AIR);

			} else if (block.isMaterial(DCMaterials.GOLD_BAG)) {
				//TODO : Give gold
				block.setMaterial(BlockMaterial.AIR);
			}
		}
	}
	
	@Override
	public boolean stepCast(Entity source, float dt) {
		Block block = getBlock(source);

		if (block == null || block.getMaterial().getHardness() == -1f) {
			resetCast(source);
			return false;
		}

		if (getCastTime(source) == 0L) {
			source.getData().put(BLOCK, block.getPosition());
			source.getData().put(TIME, (long) (block.getMaterial().getHardness()*5000L));
		} else if (block.getPosition().compareTo(source.getData().get(BLOCK)) != 0) {
			resetCast(source);
			return false;
		}

		addCastTime(source, dt);

		if (getCastTime(source) >= block.getMaterial().getHardness()*5000) {
			return true;
		}
		return false;
	}
	
	@Override
	protected Block getBlock(Entity source) {
		source.get(InteractComponent.class).setRange(4f);
		return source.get(InteractComponent.class).getTargetBlock();
	}
	
	@Override
	public float getCastPercent(Entity source) {
		long time = source.getData().get(TIME);
		if (time == 0L) {
			return 0f;
		}
		return (float) getCastTime(source) / time;
	}
}
