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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.LabelComponent;
import org.spout.api.gui.component.RenderPartsHolderComponent;
import org.spout.api.gui.render.RenderPart;
import org.spout.api.math.Rectangle;
import org.spout.api.plugin.Platform;
import org.spout.api.render.Font;
import org.spout.api.render.RenderMaterial;

import fr.karang.dungeoncreeper.DungeonCreeper;

public class LobbyScreen extends Screen {
	private static final float SCALE = 0.75f; // TODO: Apply directly from engine
	private static final Color GUI_BROWN = new Color(110, 70, 0, 200);
	private static final Color GUI_GREEN = new Color(100, 160, 0, 200);
	//private static final Color GUI_GREY = new Color(128, 128, 128, 200);
	private final RenderMaterial colorMaterial = (RenderMaterial) Spout.getFilesystem().getResource("material://Spout/resources/resources/materials/GUIColorMaterial.smt");
	private final Font FONT = (Font) Spout.getFilesystem().getResource("font://DungeonCreeper/resources/gui/DKFont.ttf");
	private Widget players = new Widget();
	private List<Widget> games = new ArrayList<Widget>();
	private Widget[][] gamesTab = new Widget[5][3];
	private float gameListOffset = 0.9f;
	
	public LobbyScreen(Lobby lobby) {
		if (Spout.getPlatform()!=Platform.CLIENT) {
			throw new IllegalStateException("Only clients can have a lobby screen.");
		}
		
		this.setTakesInput(false);
		
		newTextBox(new ChatArguments("Dungeon's name"), GUI_BROWN, -0.95f, 0.5f, 0.3f, 0.05f);
		newTextBox(new ChatArguments("Online players"), GUI_BROWN, -0.64f, 0.5f, 0.3f, 0.05f);
		newTextBox(new ChatArguments("Maximum players"), GUI_BROWN, -0.33f, 0.5f, 0.3f, 0.05f);
		
		for (int x=0 ; x<3 ; x++) {
			for (int y=0 ; y<5 ; y++) {
				Widget box = newTextBox(new ChatArguments(""), GUI_GREEN, -0.95f+0.31f*x, 0.44f-0.06f*y, 0.3f, 0.05f);
				gamesTab[y][x] = box;
			}
		}
		
		players.setGeometry(new Rectangle(0,0,0,0));
		LabelComponent playerList = players.add(LabelComponent.class);
		playerList.setFont(FONT);
		playerList.setText(new ChatArguments(ChatStyle.YELLOW, "Online players: "));
		attachWidget(DungeonCreeper.getInstance(), players);
	}
	
	public Widget newTextBox(ChatArguments label, Color bg, float x, float y, float w, float h) {
		final Widget box = new Widget();
		final RenderPartsHolderComponent titlerect = box.add(RenderPartsHolderComponent.class);
		RenderPart rect = new RenderPart();
		rect.setRenderMaterial(colorMaterial);
		rect.setColor(bg);
		rect.setSprite(new Rectangle(x*SCALE, y, w*SCALE, h));
		rect.setSource(new Rectangle(0, 0, 0, 0));
		titlerect.add(rect);
		box.setGeometry(new Rectangle(x*SCALE, y, 0, 0));
		final LabelComponent title = box.add(LabelComponent.class);
		title.setFont(FONT);
		title.setText(label);
		attachWidget(DungeonCreeper.getInstance(), box);
		return box;
	}
	
	public void addPlayer(String name) {
		System.out.println("Player list: " + name);
		LabelComponent playerList = players.get(LabelComponent.class);
		playerList.setText(playerList.getText().append(name, ", "));
	}
	
	public void addGame(ChatArguments title) {
		System.out.println("World list: " + title);
		Widget game = new Widget();
		game.setGeometry(new Rectangle(0, gameListOffset, 0, 0));
		LabelComponent txt = game.add(LabelComponent.class);
		txt.setFont(FONT);
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
