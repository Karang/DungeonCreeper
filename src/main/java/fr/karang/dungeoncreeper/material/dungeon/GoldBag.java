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
package fr.karang.dungeoncreeper.material.dungeon;

import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.material.DCMaterials;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.player.PlayerInteractEvent.Action;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.material.block.BlockFace;
import org.spout.api.model.Model;
import org.spout.api.resource.ResourcePointer;

public class GoldBag extends DCMaterial {
	public GoldBag() {
		super("Gold Bag", new ResourcePointer<Model>("model://DungeonCreeper/resources/block/dungeon/goldBag/goldBag.spm"));
		
	}

	@Override
	public void onInteractBy(Entity entity, Block block, Action type, BlockFace clickedFace) {
		if (type != Action.LEFT_CLICK && type != Action.RIGHT_CLICK) {
			return;
		}

		if (entity instanceof Player) {
			if (entity.has(Imp.class)) {
				//TODO: check the player's team
				//TODO: get the real amount of gold
				//entity.get(Imp.class).addGold(5); Replace by a item added in the imp inventory
				block.setMaterial(DCMaterials.AIR);
			} else {
				((Player) entity).sendMessage(ChatStyle.RED, "Only imps can grab gold bags.");
			}
		}
	}
}
