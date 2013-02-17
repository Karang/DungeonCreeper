package fr.karang.dungeoncreeper.protocol.codec.entity;

import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityUnderSkillMessage;

public class EntityUnderSkillCodec extends MessageCodec<EntityUnderSkillMessage> {

	public EntityUnderSkillCodec() {
		super(EntityUnderSkillMessage.class, 0x15);
	}

}
