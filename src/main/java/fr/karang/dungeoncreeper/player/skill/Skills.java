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
package fr.karang.dungeoncreeper.player.skill;

import fr.karang.dungeoncreeper.player.skill.attacks.AttackSword;
import fr.karang.dungeoncreeper.player.skill.attacks.HandToHand;
import fr.karang.dungeoncreeper.player.skill.utils.Claim;
import fr.karang.dungeoncreeper.player.skill.utils.Dig;
import fr.karang.dungeoncreeper.player.skill.utils.Haste;
import fr.karang.dungeoncreeper.player.skill.utils.Research;
import fr.karang.dungeoncreeper.player.skill.utils.Teleport;

public class Skills {
	
	/**
	 * Attack
	 */
	public static final Skill ATTACKSWORD = new AttackSword();
	public static final Skill HANDTOHAND = new HandToHand();

	
	/**
	 * Utils
	 */
	public static final Skill CLAIM = new Claim();
	public static final Skill DIG = new Dig();
	public static final Skill RESEARCH = new Research();
	public static final Skill TELEPORT = new Teleport();
	public static final Skill FIREBALL = null;
	public static final Skill HEAL = null;
	public static final Skill INVISIBILITY = null;
	public static final Skill WHIRLWIND = null;
	public static final Skill SLOW = null;
	public static final Skill RAISE_DEAD = null;
	public static final Skill DISRUPTION = null;
	public static final Skill HAILSTORM = null;
	public static final Skill SKELETON_ARMY = null;
	public static final Skill GAS_CLOUD = null;
	public static final Skill GAS_MISSILE = null;
	public static final Skill ARROW = null;
	public static final Skill KNIVES = null;
	public static final Skill GUIDED_BOLT = null;
	public static final Skill LIGHTNING = null;
	public static final Skill FREEZE = null;
	public static final Skill HAIL_STORM = null;
	public static final Skill STUN_FIREBALL = null;
	public static final Skill HASTE = new Haste();
	public static final Skill MAIDEN_WEB = null;
	public static final Skill POISON_SPIT = null;
	public static final Skill SPIT = null;
	public static final Skill BITE = null;
	public static final Skill FIREBOMB = null;
}
