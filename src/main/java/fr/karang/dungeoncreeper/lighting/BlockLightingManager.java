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

import java.util.LinkedList;
import java.util.Queue;

import org.spout.api.lighting.LightingManager;
import org.spout.api.lighting.Modifiable;
import org.spout.api.material.BlockMaterial;
import org.spout.api.material.block.BlockFace;
import org.spout.api.material.block.BlockFaces;
import org.spout.api.util.cuboid.ChunkCuboidLightBufferWrapper;
import org.spout.api.util.cuboid.ImmutableCuboidBlockMaterialBuffer;

public class BlockLightingManager extends LightingManager<DungeonCuboidLightBuffer> {
	public BlockLightingManager(String name) {
		super(name);
	}

	class Element{
		private final int x,y,z;
		private final BlockFace next;
		private final boolean increase;

		public Element(int x, int y, int z, BlockFace next, boolean increase){
			this.x = x;
			this.y = y;
			this.z = z;
			this.next = next;
			this.increase = increase;
		}

		public int getX(){
			return x;
		}

		public int getY(){
			return y;
		}

		public int getZ(){
			return z;
		}

		public BlockFace getNext(){
			return next;
		}

		public boolean increase() {
			return increase;
		}
	}

	/**
	 * Recalculates lighting after a change to the block materials at a given set of block coordinates.
	 */
	@Override
	protected void resolve(ChunkCuboidLightBufferWrapper<DungeonCuboidLightBuffer> light, ImmutableCuboidBlockMaterialBuffer material, int[] x, int[] y, int[] z, int changedBlocks) {

		Queue<Element> queue = new LinkedList<BlockLightingManager.Element>();

		for(int i = 0; i < changedBlocks; i++){
			int xx = x[i];
			int yy = y[i];
			int zz = z[i];

			BlockMaterial blockMaterial = material.get(xx, yy, zz);
			//boolean occlusion = blockMaterial.getOcclusion(blockMaterial.getData()).get(BlockFaces.NESWBT);
			byte lightValue = blockMaterial.getLightLevel(blockMaterial.getData());
			
			DungeonCuboidLightBuffer currentLightBuffer = light.getLightBuffer(xx, yy, zz);
			
			if(currentLightBuffer == null)
				continue;
			
			byte currentValue = currentLightBuffer.get(xx, yy, zz);

			if(lightValue > currentValue){
				currentLightBuffer.set(xx, yy, zz, lightValue);

				for(BlockFace face : BlockFaces.NESWBT){
					queue.add(new Element(xx, yy, zz, face, true));
				}
			}else if(lightValue < currentValue){
				boolean requireUpdate = true;
				byte minLight = 0;
				
				for(BlockFace face : BlockFaces.NESWBT){
					int nextX = xx + face.getOffset().getFloorX();
					int nextY = yy + face.getOffset().getFloorY();
					int nextZ = zz + face.getOffset().getFloorZ();
					
					byte nextLight = light.getLightBuffer( nextX, nextY, nextZ).get( nextX, nextY, nextZ);
					
					if(nextLight + 1 == currentValue){
						requireUpdate = false;
						break;
					}
					
					if(nextLight + 1 < minLight)
						minLight = (byte)(nextLight + 1);
					
				}

				if(requireUpdate){
					currentLightBuffer.set(xx, yy, zz, minLight);
					
					for(BlockFace face : BlockFaces.NESWBT){
						queue.add(new Element(xx, yy, zz, face, true));
					}
				}
			}
		}

		while(!queue.isEmpty()){
			Element element = queue.poll();

			if(element.increase()){
				int sourceX = element.getX();
				int sourceY = element.getY();
				int sourceZ = element.getZ();

				int currentX = sourceX + element.getNext().getOffset().getFloorX();
				int currentY = sourceY + element.getNext().getOffset().getFloorY();
				int currentZ = sourceZ + element.getNext().getOffset().getFloorZ();

				byte sourceLight = (byte) (light.getLightBuffer(sourceX,sourceY,sourceZ).get(sourceX,sourceY,sourceZ) - 1);
				
				DungeonCuboidLightBuffer currentLightBuffer = light.getLightBuffer(currentX,currentY,currentZ);
				
				if(currentLightBuffer == null)
					continue;
				
				byte currentLight = currentLightBuffer.get(currentX,currentY,currentZ);

				if(sourceLight >= 0 && sourceLight > currentLight){
					currentLightBuffer.set(currentX,currentY,currentZ,sourceLight);
					for(BlockFace face : BlockFaces.NESWBT){
						if(!face.equals(element.getNext().getOpposite()))
							queue.add(new Element(currentX, currentY, currentZ, face, true));
					}
				}
			}else{

			}
		}

	}

	/**
	 * Recalculates lighting after a change to the block materials at a given set of cuboid regions.
	 */
	@Override
	protected void resolve(ChunkCuboidLightBufferWrapper<DungeonCuboidLightBuffer> light, ImmutableCuboidBlockMaterialBuffer material, int[] bx, int[] by, int[] bz, int[] tx, int[] ty, int[] tz, int changedCuboids) {

	}

	/**
	 * Recalculates lighting after a change to the world surface height.
	 */
	@Override
	protected void resolve(ChunkCuboidLightBufferWrapper<DungeonCuboidLightBuffer> light, ImmutableCuboidBlockMaterialBuffer material, int[] hx, int[] hz, int[] oldHy, int[] newHy, int changedColumns) {
		// Shouldn't be happening
	}

	@Override
	public DungeonCuboidLightBuffer deserialize(Modifiable holder, int baseX, int baseY, int baseZ, int sizeX, int sizeY, int sizeZ, byte[] data) {
		return new DungeonCuboidLightBuffer(holder, getId(), baseX, baseY, baseZ, sizeX, sizeY, sizeZ, data);
	}

}
