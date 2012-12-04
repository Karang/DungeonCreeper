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
package fr.karang.dungeoncreeper.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.room.instance.RoomContainer;
import fr.karang.dungeoncreeper.room.instance.RoomInstance;
import fr.karang.dungeoncreeper.room.type.Room.Rooms;
import fr.karang.dungeoncreeper.world.DungeonGame;

import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.material.Material;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.math.Vector4;
import org.spout.api.model.mesh.Mesh;

public class Team {
	public enum TeamColor {
		/*
		*	Neutral : 128/128/128
		*	Red : 255/0/0
		*	Blue : 0/0/255
		*	Green : 0/255/0
		*	Yellow : 255/255/0
		*/

		NEUTRAL("Neutral", Color.GRAY),
		RED("Red", Color.RED),
		BLUE("Blue", Color.BLUE),
		GREEN("Green", Color.GREEN),
		YELLOW("Yellow", Color.YELLOW);
		private Map<Material, Mesh> teamMesh = new HashMap<Material, Mesh>();
		private final String name;
		private final int color;
		private final Vector4 vector;

		TeamColor(String name, Color color) {
			this.name = name;
			this.color = color.getRGB();
			this.vector = new Vector4(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		}

		public String getName() {
			return name;
		}

		public int getColor() {
			return color;
		}
		
		public Vector4 getVector() {
			return vector;
		}

		public Mesh getTeamMesh(Material material) {
			return teamMesh.get(material);
		}

		static {
			NEUTRAL.teamMesh.put(DCMaterials.BRIDGE, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			RED.teamMesh.put(DCMaterials.BRIDGE, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			BLUE.teamMesh.put(DCMaterials.BRIDGE, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			GREEN.teamMesh.put(DCMaterials.BRIDGE, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			YELLOW.teamMesh.put(DCMaterials.BRIDGE, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));

			NEUTRAL.teamMesh.put(DCMaterials.FLOOR, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			RED.teamMesh.put(DCMaterials.FLOOR, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			BLUE.teamMesh.put(DCMaterials.FLOOR, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			GREEN.teamMesh.put(DCMaterials.FLOOR, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));
			YELLOW.teamMesh.put(DCMaterials.FLOOR, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/bridge/bridge.uvs"));

			NEUTRAL.teamMesh.put(DCMaterials.WALL, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/wall/wall.uvs"));
			RED.teamMesh.put(DCMaterials.WALL, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/wall/wallRed.uvs"));
			BLUE.teamMesh.put(DCMaterials.WALL, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/wall/wallBlue.uvs"));
			GREEN.teamMesh.put(DCMaterials.WALL, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/wall/wallGreen.uvs"));
			YELLOW.teamMesh.put(DCMaterials.WALL, (Mesh) Spout.getFilesystem().getResource("cubemesh://DungeonCreeper/resources/block/dungeon/wall/wallYellow.uvs"));
		}

	}

	private static int ID_GEN = 0;
	private static Map<Integer, Team> teams = new HashMap<Integer, Team>();

	public static Team getTeam(int id) {
		return teams.get(id);
	}

	private static void addTeam(Team team) {
		teams.put(team.getId(), team);
	}

	private final int id;
	private final DungeonGame game;
	private final TeamColor color;
	private final Map<Rooms, RoomContainer> rooms = new HashMap<Rooms, RoomContainer>();
	private List<Player> players = new ArrayList<Player>();
	private Transform spawn;
	private int gold = 0;

	public Team(TeamColor color, Point spawn, DungeonGame game) {
		id = ID_GEN++;
		addTeam(this);
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

		if (container != null) {
			return container.getSurface() >= surface;
		}

		return false;
	}

	public void addRoom(RoomInstance roomInstance) {
		RoomContainer container = rooms.get(roomInstance.getRoom());

		if (container == null) {
			container = new RoomContainer(roomInstance.getRoom());
			rooms.put(roomInstance.getRoom(), container);
		}

		container.addRoom(roomInstance);
	}

	public void removeRoom(RoomInstance roomInstance) {
		RoomContainer container = rooms.get(roomInstance.getRoom());

		if (container == null) {
			throw new IllegalStateException("RoomContainer unfinded");
		}

		container.removeRoom(roomInstance);
	}

	public int getId() {
		return id;
	}
}
