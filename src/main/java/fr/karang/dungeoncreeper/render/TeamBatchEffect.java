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
