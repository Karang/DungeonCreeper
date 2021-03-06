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
package fr.karang.dungeoncreeper.lighting;

import org.spout.api.lighting.Modifiable;
import org.spout.api.util.cuboid.CuboidNibbleLightBuffer;

public class DungeonCuboidLightBuffer extends CuboidNibbleLightBuffer {
	
	protected DungeonCuboidLightBuffer(DungeonCuboidLightBuffer buffer) {
		super(buffer);
	}

	protected DungeonCuboidLightBuffer(Modifiable holder, int id, int baseX, int baseY, int baseZ, int sizeX, int sizeY, int sizeZ) {
		super(holder, id, baseX, baseY, baseZ, sizeX, sizeY, sizeZ);
	}

	protected DungeonCuboidLightBuffer(Modifiable holder, int id, int baseX, int baseY, int baseZ, int sizeX, int sizeY, int sizeZ, byte[] data) {
		super(holder, id, baseX, baseY, baseZ, sizeX, sizeY, sizeZ, data);
	}
}
