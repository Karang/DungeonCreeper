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
package fr.karang.dungeoncreeper.gui;

import java.awt.Color;
import java.util.List;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.component.entity.CreatureComponent;
import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.player.skill.Skill;
import fr.karang.dungeoncreeper.render.DungeonResources;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.entity.Player;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.LabelComponent;
import org.spout.api.gui.component.RenderPartsHolderComponent;
import org.spout.api.gui.render.RenderPart;
import org.spout.api.gui.render.RenderPartPack;
import org.spout.api.math.Rectangle;
import org.spout.api.plugin.Platform;

public class HUD extends Screen {
	private static final float SCALE = 0.75f; // TODO: Apply directly from engine
	private static final float SKILL_OFFSET = 0.2f;
	private static final float SKILL_SIZE = 0.19f;
	
	private int nbSlots = 0;
	private int slot = 1;
	private final Player player;
	
	private Widget skillBar = ((Client) Spout.getEngine()).getScreenStack().createWidget();
	private final Widget life = ((Client) Spout.getEngine()).getScreenStack().createWidget();
	private final Widget gold = ((Client) Spout.getEngine()).getScreenStack().createWidget();
	private final Widget mana = ((Client) Spout.getEngine()).getScreenStack().createWidget();
	private final Widget level = ((Client) Spout.getEngine()).getScreenStack().createWidget();
	
	// DEBUG
	private final Widget creature = ((Client) Spout.getEngine()).getScreenStack().createWidget();

	public HUD(Player player) {
		if (Spout.getPlatform() != Platform.CLIENT) {
			throw new IllegalStateException("Only clients can have an HUD screen.");
		}
		
		this.player = player;
		this.setTakesInput(false);

		skillBar.add(RenderPartsHolderComponent.class);

		CreatureComponent cc = ((Client) Spout.getEngine()).getActivePlayer().get(CreatureComponent.class);
		
		buildSkillBar(cc.getSkills());
		buildCrosshair();
		buildInfos();
		buildTest();
	}

	@Override
	public void onTick(float dt) {
		CreatureComponent cc = player.get(CreatureComponent.class);
		
		int slot = 0;
		
		for (Skill skill : cc.getSkills()) {
			skill.updateCooldown(dt, player);
			setCooldown(slot++, skill.getCooldown(player));
		}
		
		if (this.slot != cc.getSlot()) {
			selectSecondarySlot(cc.getSlot());
		}
		
		life.get(LabelComponent.class).setText(new ChatArguments(ChatStyle.RED, cc.getHealth()));
		gold.get(LabelComponent.class).setText(new ChatArguments(ChatStyle.YELLOW, player.getData().get(DungeonData.GOLD_AMOUNT)));
		mana.get(LabelComponent.class).setText(new ChatArguments(ChatStyle.BLUE, player.getData().get(DungeonData.MANA)));
		level.get(LabelComponent.class).setText(new ChatArguments(ChatStyle.BRIGHT_GREEN, player.getData().get(DungeonData.LEVEL)));
	}

	public void selectSecondarySlot(int slot) {
		this.slot = slot;
		RenderPart select = skillBar.get(RenderPartsHolderComponent.class).get(0).get(0);
		float x = -(nbSlots * SKILL_OFFSET * SCALE) / 2f + slot * SKILL_OFFSET * SCALE;
		select.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
	}

