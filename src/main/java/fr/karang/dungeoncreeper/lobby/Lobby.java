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
package fr.karang.dungeoncreeper.lobby;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.Client;
import org.spout.api.Engine;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.plugin.Platform;

import fr.karang.dungeoncreeper.player.DungeonPlayer;
import fr.karang.dungeoncreeper.world.DungeonGame;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class Lobby {
	private Engine engine;
	private List<Player> players = new ArrayList<Player>();
	private List<DungeonGame> games = new ArrayList<DungeonGame>();
	private LobbyScreen screen;
	private int gameId = 0;
	
	public Lobby() {
		engine = Spout.getEngine();
		if (engine.getPlatform() == Platform.CLIENT) {
			screen = new LobbyScreen(this);
			((Client) engine).getScreenStack().openScreen(screen);
		}
	}
	
	public void playerJoin(Player player) {
		player.add(DungeonPlayer.class);
		players.add(player);
	}
	
	public void createNewGame() {
		DungeonGenerator generator = new DungeonGenerator("texture://DungeonCreeper/resources/map.png");
		World world = Spout.getEngine().loadWorld("dungeon_"+gameId, generator);
		if (world.getAge()<=0) {
			world.setSpawnPoint(new Transform(generator.getSpectatorSpawn(world), Quaternion.IDENTITY, Vector3.ONE));
		}
		games.add(new DungeonGame(world));
		gameId++;
	}
	
	public void removeAllGames() {
		for (DungeonGame game : games) {
			engine.unloadWorld(game.getWorld(), false);
			deleteFolder(game.getWorld().getDirectory());
		}
		games.clear();
	}
	
	public void removeGame(DungeonGame game) {
		engine.unloadWorld(game.getWorld(), false);
		deleteFolder(game.getWorld().getDirectory());
		games.remove(game);
	}
	
	private void deleteFolder(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File subFile : file.listFiles()) {
					deleteFolder(subFile);
				}
			}
			file.delete();
		}
	}
	
	public LobbyScreen getScreen() {
		return screen;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<DungeonGame> getGames() {
		return games;
	}

}
