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

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.math.Vector4;
import org.spout.api.render.effect.RenderEffect;
import org.spout.api.render.effect.SnapshotRender;

import fr.karang.dungeoncreeper.player.DungeonPlayer;
import fr.karang.dungeoncreeper.player.Team;

public class TeamEffect implements RenderEffect {
	private Player player;
	
	public TeamEffect() {
		player = ((Client) Spout.getEngine()).getActivePlayer();
		
	}
	public void preRender(SnapshotRender snap) {
		// TODO: Make this depends on the entity rendered.
		Team team = player.get(DungeonPlayer.class).getTeam();
		if (team==null) {
			snap.getMaterial().getShader().setUniform("teamColor", new Vector4(0.0, 0.0, 255.0, 255.0));
		} else {
			snap.getMaterial().getShader().setUniform("teamColor", team.getColor().getVector());
		}
	}

	public void postRender(SnapshotRender snapshotRender) { }

}
