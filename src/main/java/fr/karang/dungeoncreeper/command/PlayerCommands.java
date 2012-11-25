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

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;
import org.spout.api.plugin.Platform;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.component.entity.CreatureComponent;
import fr.karang.dungeoncreeper.component.entity.TeamComponent;
import fr.karang.dungeoncreeper.player.Team;

public class PlayerCommands {
	private final DungeonCreeper plugin;

	public PlayerCommands(DungeonCreeper plugin) {
		this.plugin = plugin;
	}

	@Command(aliases = "slot", usage = "[id]", desc = "Change of slot", min = 1, max = 1)
	@CommandPermissions("dungeoncreeper.command.slot")
	public void changeSkill(CommandContext args, CommandSource source) throws CommandException {
		Player player;
		if(Spout.getEngine().getPlatform() == Platform.CLIENT){
			player = ((Client)Spout.getEngine()).getActivePlayer();
		}else{
			if (!(source instanceof Player)) {
				throw new CommandException("You must be a player to change your class.");
			}
			player = (Player) source;
		}

		int slot = Integer.parseInt(args.get(0).getPlainString());

		player.get(CreatureComponent.class).setSlot(slot);
	}
	
	@Command(aliases = "join", usage = "[id]", desc = "Change of team", min = 1, max = 1)
	@CommandPermissions("dungeoncreeper.command.slot")
	public void changeTeam(CommandContext args, CommandSource source) throws CommandException {
		Player player;
		if(Spout.getEngine().getPlatform() == Platform.CLIENT){
			player = ((Client)Spout.getEngine()).getActivePlayer();
		}else{
			if (!(source instanceof Player)) {
				throw new CommandException("You must be a player to change your class.");
			}
			player = (Player) source;
		}
		
		int id = Integer.parseInt(args.get(0).getPlainString());

		player.get(TeamComponent.class).setTeam(Team.getTeam(id));
	}
}
