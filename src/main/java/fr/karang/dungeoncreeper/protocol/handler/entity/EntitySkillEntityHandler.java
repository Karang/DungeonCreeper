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

import fr.karang.dungeoncreeper.protocol.message.entity.EntitySkillEntityMessage;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

public class EntitySkillEntityHandler extends MessageHandler<EntitySkillEntityMessage> {
	@Override
	public void handleServer(Session session, EntitySkillEntityMessage message) {
		if (!session.hasPlayer()) {
			return;
		}
		
		Player player = session.getPlayer();
		World world = player.getWorld();
		
		Entity caster = message.getCaster() != -1 ? world.getEntity(message.getCaster()) : null;
		Entity target = message.getTarget() != -1 ? world.getEntity(message.getTarget()) : null;
		
		if(caster != null){
			return;
		}
		
		if(target != null){
			return;
		}
	}

	@Override
	public void handleClient(Session session, EntitySkillEntityMessage message) {
		if (!session.hasPlayer()) {
			return;
		}
		
		Player player = session.getPlayer();
		World world = player.getWorld();
		
		Entity caster = message.getCaster() != -1 ? world.getEntity(message.getCaster()) : null;
		Entity target = message.getTarget() != -1 ? world.getEntity(message.getTarget()) : null;
		
		if(caster != null){
			return;
		}
		
		if(target != null){
			return;
		}
	}
}
