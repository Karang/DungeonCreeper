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
/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
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
package fr.karang.dungeoncreeper.command;

import fr.karang.dungeoncreeper.DungeonCreeper;

import org.spout.api.Client;
import org.spout.api.Server;
import org.spout.api.Spout;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

public class AdministrationCommands {
	private final DungeonCreeper plugin;

	public AdministrationCommands(DungeonCreeper plugin) {
		this.plugin = plugin;
	}

	@Command(aliases = "class", usage = "[class]", desc = "Change of class", min = 1, max = 1)
	@CommandPermissions("dungeoncreeper.command.class")
	public void changeClass(CommandContext args, CommandSource source) throws CommandException {
		if (args.length() == 0) {
			if (!(source instanceof Player)) {
				throw new CommandException("You must be a player to change your class.");
			}
		}
		source.sendMessage(ChatStyle.BRIGHT_GREEN, "Class changed.");
	}

	@Command(aliases = {"kill"}, usage = "[player]", desc = "Kill yourself or another player", min = 0, max = 1)
	@CommandPermissions("dungeoncreeper.command.kill")
	public void kill(CommandContext args, CommandSource source) throws CommandException {
		if (args.length() == 0) {
			if (!(source instanceof Player)) {
				throw new CommandException("Don't be silly...you cannot kill yourself as the console.");
			}
			//((Player) source).get(HealthComponent.class).kill(HealthChangeCause.COMMAND);
		} else {
			if (Spout.getEngine() instanceof Client) {
				throw new CommandException("You cannot search for players unless you are in server mode.");
			}
			Player victim = ((Server) Spout.getEngine()).getPlayer(args.getString(0), true);
			if (victim != null) {
				//victim.get(HealthComponent.class).kill(HealthChangeCause.COMMAND);
			}
		}
	}

	@Command(aliases = {"version", "vr"}, usage = "", desc = "Print out the version information for DungeonCreeper", min = 0, max = 0)
	@CommandPermissions("dungeoncreeper.command.version")
	public void getVersion(CommandContext args, CommandSource source) {
		source.sendMessage("DungeonCreeper ", plugin.getDescription().getVersion(), " (Implementing protocol v", plugin.getDescription().getData("protocol"), ")");
		source.sendMessage("Powered by Spout " + Spout.getEngine().getVersion(), " (Implementing SpoutAPI ", Spout.getAPIVersion(), ")");
	}

	@Command(aliases = {"restart"}, usage = "", desc = "Restart a party", min = 0, max = 0)
	@CommandPermissions("dungeoncreeper.command.biome")
	public void getBiomeName(CommandContext args, CommandSource source) throws CommandException {
		//TODO : Regen world, team, all
	}
}
