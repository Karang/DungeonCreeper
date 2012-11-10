package fr.karang.dungeoncreeper.player.equipment;

import java.util.Map;
import java.util.HashMap;

import org.spout.api.component.components.EntityComponent;
import org.spout.api.inventory.Inventory;

import fr.karang.dungeoncreeper.data.DungeonData;
import fr.karang.dungeoncreeper.player.skill.Skill;

public abstract class CreatureComponent extends EntityComponent {

	private Map<Class<? extends Skill>, Skill> skills = new HashMap<Class<? extends Skill>, Skill>();
	
	@Override
	public void onAttached() {
		getData().put(DungeonData.HEALTH, 10);
		getData().put(DungeonData.MAX_HEALTH, 10);
		getData().put(DungeonData.DAMAGES, 0);
		getData().put(DungeonData.SKILLSLOT, 0);
	}
	
	public abstract Inventory getInventory();

	public void addSkill(Skill skill) {
		skills.put(skill.getClass(), skill);
	}
	
	public boolean hasSkill(Class<? extends Skill> skill) {
		return skills.containsKey(skill);
	}
	
	public Skill getSkill(Class<? extends Skill> skill) {
		return skills.get(skill);
	}
	
	public void setSlot(int slot){
		getData().put(DungeonData.SKILLSLOT, slot);
	}
}
