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
package fr.karang.dungeoncreeper.protocol.handler.entity;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
import org.spout.api.math.Quaternion;
import org.spout.api.math.QuaternionMath;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityTeleportMessage;

public class EntityTeleportHandler extends MessageHandler<EntityTeleportMessage> {
	@Override
	public void handleServer(Session session, EntityTeleportMessage message) {
		if (!session.hasPlayer()) {
			return;
		}
		
		Player player = session.getPlayer();
		World world = player.getWorld();
		
		Entity entity = world.getEntity(message.getEntityId());
		
		if(entity == null){
			return;
		}
		
		Point point = new Point(world, message.getX(), message.getY(), message.getZ());
		Quaternion rotation = QuaternionMath.rotation(message.getPitch(), message.getYaw(), 0);
		
		entity.getScene().setPosition(point);
		entity.getScene().setRotation(rotation);
	}

	@Override
	public void handleClient(Session session, EntityTeleportMessage message) {
		if (!session.hasPlayer()) {
			return;
		}
		
		Player player = session.getPlayer();
		World world = player.getWorld();
		
		Entity entity = world.getEntity(message.getEntityId());
		
		if(entity == null){
			return;
		}
		
		Point point = new Point(world, message.getX(), message.getY(), message.getZ());
		Quaternion rotation = QuaternionMath.rotation(message.getPitch(), message.getYaw(), 0);
		
		entity.getScene().setPosition(point);
		entity.getScene().setRotation(rotation);
	}
}
