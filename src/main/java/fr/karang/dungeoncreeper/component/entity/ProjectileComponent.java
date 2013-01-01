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
package fr.karang.dungeoncreeper.component.entity;

import org.spout.api.component.impl.PhysicsComponent;
import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Entity;
import org.spout.api.math.Vector3;

import com.bulletphysics.collision.shapes.BoxShape;

import fr.karang.dungeoncreeper.data.DungeonData;

public class ProjectileComponent extends EntityComponent {
	
	private PhysicsComponent physics;
	private Entity shooter;
	
	@Override
	public void onAttached() {
		physics = getOwner().add(PhysicsComponent.class);
		physics.setCollisionShape(new BoxShape(1, 1, 1));
	}
	
	@Override
	public void onTick(float dt) {
		//System.out.println("phys: " + physics.getAngularVelocity());
		getOwner().getTransform().translate(physics.getLinearVelocity().multiply(dt));
	}
	
	public int getDamages() {
		return getData().get(DungeonData.DAMAGES);
	}
	
	public Vector3 getVelocity() {
		return Vector3.ONE;
	}
	
	public Entity getShooter() {
		return shooter;
	}
	
	public void setShooter(Entity shooter) {
		this.shooter = shooter;
	}
	
	public PhysicsComponent getPhysics() {
		return physics;
	}
}
