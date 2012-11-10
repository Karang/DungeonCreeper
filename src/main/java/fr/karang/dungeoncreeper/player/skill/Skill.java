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
package fr.karang.dungeoncreeper.player.skill;

import java.util.HashMap;
import java.util.Map;

import org.spout.api.entity.Entity;
import org.spout.api.math.Rectangle;

public abstract class Skill {
	
	private static Map<Integer, Skill> skills = new HashMap<Integer, Skill>();
	
	private final int id;
	private long cooldown;
	
	public Skill(int id) {
		this(id, 0L);
	}
	
	public Skill(int id, long cooldown) {
		this.id = id;
		this.cooldown = cooldown;
		skills.put(id, this);
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public long getCooldown() {
		return cooldown;
	}
	
	public int getId() {
		return id;
	}
	
	public static Skill getSkill(int skillId) {
		return skills.get(skillId);
	}
	
	public abstract void handle(Entity source);
	
	public Rectangle getUv() {
		return new Rectangle(0, 0, 32f/256f, 23f/256f);
	}
}
