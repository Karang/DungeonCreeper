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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.spout.api.Client;
import org.spout.api.Engine;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.plugin.Platform;
import org.spout.api.protocol.Session;
import org.spout.api.render.Texture;

import fr.karang.dungeoncreeper.player.DungeonPlayer;
import fr.karang.dungeoncreeper.protocol.message.lobby.PlayerListMessage;
import fr.karang.dungeoncreeper.protocol.message.lobby.WorldListMessage;
import fr.karang.dungeoncreeper.thread.DungeonLoaderThread;
import fr.karang.dungeoncreeper.world.DungeonGame;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class Lobby {
	private static final int LOADER_THREAD_COUNT = 16;
	private Engine engine;
	private List<Player> players = new ArrayList<Player>();
	private Map<World,DungeonGame> games = new HashMap<World,DungeonGame>();
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
		System.out.println("Player joined the lobby!");
		player.add(DungeonPlayer.class);
		sendPlayerList(player.getSession());
		sendGameList(player.getSession());
		players.add(player);
	}
	
	public void createNewGame() {
		Texture textureMap = (Texture) Spout.getFilesystem().getResource("texture://DungeonCreeper/resources/map.png");
		DungeonGame game = new DungeonGame(gameId,textureMap.getWidth(),textureMap.getHeight());
		DungeonGenerator generator = new DungeonGenerator(game,textureMap);
		World world = Spout.getEngine().loadWorld("dungeon_"+gameId, generator);
		game.setWorld(world);
		
		if (world.getAge()<=0) {
			world.setSpawnPoint(new Transform(generator.getSpectatorSpawn(world), Quaternion.IDENTITY, Vector3.ONE));
		}
		
		int total = generator.getSize();
		int step = total / 10;
		DungeonLoaderThread[] loaderThreads = new DungeonLoaderThread[LOADER_THREAD_COUNT];
		
		for (int i = 0; i < LOADER_THREAD_COUNT; i++) {
			loaderThreads[i] = new DungeonLoaderThread(total, step);
		}

		for (int X=0 ; X<generator.getWidth() ; X++) {
			for (int Z=0 ; Z<generator.getLength() ; Z++) {
				DungeonLoaderThread.addChunk(world, X, 1, Z);
			}
		}

		for (int i = 0; i < LOADER_THREAD_COUNT; i++) {
			loaderThreads[i].start();
		}

		for (int i = 0; i < LOADER_THREAD_COUNT; i++) {
			try {
				loaderThreads[i].join();
			} catch (InterruptedException ie) {
				engine.getLogger().info("Interrupted when waiting for spawn area to load");
			}
		}
		
		games.put(world,game);
		gameId++;
	}
	
	public void removeAllGames() {
		for (Entry<World, DungeonGame> entry : games.entrySet()) {
			engine.unloadWorld(entry.getKey(), false);
			deleteFolder(entry.getKey().getDirectory());
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
	
	public void sendPlayerList(Session session) {
		for (Player player : players) {
			session.send(false, true, new PlayerListMessage(player.getName()));
			player.getSession().send(false, true, new PlayerListMessage(session.getPlayer().getName()));
		}
	}
	
	public void sendGameList(Session session) {
		for (DungeonGame game : games.values()) {
			session.send(false, true, new WorldListMessage(game.getWorld().getName(), 0, 10));
		}
	}
	
	public LobbyScreen getScreen() {
		return screen;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public DungeonGame getGame(World world) {
		DungeonGame game = games.get(world);
		
		if( game == null )
			throw new IllegalStateException("No game in this world");
		
		return game;
	}

}
