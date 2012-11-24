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
package fr.karang.dungeoncreeper.component.entity;

import org.spout.api.math.Rectangle;
import org.spout.api.math.Vector2;

import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.player.skill.Skills;
import fr.karang.dungeoncreeper.room.type.Room.Rooms;

/**
 * The Imp is your most important creature you have. They do
 * everything that your other creatures can't, including digging,
 * claiming land, installing traps, rescuing knocked out creatures,
 * etc. They do not fight, and instead will run from battle. In
 * order to expand, you need these.
 * 
 * @source http://dungeonkeeper.wikia.com/wiki/Imp
 */
public class Imp extends CreatureComponent {
	
	private Rooms roomClaim = Rooms.LAIR;
	private Vector2 point1, point2;
	
	public Imp() {
		addSkill(Skills.DIG, 1);
		addSkill(Skills.TELEPORT, 1);
		//addSkill(Skills.CLAIM, 1);
		addSkill(Skills.HANDTOHAND, 1);
		addSkill(Skills.HASTE, 4);
		addSkill(Skills.TELEPORT, 8);
	}
	
	@Override
	public void onAttached() {
		super.onAttached();
		getData().put(DungeonData.GOLD_AMOUNT, 0);
	}

	public Rooms getRoomClaim() {
		return roomClaim;
	}

	public void setRoomClaim(Rooms roomClaim) {
		this.roomClaim = roomClaim;
	}

	public void setPoint2(Vector2 point2) {
		this.point2 = point2;
	}

	public void setPoint1(Vector2 point1) {
		this.point1 = point1;
	}
	
	public Rectangle getBuildRect(){
		if( point1 == null || point2 == null)
			return null;
		int x = Math.min(point1.getFloorX(), point1.getFloorX());
		int y = Math.min(point1.getFloorY(), point1.getFloorY());
		int height = Math.max(point1.getFloorX(), point1.getFloorX()) - x ;
		int width = Math.max(point1.getFloorY(), point1.getFloorY()) - y ;
		return new Rectangle(x, y, width, height);
	}
}
