package fr.karang.dungeoncreeper.player.skill;

import java.util.HashMap;
import java.util.Map;

import org.spout.api.entity.Entity;

public abstract class Skill {
	
	private static Map<Integer, Skill> skills = new HashMap<Integer, Skill>();
	
	private final int id;
	private long cooldown;
	
	public Skill(int id) {
		this(id, 0L);
	}
	
	public Skill(int id, long cooldown) {
		this.id = id;
		this.cooldown = cooldown;
		skills.put(id, this);
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public long getCooldown() {
		return cooldown;
	}
	
	public int getId() {
		return id;
	}
	
	public static Skill getSkill(int skillId) {
		return skills.get(skillId);
	}
	
	public abstract void handle(Entity source);
}
