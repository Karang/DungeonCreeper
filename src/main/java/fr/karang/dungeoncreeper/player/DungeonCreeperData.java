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

import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;

import fr.karang.dungeoncreeper.player.inventory.BileDemonInventory;
import fr.karang.dungeoncreeper.player.inventory.BlackKnightsInventory;
import fr.karang.dungeoncreeper.player.inventory.DarkAngelsInventory;
import fr.karang.dungeoncreeper.player.inventory.DarkElfInventory;
import fr.karang.dungeoncreeper.player.inventory.DarkMistressInventory;
import fr.karang.dungeoncreeper.player.inventory.FireflyInventory;
import fr.karang.dungeoncreeper.player.inventory.GobelinInventory;
import fr.karang.dungeoncreeper.player.inventory.HornedReaperInventory;
import fr.karang.dungeoncreeper.player.inventory.ImpInventory;
import fr.karang.dungeoncreeper.player.inventory.MaidenInventory;
import fr.karang.dungeoncreeper.player.inventory.RogueInventory;
import fr.karang.dungeoncreeper.player.inventory.SalamenderInventory;
import fr.karang.dungeoncreeper.player.inventory.SkeletonInventory;
import fr.karang.dungeoncreeper.player.inventory.TrollInventory;
import fr.karang.dungeoncreeper.player.inventory.VampireInventory;
import fr.karang.dungeoncreeper.player.inventory.WarlockInventory;

public class DungeonCreeperData {

	public static final DefaultedKey<BileDemonInventory> BileDemon_Inventory = new DefaultedKeyImpl<BileDemonInventory>("inventory", new BileDemonInventory());
	public static final DefaultedKey<BlackKnightsInventory> BlackKnights_Inventory = new DefaultedKeyImpl<BlackKnightsInventory>("inventory", new BlackKnightsInventory());
	public static final DefaultedKey<DarkAngelsInventory> DarkAngels_Inventory = new DefaultedKeyImpl<DarkAngelsInventory>("inventory", new DarkAngelsInventory());
	public static final DefaultedKey<DarkElfInventory> DarkElf_Inventory = new DefaultedKeyImpl<DarkElfInventory>("inventory", new DarkElfInventory());
	public static final DefaultedKey<DarkMistressInventory> DarkMistress_Inventory = new DefaultedKeyImpl<DarkMistressInventory>("inventory", new DarkMistressInventory());
	public static final DefaultedKey<FireflyInventory> Firefly_Inventory = new DefaultedKeyImpl<FireflyInventory>("inventory", new FireflyInventory());
	public static final DefaultedKey<GobelinInventory> Gobelin_Inventory = new DefaultedKeyImpl<GobelinInventory>("inventory", new GobelinInventory());
	public static final DefaultedKey<HornedReaperInventory> HornedReaper_Inventory = new DefaultedKeyImpl<HornedReaperInventory>("inventory", new HornedReaperInventory());
	public static final DefaultedKey<ImpInventory> Imp_Inventory = new DefaultedKeyImpl<ImpInventory>("inventory", new ImpInventory());
	public static final DefaultedKey<MaidenInventory> Maiden_Inventory = new DefaultedKeyImpl<MaidenInventory>("inventory", new MaidenInventory());
	public static final DefaultedKey<RogueInventory> Rogue_Inventory = new DefaultedKeyImpl<RogueInventory>("inventory", new RogueInventory());
	public static final DefaultedKey<SalamenderInventory> Salamender_Inventory = new DefaultedKeyImpl<SalamenderInventory>("inventory", new SalamenderInventory());
	public static final DefaultedKey<SkeletonInventory> Skeleton_Inventory = new DefaultedKeyImpl<SkeletonInventory>("inventory", new SkeletonInventory());
	public static final DefaultedKey<TrollInventory> Troll_Inventory = new DefaultedKeyImpl<TrollInventory>("inventory", new TrollInventory());
	public static final DefaultedKey<VampireInventory> Vampire_Inventory = new DefaultedKeyImpl<VampireInventory>("inventory", new VampireInventory());
	public static final DefaultedKey<WarlockInventory> Warlock_Inventory = new DefaultedKeyImpl<WarlockInventory>("inventory", new WarlockInventory());
	
	
}
