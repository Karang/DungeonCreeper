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
package fr.karang.dungeoncreeper.lobby;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.LabelComponent;
import org.spout.api.math.Rectangle;
import org.spout.api.plugin.Platform;

import fr.karang.dungeoncreeper.DungeonCreeper;

public class LobbyScreen extends Screen {
	
	private List<Widget> games = new ArrayList<Widget>();
	private float gameListOffset = 0.9f;
	
	public LobbyScreen(Lobby lobby) {
		if (Spout.getPlatform()!=Platform.CLIENT) {
			throw new IllegalStateException("Only clients can have a lobby screen.");
		}
		
		this.setTakesInput(false);
		//TODO: Player list, world list
	}
	
	public void addGame(ChatArguments title) {
		Widget game = new Widget();
		game.setGeometry(new Rectangle(0, gameListOffset, 0, 0));
		LabelComponent txt = game.add(LabelComponent.class);
		txt.setText(title);
		games.add(game);
		attachWidget(DungeonCreeper.getInstance(), game);
		gameListOffset -= 0.1f;
	}
	
	public void refresh() {
		for (Widget game : games) {
			removeWidget(game);
		}
		games.clear();
		gameListOffset = 0.9f;
	}
}
