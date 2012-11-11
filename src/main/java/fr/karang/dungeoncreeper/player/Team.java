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
package fr.karang.dungeoncreeper.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;

import fr.karang.dungeoncreeper.material.dungeon.Bridge;
import fr.karang.dungeoncreeper.material.dungeon.Floor;
import fr.karang.dungeoncreeper.material.dungeon.Wall;
import fr.karang.dungeoncreeper.room.instance.RoomContainer;
import fr.karang.dungeoncreeper.room.instance.RoomInstance;
import fr.karang.dungeoncreeper.room.type.Room.Rooms;
import fr.karang.dungeoncreeper.world.DungeonGame;

public class Team {
	
	public enum TeamColor{
		NEUTRAL(Floor.FLOOR_NEUTRAL,Wall.WALL_NEUTRAL,Bridge.BRIDGE_NEUTRAL),
		RED(Floor.FLOOR_RED,Wall.WALL_RED,Bridge.BRIDGE_RED),
		BLUE(Floor.FLOOR_RED,Wall.WALL_RED,Bridge.BRIDGE_RED),
		GREEN(Floor.FLOOR_RED,Wall.WALL_RED,Bridge.BRIDGE_RED),
		YELLOW(Floor.FLOOR_RED,Wall.WALL_RED,Bridge.BRIDGE_RED);
		
		private final Floor floor;
		private final Wall wall;
		private final Bridge bridge;
		private final short data;
		
		TeamColor(Floor floor, Wall wall, Bridge bridge){
			this.floor = floor;
			this.wall = wall;
			this.bridge = bridge;
			
			//Assume all material of one team has the same data
			data = floor.getData();
		}

		public Floor getFloor() {
			return floor;
		}

		public Wall getWall() {
			return wall;
		}

		public Bridge getBridge() {
			return bridge;
		}

		public short getData() {
			return data;
		}
		
	}
	
	private final DungeonGame game;
	
	private final String name;
	private final TeamColor color;
	private final Map<Rooms,RoomContainer> rooms = new HashMap<Rooms,RoomContainer>();
	private List<Player> players = new ArrayList<Player>();
	private Transform spawn;
	private int gold = 0;
	
	public Team(String name, TeamColor color, Point spawn, DungeonGame game) {
		this.name = name;
		this.game = game;
		this.color = color;
		this.spawn = new Transform(spawn, Quaternion.IDENTITY, Vector3.ONE);
	}
	
	public void respawnPlayers() {
		for (Player player : players) {
			player.teleport(spawn);
		}
	}
	
	public void playerJoin(Player player) {
		players.add(player);
		player.get(DungeonPlayer.class).setTeam(this);
	}
	
	public void playerQuit(Player player) {
		players.remove(player);
		player.get(DungeonPlayer.class).setTeam(null);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public String getName() {
		return name;
	}
	
	public TeamColor getColor() {
		return color;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public DungeonGame getGame() {
		return game;
	}

	public boolean hasRoom(Rooms type, int surface) {
		RoomContainer container = rooms.get(type);

		if( container != null )
			return container.getSurface() >= surface;

			return false;
	}

	public void addRoom(RoomInstance roomInstance) {
		RoomContainer container = rooms.get(roomInstance.getRoom());
		
		if( container == null){
			container = new RoomContainer(roomInstance.getRoom());
			rooms.put(roomInstance.getRoom(), container);
		}
		
		container.addRoom(roomInstance);
	}

	public void removeRoom(RoomInstance roomInstance) {
		RoomContainer container = rooms.get(roomInstance.getRoom());
		
		if( container == null)
			throw new IllegalStateException("RoomContainer unfinded");
		
		container.removeRoom(roomInstance);
	}
}
