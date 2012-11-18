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
package fr.karang.dungeoncreeper.gui;

import java.awt.Color;
import java.util.List;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.RenderPartsHolderComponent;
import org.spout.api.gui.render.RenderPart;
import org.spout.api.math.Rectangle;
import org.spout.api.plugin.Platform;
import org.spout.api.render.RenderMaterial;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.component.entity.CreatureComponent;
import fr.karang.dungeoncreeper.player.skill.Skill;

public class HUD extends Screen {
	private static final float SCALE = 0.75f; // TODO: Apply directly from engine
	private static final float SKILL_OFFSET = 0.2f;
	private static final float SKILL_SIZE = 0.19f;
	
	private final RenderMaterial skillMaterial = (RenderMaterial) Spout.getFilesystem().getResource("material://DungeonCreeper/resources/gui/skillMaterial.smt");
	private Widget skillBar = new Widget();
	private int nbSlots = 0;
	private int slot = 1;
	private final Player player;
	
	public HUD(Player player) {
		if (Spout.getPlatform()!=Platform.CLIENT) {
			throw new IllegalStateException("Only clients can have an HUD screen.");
		}
		
		this.player = player;
		this.setTakesInput(false);
		
		skillBar.add(RenderPartsHolderComponent.class);
		
		CreatureComponent cc = ((Client)Spout.getEngine()).getActivePlayer().get(CreatureComponent.class);
		buildSkillBar(cc.getSkills());
		
		//setCooldown(2, 0.7f);
		//selectSecondarySlot(2);
		
		this.attachWidget(DungeonCreeper.getInstance(), skillBar);
	}
	
	@Override
	public void onTick(float dt) {
		CreatureComponent cc = player.get(CreatureComponent.class);
		int slot = 0;
		for (Skill skill : cc.getSkills()) {
			skill.updateCooldown(dt, player);
			setCooldown(slot++, skill.getCooldown(player));
		}
		if (this.slot!=cc.getSlot()) {
			selectSecondarySlot(cc.getSlot());
		}
	}
	
	public void selectSecondarySlot(int slot) {
		this.slot = slot;
		RenderPart select = skillBar.get(RenderPartsHolderComponent.class).get(0);
		float x = - (nbSlots * SKILL_OFFSET * SCALE) / 2f + slot * SKILL_OFFSET * SCALE;
		select.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
	}
	
	public void setCooldown(int slot, float percent) {
		RenderPart cooldown = skillBar.get(RenderPartsHolderComponent.class).get(2+slot);
		float x = cooldown.getSprite().getX();
		cooldown.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE * percent));
	}
	
	public void buildSkillBar(List<Skill> skills) {
		RenderPartsHolderComponent bar = skillBar.get(RenderPartsHolderComponent.class);
		nbSlots = skills.size();
		float x = - (nbSlots * SKILL_OFFSET * SCALE) / 2f;
		
		RenderPart selectSecondary = new RenderPart();
		selectSecondary.setColor(Color.WHITE);
		selectSecondary.setRenderMaterial(skillMaterial);
		selectSecondary.setSprite(new Rectangle(x + SKILL_OFFSET * SCALE, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
		selectSecondary.setSource(new Rectangle(32f/256f, 224f/256f, 32f/256f, 32f/256f));
		bar.add(selectSecondary, 0);
		
		RenderPart selectPrincipal = new RenderPart();
		selectPrincipal.setColor(Color.WHITE);
		selectPrincipal.setRenderMaterial(skillMaterial);
		selectPrincipal.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
		selectPrincipal.setSource(new Rectangle(32f/256f, 224f/256f, 32f/256f, 32f/256f));
		bar.add(selectPrincipal, 1);
		
		for (int j=0 ; j<nbSlots ; j++) {
			RenderPart cooldown = new RenderPart();
			cooldown.setColor(Color.WHITE);
			cooldown.setRenderMaterial(skillMaterial);
			cooldown.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, 0));
			cooldown.setSource(new Rectangle(0, 224f/256f, 32f/256f, 32f/256f));
			bar.add(cooldown, 2+j);
			x += SKILL_OFFSET * SCALE;
		}
		x = - (nbSlots * SKILL_OFFSET * SCALE) / 2f;
		int i = nbSlots;
		for (Skill skill : skills) {
			RenderPart icon = new RenderPart();
			icon.setColor(Color.WHITE);
			icon.setRenderMaterial(skillMaterial);
			icon.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
			icon.setSource(skill.getUv());
			bar.add(icon, 2 + i++);
			x += SKILL_OFFSET * SCALE;
		}
	}
}
