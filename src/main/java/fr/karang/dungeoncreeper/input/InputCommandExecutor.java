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
package fr.karang.dungeoncreeper.input;

import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.CommandSource;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import fr.karang.dungeoncreeper.component.entity.CreatureComponent;

public class InputCommandExecutor implements CommandExecutor {
	
	public void processCommand(CommandSource source, Command command, CommandContext args) throws CommandException {
		if (!(source instanceof Player)) {
			throw new CommandException("Only players may open inventory windows.");
		}

		String name = command.getPreferredName();
		if (name.startsWith("+skillbar_")) {
			Player player = (Player) source;
			CreatureComponent skillbar = player.get(CreatureComponent.class);
			if (skillbar != null) {
				int newSlot = skillbar.getSlot();
				if (name.endsWith("left")) {
					newSlot--;
					if (newSlot < 0) {
						newSlot = skillbar.getSlotAmount()-1;
					}
				} else if (name.endsWith("right")) {
					newSlot++;
					if (newSlot >= skillbar.getSlotAmount()) {
						newSlot = 0;
					}
				}
				System.out.println("test"+newSlot);
				skillbar.setSlot(newSlot);
			}
		}
	}
}
