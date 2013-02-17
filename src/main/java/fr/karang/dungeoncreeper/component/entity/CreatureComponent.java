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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.event.entity.EntitySkillUseEvent;
import fr.karang.dungeoncreeper.player.skill.Skill;

import org.spout.api.Spout;
import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.entity.state.PlayerInputState;
import org.spout.api.math.GenericMath;

public abstract class CreatureComponent extends EntityComponent {
	//7, 12, 16, 20, 30, 50, 70, 90
	private static final int[] levelXp = {5, 12, 24, 40, 60, 90, 140, 210, 300};

	private Map<Skill, Integer> requiredLevel = new HashMap<Skill, Integer>();
	private List<Skill> skills = new ArrayList<Skill>();

	@Override
	public void onAttached() {
		getData().put(DungeonData.HEALTH, 10);
		getData().put(DungeonData.MAX_HEALTH, 10);
		getData().put(DungeonData.DAMAGES, 0);
		getData().put(DungeonData.SKILLSLOT, 1);
		getData().put(DungeonData.LEVEL, 1);
		getData().put(DungeonData.XP, 0);
	}

	public int getHealth(){
		return (int)getData().get(DungeonData.HEALTH);
	}

	public void setHealth(int health){
		if(health <= 0){
			//TODO : Kill and respawn
		}

		getData().put(DungeonData.HEALTH, health);
	}

	public void damage(int damage){
		int health = getHealth() - damage;

		if(health <= 0){
			//TODO : Kill and respawn
		}

		setHealth(health);
	}

	@Override
	public void onTick(float dt) {
		PlayerInputState input = ((Player) getOwner()).input();

		if (input.getFire1()) {
			if (getCastType()==1) {
				if (getPrimarySkill().canUse(getOwner()) && getPrimarySkill().stepCast(getOwner(), dt)) {
					primaryInterract();
					resetCast(0);
				}
			} else {
				resetCast(1);
			}
		} else if (input.getInteract()) {
			if (getCastType()==2) {
				if (getSecondarySkill().canUse(getOwner()) && getSecondarySkill().stepCast(getOwner(), dt)) {
					secondaryInterract();
					resetCast(0);
				}
			} else {
				resetCast(2);
			}
		} else {
			resetCast(0);
		}
	}

	public void addXp(int amount) {
		int xp = getData().get(DungeonData.XP);
		xp += amount;

		int level = getData().get(DungeonData.LEVEL);

		if (level==10) {
			return;
		}

		if (xp >= levelXp[level-1]) {
			getData().put(DungeonData.LEVEL, level + 1);
		}

		getData().put(DungeonData.XP, xp);
	}

	private void resetCast(int type) {
		getData().put(DungeonData.CAST_TIME, 0L);
		getData().put(DungeonData.CAST_TYPE, type);
	}

	private int getCastType() {
		return getData().get(DungeonData.CAST_TYPE);
	}

	public Skill getSkillInCast() {
		if (getCastType()==1)
			return getPrimarySkill();
		if (getCastType()==2)
			return getSecondarySkill();
		return null;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void addSkill(Skill skill, int level) {
		requiredLevel.put(skill, level);
		skills.add(skill);
	}

	public Skill getPrimarySkill() {
		return skills.get(0);
	}

	public Skill getSecondarySkill() {
		return skills.get(getSlot());
	}

	public int getSlotAmount() {
		return skills.size();
	}

	public void setSlot(int slot) {
		getData().put(DungeonData.SKILLSLOT, GenericMath.clamp(slot, 1, skills.size() - 1));
	}

	public int getSlot() {
		return getData().get(DungeonData.SKILLSLOT);
	}

	public void primaryInterract() {
		EntitySkillUseEvent event = Spout.getEngine().getEventManager().callEvent(new EntitySkillUseEvent(getOwner(), getPrimarySkill()));
		if (event.isCancelled()) {
			System.out.println("Action canceled");
			return;
		}
		event.getSkill().initCooldown(event.getEntity());
		event.getSkill().handle(event.getEntity());
	}

	public void secondaryInterract() {
		EntitySkillUseEvent event = Spout.getEngine().getEventManager().callEvent(new EntitySkillUseEvent(getOwner(), getSecondarySkill()));
		if (event.isCancelled()) {
			System.out.println("Action canceled");
			return;
		}
		event.getSkill().initCooldown(event.getEntity());
		event.getSkill().handle(event.getEntity());
	}
}
