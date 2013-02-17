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

import org.spout.api.collision.CollisionStrategy;

public class DungeonHeart extends DungeonMaterial {
	public static DungeonHeartPart N;
	public static DungeonHeartPart S;
	public static DungeonHeartPart E;
	public static DungeonHeartPart W;
	public static DungeonHeartPart NE;
	public static DungeonHeartPart NW;
	public static DungeonHeartPart SE;
	public static DungeonHeartPart SW;
	
	static {
		N = new DungeonHeartPart("N");
		S = new DungeonHeartPart("S");
		E = new DungeonHeartPart("E");
		W = new DungeonHeartPart("W");
		NE = new DungeonHeartPart("NE");
		NW = new DungeonHeartPart("NW");
		SE = new DungeonHeartPart("SE");
		SW = new DungeonHeartPart("SW");
	}
	
	public DungeonHeart() {
		super("Dungeon Heart", "model://DungeonCreeper/resources/block/dungeon/dungeonheart/dungeonheart.spm");
		setCollision(CollisionStrategy.SOLID);
		this.setTransparent();
	}

	@Override
	public byte getLightLevel(short data) {
		return 5;
	}
}
