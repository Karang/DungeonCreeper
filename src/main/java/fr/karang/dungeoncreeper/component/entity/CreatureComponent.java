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
package fr.karang.dungeoncreeper.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spout.api.Spout;
import org.spout.api.component.components.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.entity.state.PlayerInputState;
import org.spout.api.math.MathHelper;

import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.event.entity.EntitySkillUseEvent;
import fr.karang.dungeoncreeper.player.Team;
import fr.karang.dungeoncreeper.player.skill.Skill;

public abstract class CreatureComponent extends EntityComponent {

	private Map<Skill, Integer> requiredLevel = new HashMap<Skill, Integer>();
	private List<Skill> skills = new ArrayList<Skill>();
	
	@Override
	public void onAttached() {
		getData().put(DungeonData.HEALTH, 10);
		getData().put(DungeonData.MAX_HEALTH, 10);
		getData().put(DungeonData.DAMAGES, 0);
		getData().put(DungeonData.SKILLSLOT, 1);
		getData().put(DungeonData.LEVEL, 1);
	}
	
	@Override
	public void onTick(float dt) {
		PlayerInputState input = ((Player) getOwner()).input();
		
		if (input.getFire1()) {
			primaryInterract();
		}
		
		if (input.getInteract()) {
			secondaryInterract();
		}
		
		//TODO: slot selection
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
	
	public void setSlot(int slot){
		getData().put(DungeonData.SKILLSLOT, MathHelper.clamp(slot, 1, skills.size() - 1));
	}
	
	public int getSlot() {
		return getData().get(DungeonData.SKILLSLOT);
	}

	public void primaryInterract() {
		EntitySkillUseEvent event = Spout.getEngine().getEventManager().callEvent(new EntitySkillUseEvent(getOwner(), getPrimarySkill()));
		if (event.isCancelled() || event.getSkill().canUse(event.getEntity())) {
			System.out.println("Action canceled");
			return;
		}
		event.getSkill().initCooldown(event.getEntity());
		event.getSkill().handle(event.getEntity());
	}

	public void secondaryInterract() {
		EntitySkillUseEvent event = Spout.getEngine().getEventManager().callEvent(new EntitySkillUseEvent(getOwner(), getSecondarySkill()));
		if (event.isCancelled() || event.getSkill().canUse(event.getEntity())) {
			System.out.println("Action canceled");
			return;
		}
		event.getSkill().initCooldown(event.getEntity());
		event.getSkill().handle(event.getEntity());
	}
	
	public boolean hasRequired(Team team){
		return true;
	}
}
