package fr.karang.dungeoncreeper.protocol.codec.entity;

import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityUseSkillMessage;

public class EntityUseSkillCodec extends MessageCodec<EntityUseSkillMessage> {

	public EntityUseSkillCodec() {
		super(EntityUseSkillMessage.class, 0x14);
	}

}
