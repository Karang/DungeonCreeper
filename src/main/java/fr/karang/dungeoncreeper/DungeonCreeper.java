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
package fr.karang.dungeoncreeper;

import java.util.logging.Level;

import fr.karang.dungeoncreeper.command.AdministrationCommands;
import fr.karang.dungeoncreeper.command.PlayerCommands;
import fr.karang.dungeoncreeper.lobby.Lobby;
import fr.karang.dungeoncreeper.protocol.DungeonProtocol;

import org.spout.api.Client;
import org.spout.api.Engine;
import org.spout.api.Spout;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.input.InputManager;
import org.spout.api.input.Keyboard;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.Platform;
import org.spout.api.protocol.Protocol;

public class DungeonCreeper extends CommonPlugin {
	private static DungeonCreeper instance;
	private Engine engine;
	private DungeonConfig config;
	private Lobby lobby;

	@Override
	public void onLoad() {
		instance = this;
		engine = getEngine();
		config = new DungeonConfig(getDataFolder());
		Protocol.registerProtocol(new DungeonProtocol());
	}

	@Override
	public void onEnable() {
		try {
			config.load();
		} catch (ConfigurationException e) {
			getLogger().log(Level.WARNING, "Error loading DungeonCreeper configuration: ", e);
		}

		lobby = new Lobby();

		// World
		if (engine.debugMode() || engine.getPlatform() == Platform.SERVER) {
			lobby.createNewGame();
		}

		// Commands
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		engine.getRootCommand().addSubCommands(this, AdministrationCommands.class, commandRegFactory);
		engine.getRootCommand().addSubCommands(this, PlayerCommands.class, commandRegFactory);
		engine.getEventManager().registerEvents(new DungeonListener(this), this);

		if (Spout.getPlatform() == Platform.CLIENT) {
			InputManager input = ((Client) Spout.getEngine()).getInputManager();
			input.bind(Keyboard.KEY_0, "slot 0");
			input.bind(Keyboard.KEY_1, "slot 1");
			input.bind(Keyboard.KEY_2, "slot 2");
			input.bind(Keyboard.KEY_3, "slot 3");
			input.bind(Keyboard.KEY_4, "slot 4");
			input.bind(Keyboard.KEY_5, "slot 5");
			input.bind(Keyboard.KEY_6, "slot 6");
			input.bind(Keyboard.KEY_7, "slot 7");
			input.bind(Keyboard.KEY_8, "slot 8");
			input.bind(Keyboard.KEY_9, "slot 9");
		}

		getLogger().info("DungeonCreeper " + getDescription().getVersion() + " enabled!");
	}

	@Override
	public void onDisable() {
		lobby.removeAllGames();
		getLogger().info("DungeonCreeper " + getDescription().getVersion() + " disabled.");
	}

	public Lobby getLobby() {
		return lobby;
	}

	public DungeonConfig getConfig() {
		return config;
	}

	public static DungeonCreeper getInstance() {
		return instance;
	}
}
