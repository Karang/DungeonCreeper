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
package fr.karang.dungeoncreeper.world.populator;

import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.World;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class PortalRoomObject extends WorldGeneratorObject{

	private static int WIDTH = 1;
	private static int HEIGHT = 4;
	
	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return true;
	}

	@Override
	public void placeObject(World w, int x, int y, int z) {
		// Make place
		fill(w, x-WIDTH-1, y+1, z-1, x+WIDTH, y+HEIGHT, z+1, DCMaterials.AIR);
		fill(w, x-WIDTH-1, y, z-1, x+WIDTH, y, z+1, DCMaterials.UNCLAIMED_FLOOR);
		
		// Make portal // TODO: make proper materials
		fill(w, x-WIDTH, y, z, x+WIDTH, y+HEIGHT-1, z, DCMaterials.DIRT);
		fill(w, x-WIDTH+1, y+1, z, x+WIDTH-1, y+HEIGHT-2, z, DCMaterials.AIR);
	}

	private void fill(World w, int x1, int y1, int z1, int x2, int y2, int z2, BlockMaterial material) {
		for (int x=x1 ; x<=x2 ; x++) {
			for (int y=y1 ; y<=y2 ; y++) {
				for (int z=z1 ; z<=z2 ; z++) {
					w.getBlock(x, y, z).setMaterial(material);
				}
			}
		}
	}
}
