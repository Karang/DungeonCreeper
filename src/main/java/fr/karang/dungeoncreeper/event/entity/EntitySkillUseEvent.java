package fr.karang.dungeoncreeper.event.entity;

import org.spout.api.entity.Entity;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

import fr.karang.dungeoncreeper.player.skill.Skill;

public class EntitySkillUseEvent extends Event{
	private static HandlerList handlers = new HandlerList();
	private final Entity entity;
	private final Skill skill;

	public EntitySkillUseEvent(Entity entity, Skill skill) {
		this.entity = entity;
		this.skill = skill;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Entity getEntity() {
		return entity;
	}

	public Skill getSkill() {
		return skill;
	}
}
