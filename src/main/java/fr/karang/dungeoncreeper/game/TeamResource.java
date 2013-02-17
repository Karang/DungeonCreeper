package fr.karang.dungeoncreeper.game;

import fr.karang.dungeoncreeper.material.dungeon.DungeonResource;

public class TeamResource {

	private final Class<? extends DungeonResource> type;
	private int owned = 0;
	private int used = 0;
	
	public TeamResource(Class<? extends DungeonResource> type){
		this.type = type;
	}

	public void addResources(int added) {
		owned += added;
	}
	
	public void removeResources(int removed) {
		if(owned < removed)
			throw new IllegalStateException("Not enough furniture");
		owned -= removed;
	}
	
	public boolean hasResources(int count){
		return owned - used >= count;
	}

	public void useResources(int count) {
		if(used + count > owned)
			throw new IllegalStateException("Not enough non-used furniture");
		used += count;
	}
	
	public void unuseResources(int count) {
		if(used < count)
			throw new IllegalStateException("Not enough used furniture");
		used -= count;
	}

	public Class<? extends DungeonResource> getType() {
		return type;
	}
	
}
