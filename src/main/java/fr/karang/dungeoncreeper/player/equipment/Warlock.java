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
package fr.karang.dungeoncreeper.player.equipment;

import org.spout.api.inventory.Inventory;

import fr.karang.dungeoncreeper.player.DungeonCreeperData;
import fr.karang.dungeoncreeper.player.skill.Skills;

/**
 * The Imp is your most important creature you have. They do
 * everything that your other creatures can't, including digging,
 * claiming land, installing traps, rescuing knocked out creatures,
 * etc. They do not fight, and instead will run from battle. In
 * order to expand, you need these.
 * 
 * @source http://dungeonkeeper.wikia.com/wiki/Warlock
 */
public class Warlock extends CreatureComponent {

	public Warlock(){
		addSkill(Skills.HANDTOHAND, 1);
		addSkill(Skills.FIREBALL, 1);
		addSkill(Skills.HEAL, 2);
		addSkill(Skills.FIREBOMB, 8);
	}
	
	public Inventory getInventory() {
		return getData().get(DungeonCreeperData.Warlock_Inventory);
	}
}
