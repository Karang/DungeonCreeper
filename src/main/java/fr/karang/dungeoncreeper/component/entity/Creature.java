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
package fr.karang.dungeoncreeper.component.entity;

import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.component.entity.creature.BileDemon;
import fr.karang.dungeoncreeper.component.entity.creature.BlackKnight;
import fr.karang.dungeoncreeper.component.entity.creature.DarkAngel;
import fr.karang.dungeoncreeper.component.entity.creature.DarkElf;
import fr.karang.dungeoncreeper.component.entity.creature.DarkMistress;
import fr.karang.dungeoncreeper.component.entity.creature.Firefly;
import fr.karang.dungeoncreeper.component.entity.creature.Gobelin;
import fr.karang.dungeoncreeper.component.entity.creature.HornedReaper;
import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.component.entity.creature.Maiden;
import fr.karang.dungeoncreeper.component.entity.creature.Rogue;
import fr.karang.dungeoncreeper.component.entity.creature.Salamender;
import fr.karang.dungeoncreeper.component.entity.creature.Skeleton;
import fr.karang.dungeoncreeper.component.entity.creature.Troll;
import fr.karang.dungeoncreeper.component.entity.creature.Vampire;
import fr.karang.dungeoncreeper.component.entity.creature.Warlock;
import fr.karang.dungeoncreeper.material.dungeon.DungeonResource;

public enum Creature {
	
	
	BILE_DEMON(BileDemon.class),
	BLACK_KNIGHT(BlackKnight.class),
	DARK_ANGEL(DarkAngel.class),
	DARK_ELF(DarkElf.class),
	DARK_MISTRESS(DarkMistress.class),
	FIREFLY(Firefly.class),
	GOBELIN(Gobelin.class),
	HORNEDREAPER(HornedReaper.class),
	IMP(Imp.class),
	MAIDEN(Maiden.class),
	ROGUE(Rogue.class),
	SALAMENDER(Salamender.class),
	SKELETON(Skeleton.class),
	TROLL(Troll.class),
	VAMPIRE(Vampire.class),
	WARLOCK(Warlock.class);

	static Map<Class<? extends CreatureComponent>,Creature> map = new HashMap<Class<? extends CreatureComponent>,Creature>();
	
	private final Class<? extends CreatureComponent> type;
	private final Map<Class<? extends DungeonResource>,Integer> required = new HashMap<Class<? extends DungeonResource>,Integer>();
	
	Creature(Class<? extends CreatureComponent> type){
		this.type = type;
	}
	
	public Class<? extends CreatureComponent> getComponent(){
		return type;
	}
	
	public Map<Class<? extends DungeonResource>,Integer> getRequired(){
		return required;
	}
	
	public static Creature getCreature(Class<? extends CreatureComponent> type){
		return map.get(type);
	}
	
	//Register required
	static{
		//Inversed map
		for(Creature c : Creature.values()){
			map.put(c.getComponent(), c);
		}
		
		//Imp nothing
		
		//Gobelin
		//GOBELIN.required.put(Bed, 1);
		
		//etc...
	}
	
}
