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
package fr.karang.dungeoncreeper.component.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.component.entity.HeartComponent;
import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.game.GameState;
import fr.karang.dungeoncreeper.game.TeamColor;

import org.spout.api.component.type.WorldComponent;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.LoadOption;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;

public class PartyComponent extends WorldComponent{
	
	private Map<TeamColor, Entity> teams = new HashMap<TeamColor, Entity>();
	private GameState state = GameState.LOBBY;

	public void start() {
		if(state != GameState.LOBBY)
			throw new IllegalStateException("Can't start a already started game");
		
		state = GameState.GAME;
		
		//TODO : Don't respawn player, just allow them to go out of the hearth room
		for (Entity entity : teams.values()) {
			HeartComponent hearth = entity.get(HeartComponent.class);
			hearth.respawnPlayers();
		}
	}
	
	public void end() {
		if(state != GameState.GAME)
			throw new IllegalStateException("Can't start a already started game");
		
		state = GameState.END;
		
		//TODO : End game gui
	}

	public void join(Player player, String team) {
		teams.get(team).get(HeartComponent.class).playerJoin(player);
	}

	public void setTerritory(World world, int x, int z, TeamColor color) {
		Chunk chunk = world.getChunk(x, 0, z);
		byte[][] territory = chunk.getDataMap().get(DungeonData.TERRITORY);
		territory[x & Chunk.BLOCKS.MASK][z & Chunk.BLOCKS.MASK] = (byte)color.ordinal();
	}

	public TeamColor getTerritory(World world, int x, int z) {
		Chunk chunk = world.getChunk(x, 0, z);
		byte[][] territory = chunk.getDataMap().get(DungeonData.TERRITORY);
		return TeamColor.values()[territory[x & Chunk.BLOCKS.MASK][z & Chunk.BLOCKS.MASK]];
	}

	public HeartComponent createTeam(int color, Point point) {
		for (TeamColor t : TeamColor.values()) {
			if (t.getColor() == color) {
				Entity hearth = teams.get(t.getName());
				if (hearth == null) {
					hearth = point.getWorld().createAndSpawnEntity(point, HeartComponent.class, LoadOption.LOAD_GEN);
					hearth.get(HeartComponent.class).setColor(t);
					teams.put(t, hearth);
				}
				return hearth.get(HeartComponent.class);
			}
		}
		return null;
	}

	public Collection<Entity> getTeams() {
		return teams.values();
	}

	public Entity getTeam(TeamColor color) {
		return teams.get(color);
	}
}
