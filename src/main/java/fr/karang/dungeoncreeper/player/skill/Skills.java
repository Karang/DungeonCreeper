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
import fr.karang.dungeoncreeper.player.skill.attacks.Bite;
import fr.karang.dungeoncreeper.player.skill.attacks.HandToHand;
import fr.karang.dungeoncreeper.player.skill.misc.RaiseDead;
import fr.karang.dungeoncreeper.player.skill.misc.SkeletonArmy;
import fr.karang.dungeoncreeper.player.skill.projectiles.Arrow;
import fr.karang.dungeoncreeper.player.skill.projectiles.Disruption;
import fr.karang.dungeoncreeper.player.skill.projectiles.FireBall;
import fr.karang.dungeoncreeper.player.skill.projectiles.FireBomb;
import fr.karang.dungeoncreeper.player.skill.projectiles.Freeze;
import fr.karang.dungeoncreeper.player.skill.projectiles.GasCloud;
import fr.karang.dungeoncreeper.player.skill.projectiles.GasMissile;
import fr.karang.dungeoncreeper.player.skill.projectiles.GuidedBolt;
import fr.karang.dungeoncreeper.player.skill.projectiles.HailStorm;
import fr.karang.dungeoncreeper.player.skill.projectiles.Invisibility;
import fr.karang.dungeoncreeper.player.skill.projectiles.Knives;
import fr.karang.dungeoncreeper.player.skill.projectiles.Lightning;
import fr.karang.dungeoncreeper.player.skill.projectiles.MaidenWeb;
import fr.karang.dungeoncreeper.player.skill.projectiles.PoisonSpit;
import fr.karang.dungeoncreeper.player.skill.projectiles.Spit;
import fr.karang.dungeoncreeper.player.skill.projectiles.StunFireball;
import fr.karang.dungeoncreeper.player.skill.projectiles.Whirlwind;
import fr.karang.dungeoncreeper.player.skill.utils.Claim;
import fr.karang.dungeoncreeper.player.skill.utils.Dig;
import fr.karang.dungeoncreeper.player.skill.utils.Haste;
import fr.karang.dungeoncreeper.player.skill.utils.Heal;
import fr.karang.dungeoncreeper.player.skill.utils.Research;
import fr.karang.dungeoncreeper.player.skill.utils.Slow;
import fr.karang.dungeoncreeper.player.skill.utils.Teleport;

public class Skills {
	
	public static int id = 0;
	
	/**
	 * Attack
	 */
	public static final Skill ATTACKSWORD = new AttackSword(id++);
	public static final Skill HANDTOHAND = new HandToHand(id++);

	
	/**
	 * Utils
	 */
	public static final Skill CLAIM = new Claim(id++);
	public static final Skill DIG = new Dig(id++);
	public static final Skill RESEARCH = new Research(id++);
	public static final Skill TELEPORT = new Teleport(id++);
	public static final Skill FIREBALL = new FireBall(id++);
	public static final Skill HEAL = new Heal(id++);
	public static final Skill INVISIBILITY = new Invisibility(id++);
	public static final Skill WHIRLWIND = new Whirlwind(id++);
	public static final Skill SLOW = new Slow(id++);
	public static final Skill RAISE_DEAD = new RaiseDead(id++);
	public static final Skill DISRUPTION = new Disruption(id++);
	public static final Skill HAILSTORM = new HailStorm(id++);
	public static final Skill SKELETON_ARMY = new SkeletonArmy(id++);
	public static final Skill GAS_CLOUD = new GasCloud(id++);
	public static final Skill GAS_MISSILE = new GasMissile(id++);
	public static final Skill ARROW = new Arrow(id++);
	public static final Skill KNIVES = new Knives(id++);
	public static final Skill GUIDED_BOLT = new GuidedBolt(id++);
	public static final Skill LIGHTNING = new Lightning(id++);
	public static final Skill FREEZE = new Freeze(id++);
	public static final Skill HAIL_STORM = new HailStorm(id++);
	public static final Skill STUN_FIREBALL = new StunFireball(id++);
	public static final Skill HASTE = new Haste(id++);
	public static final Skill MAIDEN_WEB = new MaidenWeb(id++);
	public static final Skill POISON_SPIT = new PoisonSpit(id++);
	public static final Skill SPIT = new Spit(id++);
	public static final Skill BITE = new Bite(id++);
	public static final Skill FIREBOMB = new FireBomb(id++);
}
