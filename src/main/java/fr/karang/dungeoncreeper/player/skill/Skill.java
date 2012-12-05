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
package fr.karang.dungeoncreeper.player.skill;

import java.util.HashMap;
import java.util.Map;

import org.spout.api.entity.Entity;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.data.DungeonData;

public abstract class Skill {
	private static Map<Integer, Skill> skills = new HashMap<Integer, Skill>();
	private final int id;
	protected final long max_cooldown;
	protected final long cast_time;
	private final DefaultedKey<Long> KEY_COOLDOWN;

	public Skill(int id, String skill_name) {
		this(id, 0L, skill_name);
	}
	
	public Skill(int id, long max_cooldown, String skill_name) {
		this(id, max_cooldown, 0L, skill_name);
	}

	public Skill(int id, long max_cooldown, long cast_time, String skill_name) {
		this.id = id;
		this.max_cooldown = max_cooldown;
		this.cast_time = cast_time;
		this.KEY_COOLDOWN =  new DefaultedKeyImpl<Long>("cd_" + skill_name, 0L);
		skills.put(id, this);
	}

	public float getCooldown(Entity source) {
		if (max_cooldown == 0L) {
			return 0f;
		}
		return (float) source.getData().get(KEY_COOLDOWN) / max_cooldown;
	}

	public void initCooldown(Entity source) {
		source.getData().put(KEY_COOLDOWN, max_cooldown);
	}

	public boolean canUse(Entity source) {
		if (max_cooldown == 0L) {
			return true;
		}
		return (source.getData().get(KEY_COOLDOWN) == 0L);
	}

	public void updateCooldown(float dt, Entity source) {
		long cd = source.getData().get(KEY_COOLDOWN);
		cd -= dt;
		if (cd < 0) {
			source.getData().put(KEY_COOLDOWN, 0L);
		} else {
			source.getData().put(KEY_COOLDOWN, cd);
		}
	}

	public int getId() {
		return id;
	}

	public static Skill getSkill(int skillId) {
		return skills.get(skillId);
	}

	public abstract void handle(Entity source);
	
	public boolean stepCast(Entity source, float dt) {
		addCastTime(source, dt);
		
		if (getCastTime(source) >= cast_time) {
			return true;
		}
		return false;
	}
	
	public void resetCast(Entity source) {
		source.getData().put(DungeonData.CAST_TIME, 0L);
	}
	
	public void addCastTime(Entity source, float dt) {
		source.getData().put(DungeonData.CAST_TIME, (long)(getCastTime(source) + dt * 1000));
	}
	
	public float getCastPercent(Entity source) {
		if (cast_time == 0L) {
			return 0f;
		}
		return (float) getCastTime(source) / cast_time;
	}
	
	public long getCastTime(Entity source) {
		return source.getData().get(DungeonData.CAST_TIME);
	}
	
	public int getManaCost() {
		return 0;
	}

	public Rectangle getUv() {
		return new Rectangle(0, 0, 32f / 256f, 32f / 256f);
	}
}
