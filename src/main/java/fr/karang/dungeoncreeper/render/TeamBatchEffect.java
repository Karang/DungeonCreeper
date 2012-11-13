package fr.karang.dungeoncreeper.render;

import org.spout.api.geo.World;
import org.spout.api.render.BatchEffect;
import org.spout.api.render.SnapshotRender;

import fr.karang.dungeoncreeper.DungeonCreeper;

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
		if(snapshotRender.getSnapshotModel() != null && snapshotRender.getPosition() != null){
			World world = snapshotRender.getSnapshotModel().getCenter().getWorld();
			DungeonCreeper.getInstance().getLobby().getGame(world)
			.getTerritory(snapshotRender.getPosition().getFloorX(), snapshotRender.getPosition().getFloorZ());
			//snapshotRender.getMesh();
			//snapshotRender.setMesh(mesh);
		}
	}

	public void postBatch(SnapshotRender snapshotRender) {
	}

}
