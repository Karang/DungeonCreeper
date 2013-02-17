package fr.karang.dungeoncreeper.protocol.message.entity;

import org.spout.api.protocol.Message;

public class EntityUseSkillMessage  implements Message {

	private final int id;
	private final byte skill;
	
	public EntityUseSkillMessage(int id, byte skill) {
		this.id = id;
		this.skill = skill;
	}
	
	public int getEntityId() {
		return id;
	}
	
	public byte getSkillId() {
		return skill;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
