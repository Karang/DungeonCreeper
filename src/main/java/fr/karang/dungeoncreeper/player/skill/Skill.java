package fr.karang.dungeoncreeper.player.skill;

import java.util.HashMap;
import java.util.Map;

public class Skill {
	
	private static Map<Integer, Skill> skills = new HashMap<Integer, Skill>();
	
	private final int id;
	private int cooldown = 0;
	
	public Skill(int id) {
		this.id = id;
		skills.put(id, this);
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public int getId() {
		return id;
	}
	
	public static Skill getSkill(int skillId) {
		return skills.get(skillId);
	}
}
