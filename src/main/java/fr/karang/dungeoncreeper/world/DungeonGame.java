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
package fr.karang.dungeoncreeper.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.player.Team.TeamColor;

import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;

public class DungeonGame {
	private final int id;
	private World world;
	private Map<String, Team> teams = new HashMap<String, Team>();
	private List<Player> players = new ArrayList<Player>();
	private final TeamColor[][] territory;
	private final int width, height;
	private boolean canJoin;

	public DungeonGame(int id, int width, int height) {
		this.id = id;
		this.canJoin = true;

		this.width = width;
		this.height = height;
		territory = new TeamColor[width][height];
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void start() {
		canJoin = false;
		for (Team team : teams.values()) {
			team.respawnPlayers();
		}
	}

	public boolean canJoin() {
		return canJoin;
	}

	public void join(Player player, String team) {
		teams.get(team).playerJoin(player);
		players.add(player);
	}

	public World getWorld() {
		return world;
	}

	public int getId() {
		return id;
	}

	public void setTerritory(int x, int z, TeamColor color) {
		if (x < 0 || x >= width || z < 0 || x >= height) {
			throw new IllegalAccessError("Out of array");
		}
		territory[x][z] = color;
	}

	public TeamColor getTerritory(int x, int z) {
		if (x < 0 || x >= width || z < 0 || x >= height) {
			throw new IllegalAccessError("Out of array");
		}
		return territory[x][z];
	}

	public Team createTeam(int color, Point point) {
		for (TeamColor t : TeamColor.values()) {
			if (t.getColor() == color) {
				Team team = teams.get(t.getName());
				if (team == null) {
					team = new Team(t, point, this);
					teams.put(t.getName(), team);
				}
				return team;
			}
		}
		return null;
	}

	public Collection<Team> getTeams() {
		return teams.values();
	}
}
