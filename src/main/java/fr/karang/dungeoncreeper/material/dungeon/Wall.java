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
package fr.karang.dungeoncreeper.material.dungeon;

import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.ChunkSnapshot;
import org.spout.api.math.Vector3;
import org.spout.api.model.mesh.Mesh;
import org.spout.api.render.effect.MeshEffect;
import org.spout.api.render.effect.SnapshotMesh;

import fr.karang.dungeoncreeper.material.DCMaterial;
import fr.karang.dungeoncreeper.player.Team.TeamColor;


public class Wall extends DungeonMaterial {

	public Wall() {
		super("Wall", "model://DungeonCreeper/resources/block/dungeon/wall/wall.spm");
		addMeshEffect(new MeshEffect() {
			public void preMesh(SnapshotMesh snapshotMesh) {
				World world = snapshotMesh.getPosition().getWorld();
				Vector3 v = snapshotMesh.getPosition();
				
				ChunkSnapshot c = snapshotMesh.getSnapshotModel().getChunkFromBlock(v.getFloorX(), v.getFloorY(), v.getFloorZ());
				DCMaterial material = (DCMaterial) c.getBlockMaterial(v.getFloorX(), v.getFloorY(), v.getFloorZ());
				TeamColor team = material.getOwner(world, v.getFloorX(), v.getFloorZ());
				
				if (team != null) {
					Mesh mesh = team.getTeamMesh(material);
					if (mesh != null) {
						snapshotMesh.setMesh(mesh);
					}
				}
			}

			public void postMesh(SnapshotMesh snapshotMesh) {
				
			}
		});
	}

}
