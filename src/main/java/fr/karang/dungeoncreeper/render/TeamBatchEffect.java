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

import org.spout.api.geo.World;
import org.spout.api.model.mesh.OrientedMesh;
import org.spout.api.render.BatchEffect;
import org.spout.api.render.SnapshotRender;

import fr.karang.dungeoncreeper.DungeonCreeper;
import fr.karang.dungeoncreeper.player.Team.TeamColor;

public class TeamBatchEffect implements BatchEffect {

	public void preBatch(SnapshotRender snapshotRender) {
		if(snapshotRender.getSnapshotModel() != null && snapshotRender.getPosition() != null && snapshotRender.getMesh() instanceof OrientedMesh){
			World world = snapshotRender.getSnapshotModel().getCenter().getWorld();
			
			TeamColor teamColor = DungeonCreeper.getInstance().getLobby().getGame(world)
			.getTerritory(snapshotRender.getPosition().getFloorX(), snapshotRender.getPosition().getFloorZ());

			snapshotRender.setMesh(teamColor.getTeamMesh(snapshotRender.getMaterial()));
		}
	}

	public void postBatch(SnapshotRender snapshotRender) {
	}

}
