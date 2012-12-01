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

import org.spout.api.Spout;
import org.spout.api.render.Font;
import org.spout.api.render.RenderMaterial;

public class DungeonResources {
	public static final Font FONT = (Font) Spout.getFilesystem().getResource("font://DungeonCreeper/resources/gui/DKFont.ttf");

	// GUI
	public static final RenderMaterial COLOR_MAT = (RenderMaterial) Spout.getFilesystem().getResource("material://DungeonCreeper/resources/gui/GUIRoundedRect.smt");
	public static final RenderMaterial SKILL_MAT = (RenderMaterial) Spout.getFilesystem().getResource("material://DungeonCreeper/resources/gui/skillMaterial.smt");
	public static final RenderMaterial CROSSHAIR_MAT = (RenderMaterial) Spout.getFilesystem().getResource("material://DungeonCreeper/resources/gui/crosshair.smt");
	
	// Terrain
	public static final RenderMaterial TERRAIN_MAT = (RenderMaterial) Spout.getFilesystem().getResource("material://DungeonCreeper/resources/terrain.smt");
	
	public static void init() {
		TERRAIN_MAT.addRenderEffect(RenderEffects.BUMP);
		CROSSHAIR_MAT.addRenderEffect(RenderEffects.GUI_CAST);
	}
}