	public void setCooldown(int slot, float percent) {
		RenderPart cooldown = skillBar.get(RenderPartsHolderComponent.class).get(0).get(2 + slot);
		float x = cooldown.getSprite().getX();
		cooldown.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE * percent));
	}
	
	public void buildTest() {
		LabelComponent txtCreature = creature.add(LabelComponent.class);
		
		creature.getTransform().setPosition(-0.8f, 0);
		txtCreature.setFont(DungeonResources.FONT);
		txtCreature.setText(new ChatArguments("Imp"));
		
		attachWidget(DungeonCreeper.getInstance(), creature);
	}
	
	public void buildInfos() {
		LabelComponent txtLife = life.add(LabelComponent.class);
		LabelComponent txtGold = gold.add(LabelComponent.class);
		LabelComponent txtMana = mana.add(LabelComponent.class);
		LabelComponent txtLevel = level.add(LabelComponent.class);
		
		life.getTransform().setPosition(-0.3f, -0.75f);
		txtLife.setFont(DungeonResources.FONT);
		
		gold.getTransform().setPosition(0, 0.8f);
		txtGold.setFont(DungeonResources.FONT);
		
		mana.getTransform().setPosition(0.3f, -0.75f);
		txtMana.setFont(DungeonResources.FONT);
		
		level.getTransform().setPosition(0, -0.75f);
		txtLevel.setFont(DungeonResources.FONT);
		
		attachWidget(DungeonCreeper.getInstance(), life);
		attachWidget(DungeonCreeper.getInstance(), gold);
		attachWidget(DungeonCreeper.getInstance(), mana);
		attachWidget(DungeonCreeper.getInstance(), level);
	}

	public void buildCrosshair() {
		Widget crosshair = ((Client) Spout.getEngine()).getScreenStack().createWidget();
		

		RenderPartPack crossPack = new RenderPartPack(DungeonResources.SKILL_MAT);
		RenderPart cross = new RenderPart();
		cross.setSource(new Rectangle(0, 0.75f, 0.02f, 0.02f));
		cross.setSprite(new Rectangle(-0.025f * SCALE, -0.025f, 0.05f * SCALE, 0.05f));

		crossPack.add(cross);
		crosshair.add(RenderPartsHolderComponent.class).add(crossPack);
		
		RenderPartPack castPack = new RenderPartPack(DungeonResources.CROSSHAIR_MAT);
		RenderPart cast = new RenderPart();
		cast.setSource(new Rectangle(0, 0, 1, 1));
		cast.setSprite(new Rectangle(-0.1f * SCALE, -0.1f, 0.2f * SCALE, 0.2f));

		castPack.add(cast);
		crosshair.get(RenderPartsHolderComponent.class).add(castPack);
		attachWidget(DungeonCreeper.getInstance(), crosshair);
	}
	
	public void buildSkillBar(List<Skill> skills) {
		nbSlots = skills.size();
		float x = -(nbSlots * SKILL_OFFSET * SCALE) / 2f;
		RenderPartPack skillBarPack = new RenderPartPack(DungeonResources.SKILL_MAT);

		RenderPart selectSecondary = new RenderPart();
		selectSecondary.setColor(Color.WHITE);
		selectSecondary.setSprite(new Rectangle(x + SKILL_OFFSET * SCALE, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
		selectSecondary.setSource(new Rectangle(32f / 256f, 224f / 256f, 32f / 256f, 32f / 256f));
		skillBarPack.add(selectSecondary, 0);

		RenderPart selectPrincipal = new RenderPart();
		selectPrincipal.setColor(Color.WHITE);
		selectPrincipal.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
		selectPrincipal.setSource(new Rectangle(32f / 256f, 224f / 256f, 32f / 256f, 32f / 256f));
		skillBarPack.add(selectPrincipal, 1);

		for (int j = 0; j < nbSlots; j++) {
			RenderPart cooldown = new RenderPart();
			cooldown.setColor(Color.WHITE);
			cooldown.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, 0));
			cooldown.setSource(new Rectangle(0, 224f / 256f, 32f / 256f, 32f / 256f));
			skillBarPack.add(cooldown, 2 + j);
			x += SKILL_OFFSET * SCALE;
		}
		x = -(nbSlots * SKILL_OFFSET * SCALE) / 2f;
		int i = nbSlots;
		for (Skill skill : skills) {
			RenderPart icon = new RenderPart();
			icon.setColor(Color.WHITE);
			icon.setSprite(new Rectangle(x, -0.95f, SKILL_SIZE * SCALE, SKILL_SIZE));
			icon.setSource(skill.getUv());
			skillBarPack.add(icon, 2 + i++);
			x += SKILL_OFFSET * SCALE;
		}
		
		skillBar.get(RenderPartsHolderComponent.class).add(skillBarPack);
		attachWidget(DungeonCreeper.getInstance(), skillBar);
	}
}
