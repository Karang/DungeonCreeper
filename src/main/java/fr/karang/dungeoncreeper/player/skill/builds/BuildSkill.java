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

import org.spout.api.component.impl.InteractComponent;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.math.Vector3;

import fr.karang.dungeoncreeper.player.skill.Skill;

public class BuildSkill extends Skill {
	protected final DefaultedKey<Vector3> BLOCK;
	
	public BuildSkill(byte id, String skill_name) {
		super(id, skill_name);
		BLOCK = new DefaultedKeyImpl<Vector3>(skill_name + "_block", Vector3.ZERO);
	}
	
	public BuildSkill(byte id, long max_cooldown, String skill_name) {
		super(id, max_cooldown, skill_name);
		BLOCK = new DefaultedKeyImpl<Vector3>(skill_name + "_block", Vector3.ZERO);
	}
	
	public BuildSkill(byte id, long max_cooldown, long cast_time, String skill_name) {
		super(id, max_cooldown, cast_time, skill_name);
		BLOCK = new DefaultedKeyImpl<Vector3>(skill_name + "_block", Vector3.ZERO);
	}

	@Override
	public void handle(Entity source) {
		
	}
	
	public boolean stepCast(Entity source, float dt) {
		Block block = getBlock(source);
		
		if (block == null) {
			resetCast(source);
			return false;
		}
		
		if (getCastTime(source) == 0L) {
			source.getData().put(BLOCK, block.getPosition());
		} else if (block.getPosition().compareTo(source.getData().get(BLOCK)) != 0) {
			resetCast(source);
			return false;
		}
		
		addCastTime(source, dt);
		
		if (getCastTime(source) >= cast_time) {
			return true;
		}
		return false;
	}
	
	protected Block getBlock(Entity source) {
		source.get(InteractComponent.class).setRange(4f);
		return source.get(InteractComponent.class).getLastEmpty();
	}
}
