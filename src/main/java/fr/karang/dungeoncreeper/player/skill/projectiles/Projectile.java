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
package fr.karang.dungeoncreeper.player.skill.projectiles;

import fr.karang.dungeoncreeper.component.entity.ProjectileComponent;
import fr.karang.dungeoncreeper.player.skill.Skill;

import org.spout.api.entity.Entity;
import org.spout.api.entity.EntityPrefab;

public abstract class Projectile extends Skill {
	public Projectile(int id, long max_cooldown, long cast_time, String skill_name) {
		super(id, max_cooldown, cast_time, "proj_" + skill_name);
	}

	@Override
	public void handle(Entity source) {
		Entity projectile = getProjectile().createEntity(source.getTransform().getPosition());
		source.getWorld().spawnEntity(projectile);
		projectile.get(ProjectileComponent.class).getPhysics()
		.applyImpulse(source.getTransform().getTransform().forwardVector());
	}

	public abstract EntityPrefab getProjectile();
}
