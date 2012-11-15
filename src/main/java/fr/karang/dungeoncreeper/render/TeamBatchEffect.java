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
package fr.karang.dungeoncreeper.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.spout.api.geo.World;
import org.spout.api.model.mesh.OrientedMesh;
import org.spout.api.model.mesh.OrientedMeshFace;
import org.spout.api.model.mesh.Vertex;
import org.spout.api.render.BatchEffect;
import org.spout.api.render.SnapshotRender;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.player.Team.TeamColor;

public class TeamBatchEffect implements BatchEffect {

	TeamBatchEffect(){
	}

	public void preBatch(SnapshotRender snapshotRender) {

		// TODO : Modify the mesh for the good color
		/*
		 * 2 solutions :
		 * - Use a other mesh, a other uvs file by example
		 * - Make a new mesh with the given mesh, andchange the color
		 */
		if(snapshotRender.getSnapshotModel() != null && snapshotRender.getPosition() != null && snapshotRender.getMesh() instanceof OrientedMesh){
			World world = snapshotRender.getSnapshotModel().getCenter().getWorld();
			
			TeamColor teamColor = DungeonCreeper.getInstance().getLobby().getGame(world)
			.getTerritory(snapshotRender.getPosition().getFloorX(), snapshotRender.getPosition().getFloorZ());
			
			//Solution : Remplacement des couleurs
			/*Color color = new Color(teamColor.getColor());
			
			List<OrientedMeshFace> faces = new ArrayList<OrientedMeshFace>();
			for(OrientedMeshFace face : ((OrientedMesh)snapshotRender.getMesh())){
				Iterator<Vertex> it = face.iterator();
				
				Vertex v1 = new Vertex(it.next());
				Vertex v2 = new Vertex(it.next());
				Vertex v3 = new Vertex(it.next());
				
				v1.color = color;
				v2.color = color;
				v3.color = color;
				
				new OrientedMeshFace(v1, v2, v3, face.getSeeFromFace()); // TODO : Modify API
				faces.add(face);
			}
			
			snapshotRender.setMesh(new OrientedMesh(faces));*/
			
			//Solution : Remplacement du mesh directement
			//snapshotRender.setMesh(teamColor.getMesh(material);
		}
	}

	public void postBatch(SnapshotRender snapshotRender) {
	}

}
