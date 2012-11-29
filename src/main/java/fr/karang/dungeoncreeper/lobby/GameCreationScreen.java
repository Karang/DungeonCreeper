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
package fr.karang.dungeoncreeper.lobby;

import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.DungeonCreeper;

import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;
import org.spout.api.gui.component.LabelComponent;
import org.spout.api.math.Rectangle;
import org.spout.api.render.Font;

public class GameCreationScreen extends Screen {
	private static final float SCALE = 0.75f;
	private static final float OPTW = 0.3f * SCALE;
	private final Widget backButton = new Widget();
	private final Widget worldPreview = new Widget();
	private final Map<String, OptionWidget> options = new HashMap<String, OptionWidget>();
	private final Lobby lobby;
	private final DungeonCreeper plugin;

	public GameCreationScreen(Lobby lobby) {
		this.lobby = lobby;
		this.plugin = DungeonCreeper.getInstance();

		createOption("World", "world1", "world2", "world3").setPos(-0.5f * SCALE, 0.1f);
		createOption("Teams", "2", "3", "4").setPos(-0.5f * SCALE, 0f);
		createOption("Max players", "4", "8", "16", "24", "32").setPos(-0.5f * SCALE, -0.1f);
	}

	private OptionWidget createOption(String label, String... values) {
		OptionWidget opt = new OptionWidget(label, values);
		opt.attach(this, plugin);
		options.put(label, opt);
		return opt;
	}

	private static class OptionWidget {
		private final Font FONT = (Font) Spout.getFilesystem().getResource("font://DungeonCreeper/resources/gui/DKFont.ttf");
		private Widget title = new Widget();
		private Widget value = new Widget();
		private String[] values;
		private int i = 0;

		public OptionWidget(String label, String... values) {
			this.values = values;
			LabelComponent txtTitle = title.add(LabelComponent.class);
			txtTitle.setFont(FONT);
			txtTitle.setText(new ChatArguments(label, ":"));

			LabelComponent txtValue = value.add(LabelComponent.class);
			txtValue.setFont(FONT);
			txtValue.setText(new ChatArguments(values[i]));
		}

		public void setPos(float x, float y) {
			title.setGeometry(new Rectangle(x, y, 0, 0));
			value.setGeometry(new Rectangle(x + OPTW, y, 0, 0));
		}

		public void attach(Screen screen, DungeonCreeper plugin) {
			screen.attachWidget(plugin, title);
			screen.attachWidget(plugin, value);
		}

		public void next() {
			i++;
			if (i >= values.length) {
				i = 0;
			}
			LabelComponent txtValue = value.get(LabelComponent.class);
			txtValue.setText(new ChatArguments(values[i]));
		}

		public void previous() {
			i--;
			if (i < 0) {
				i = values.length - 1;
			}
			LabelComponent txtValue = value.get(LabelComponent.class);
			txtValue.setText(new ChatArguments(values[i]));
		}
	}
}
