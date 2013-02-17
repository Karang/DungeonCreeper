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
package fr.karang.dungeoncreeper.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Transform;

import fr.karang.dungeoncreeper.component.world.PartyComponent;
import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.game.TeamResource;
import fr.karang.dungeoncreeper.game.TeamColor;
import fr.karang.dungeoncreeper.material.dungeon.DungeonResource;

public class HeartComponent extends EntityComponent {

	private PartyComponent party = null;
	
	private TeamColor color;
	
	private final Map<Class<? extends DungeonResource>, TeamResource> resources = new HashMap<Class<? extends DungeonResource>, TeamResource>();
	
	private List<Player> players = new ArrayList<Player>();
	private Transform spawn;
	private int gold = 0;
	
	public void setColor(TeamColor color){
		this.color = color;
	}
	
	public void setSpawn(Transform transform){
		spawn = transform;
	}

	public void respawnPlayers() {
		for (Player player : players) {
			player.teleport(spawn);
		}
	}

	public void playerJoin(Player player) {
		players.add(player);
		player.get(DungeonPlayer.class).setTeam(getOwner());
	}

	public void playerQuit(Player player) {
		players.remove(player);
		player.get(DungeonPlayer.class).setTeam(null);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public TeamColor getColor() {
		return color;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public PartyComponent getGame() {
		if(party == null)
			party = getOwner().getWorld().getComponentHolder().get(PartyComponent.class);
		return party;
	}

	public boolean hasResources(Class<? extends DungeonResource> type, int required) {
		TeamResource furniture = resources.get(type);
		return furniture != null && furniture.hasResources(required);
	}

	public void addResources(Class<? extends DungeonResource> type, int added) {
		TeamResource furniture = resources.get(type);

		if(furniture == null){
			furniture = new TeamResource(type);
			resources.put(type, furniture);
		}

		furniture.addResources(added);
	}

	public void removeResources(Class<? extends DungeonResource> type, int removed) {
		TeamResource furniture = resources.get(type);

		if(furniture == null || !furniture.hasResources(removed)){
			throw new IllegalStateException("This party don't have enough resources");
		}

		furniture.removeResources(removed);
	}
	
	public void useResources(Class<? extends DungeonResource> type, int use) {
		TeamResource furniture = resources.get(type);

		if(furniture == null){
			furniture = new TeamResource(type);
			resources.put(type, furniture);
		}

		furniture.useResources(use);
	}
	
	public void liberateResources(Class<? extends DungeonResource> type, int liberated) {
		TeamResource furniture = resources.get(type);

		if(furniture == null){
			furniture = new TeamResource(type);
			resources.put(type, furniture);
		}

		furniture.unuseResources(liberated);
	}

	@Override
	public void onAttached() {
		getData().put(DungeonData.HEALTH, 10);
		getData().put(DungeonData.MAX_HEALTH, 10);
	}

	public int getHealth(){
		return (int)getData().get(DungeonData.HEALTH);
	}

	public void setHealth(int health){
		if(health <= 0){
			//TODO : Kill and respawn
		}

		getData().put(DungeonData.HEALTH, health);
	}

	public void damage(int damage){
		int health = getHealth() - damage;

		if(health <= 0){
			//TODO : Kill and respawn
		}

		setHealth(health);
	}

	@Override
	public void onTick(float dt) {

	}
}
