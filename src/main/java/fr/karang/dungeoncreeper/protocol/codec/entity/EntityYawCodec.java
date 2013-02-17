package fr.karang.dungeoncreeper.protocol.codec.entity;

import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.entity.EntityYawMessage;

public class EntityYawCodec extends MessageCodec<EntityYawMessage> {

	public EntityYawCodec() {
		super(EntityYawMessage.class, 0x13);
	}

}
