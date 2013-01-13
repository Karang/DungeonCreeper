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
package fr.karang.dungeoncreeper.render;

import gnu.trove.list.array.TFloatArrayList;

import org.spout.api.geo.cuboid.ChunkSnapshot;
import org.spout.api.geo.cuboid.ChunkSnapshotModel;
import org.spout.api.material.BlockMaterial;
import org.spout.api.render.BufferContainer;
import org.spout.api.render.effect.BufferEffect;

public class LightBufferEffect implements BufferEffect {

	public void post(ChunkSnapshotModel chunkModel, BufferContainer container) {
		TFloatArrayList vertexBuffer = (TFloatArrayList) container.getBuffers().get(0);

		/*
		 * Use a shader light (2) and skylight (4)
		 * 
		 * WE NEED TO USE 2 BECAUSE WE DON'T USE COLOR
		 * OPENGL 2 NEED TO USE LAYOUT IN THE ORDER
		 * WE CAN'T USE 3 IF 2 ISN'T USED
		 * 
		 * One float per vertice
		 * file://Vanilla/shaders/terrain.120.vert 
		 * file://Vanilla/shaders/terrain.330.vert
		 */

		TFloatArrayList lavaLightBuffer = (TFloatArrayList) container.getBuffers().get(1);
		TFloatArrayList waterLightBuffer = (TFloatArrayList) container.getBuffers().get(4);

		if (lavaLightBuffer == null) {
			lavaLightBuffer = new TFloatArrayList(vertexBuffer.size() / 4);
			container.setBuffers(1, lavaLightBuffer);
		}

		if (waterLightBuffer == null) {
			waterLightBuffer = new TFloatArrayList(vertexBuffer.size() / 4);
			container.setBuffers(4, waterLightBuffer);
		}

		for (int i = 0; i < vertexBuffer.size(); ) {
			float x = vertexBuffer.get(i++);
			float y = vertexBuffer.get(i++);
			float z = vertexBuffer.get(i++);
			i++; // w component

			//TODO : Create a buffer for each light registred by plugin

			generateLightOnVertices(chunkModel, x, y, z, lavaLightBuffer, waterLightBuffer);
		}
	}

	/**
	 * Compute the light for one vertex
	 * @param chunkModel
	 * @param x
	 * @param y
	 * @param z
	 * @param lightBuffer
	 */
	private void generateLightOnVertices(ChunkSnapshotModel chunkModel, float x, float y, float z, TFloatArrayList lavaLightBuffer, TFloatArrayList waterLightBuffer) {
		int xi = (int) x;
		int yi = (int) y;
		int zi = (int) z;
		if (chunkModel != null) {
			float lavaLight = 0;
			float waterLight = 0;
			int count = 0;

			int xs = (x == xi) ? (xi - 1) : xi;
			int ys = (y == yi) ? (yi - 1) : yi;
			int zs = (z == zi) ? (zi - 1) : zi;

			for (int xx = xs; xx <= xi; xx++) {
				for (int yy = ys; yy <= yi; yy++) {
					for (int zz = zs; zz <= zi; zz++) {
						ChunkSnapshot chunk = chunkModel.getChunkFromBlock(xx, yy, zz);
						BlockMaterial m = chunk.getBlockMaterial(xx, yy, zz);
						if (!m.isOpaque()) {
							lavaLight += chunk.getBlockLight(xx, yy, zz);
							waterLight += chunk.getBlockLight(xx, yy, zz);
							count++;
						}
					}
				}
			}

			if (count == 0) {
				count++;
			}

			lavaLight /= count;
			waterLight /= count;
			lavaLight /= 16;
			waterLight /= 16;

			//TODO : To replace by 2 byte buffer for Vanilla

			lavaLightBuffer.add(lavaLight);
			waterLightBuffer.add(waterLight);
		} else {
			lavaLightBuffer.add(1f);
			waterLightBuffer.add(1f);
		}
	}

}
