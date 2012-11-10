package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.protocol.Message;

import fr.karang.dungeoncreeper.player.skill.Skill;

public class PlayerSkillMessage implements Message {
	private int skillId;
	private boolean endCooldown;
	
	public PlayerSkillMessage(int skillId, boolean endCooldown) {
		this.skillId = skillId;
		this.endCooldown = endCooldown;
	}
	
	public PlayerSkillMessage(Skill skill) {
		this.skillId = skill.getId();
		this.endCooldown = false;
	}
	
	public int getSkill() {
		return skillId;
	}
	
	public boolean getEndCoolDown() {
		return endCooldown;
	}
}
