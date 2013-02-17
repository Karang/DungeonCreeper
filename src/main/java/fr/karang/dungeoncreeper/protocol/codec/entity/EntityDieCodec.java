package fr.karang.dungeoncreeper.protocol.codec.entity;

import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityDieMessage;

public class EntityDieCodec extends MessageCodec<EntityDieMessage> {

	public EntityDieCodec() {
		super(EntityDieMessage.class, 0x16);
	}

}
