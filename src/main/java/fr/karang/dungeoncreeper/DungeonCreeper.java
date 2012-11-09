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
package fr.karang.dungeoncreeper;

import org.spout.api.Engine;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.Platform;

import fr.karang.dungeoncreeper.command.AdministrationCommands;
import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class DungeonCreeper extends CommonPlugin {
	private static DungeonCreeper instance;
	private Engine engine;
	private DungeonConfig config;
	
	@Override
	public void onLoad() {
		instance = this;
		engine = getEngine();
		config = new DungeonConfig(getDataFolder());
	}
	
	@Override
	public void onEnable() {
		if (engine.debugMode() || engine.getPlatform()==Platform.SERVER) {
			setupWorld();
		}

		//Commands
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		engine.getRootCommand().addSubCommands(this, AdministrationCommands.class, commandRegFactory);
		
		getLogger().info("DungeonCreeper " + getDescription().getVersion() + " enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("DungeonCreeper " + getDescription().getVersion() + " disabled.");
	}
	
	private void setupWorld() {
		DungeonGenerator generator = new DungeonGenerator();
		World world = engine.loadWorld("Dungeon", generator);
		if (world.getAge()<=0) {
			world.setSpawnPoint(new Transform(generator.getSpectatorSpawn(world), Quaternion.IDENTITY, Vector3.ONE));
		}
	}
	
	public DungeonConfig getConfig() {
		return config;
	}
	
	public static DungeonCreeper getInstance() {
		return instance;
	}

}
