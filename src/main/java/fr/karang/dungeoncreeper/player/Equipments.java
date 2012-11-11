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
package fr.karang.dungeoncreeper.player;

import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.player.equipment.BileDemon;
import fr.karang.dungeoncreeper.player.equipment.BlackKnights;
import fr.karang.dungeoncreeper.player.equipment.CreatureComponent;
import fr.karang.dungeoncreeper.player.equipment.DarkAngels;
import fr.karang.dungeoncreeper.player.equipment.DarkElf;
import fr.karang.dungeoncreeper.player.equipment.DarkMistress;
import fr.karang.dungeoncreeper.player.equipment.Firefly;
import fr.karang.dungeoncreeper.player.equipment.Gobelin;
import fr.karang.dungeoncreeper.player.equipment.HornedReaper;
import fr.karang.dungeoncreeper.player.equipment.Imp;
import fr.karang.dungeoncreeper.player.equipment.Maiden;
import fr.karang.dungeoncreeper.player.equipment.Rogue;
import fr.karang.dungeoncreeper.player.equipment.Salamender;
import fr.karang.dungeoncreeper.player.equipment.Skeleton;
import fr.karang.dungeoncreeper.player.equipment.Troll;
import fr.karang.dungeoncreeper.player.equipment.Vampire;
import fr.karang.dungeoncreeper.player.equipment.Warlock;

public class Equipments {

	private static Map<Integer, Class<? extends CreatureComponent>> byID = new HashMap<Integer, Class<? extends CreatureComponent>>();
	private static Map<Class<? extends CreatureComponent>,Integer> byClass = new HashMap<Class<? extends CreatureComponent>,Integer>();
	private static int id = 0;
	
	private static void register(Class<? extends CreatureComponent> component){
		byID.put(id, component);
		byClass.put(component,id++);
	}
	
	public static int getIdByCreatureComponent(Class<? extends CreatureComponent> component){
		return byClass.get(component);
	}
	
	public static Class<? extends CreatureComponent> getCreatureComponentById(int id){
		return byID.get(id);
	}
	
	static{
		register(BileDemon.class);
		register(BlackKnights.class);
		register(DarkAngels.class);
		register(DarkElf.class);
		register(DarkMistress.class);
		register(Firefly.class);
		register(Gobelin.class);
		register(HornedReaper.class);
		register(Imp.class);
		register(Maiden.class);
		register(Rogue.class);
		register(Salamender.class);
		register(Skeleton.class);
		register(Troll.class);
		register(Vampire.class);
		register(Warlock.class);
	}
	
}
