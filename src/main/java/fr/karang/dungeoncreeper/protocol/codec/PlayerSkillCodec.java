package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.PlayerSkillMessage;

public class PlayerSkillCodec extends MessageCodec<PlayerSkillMessage> {

	public PlayerSkillCodec() {
		super(PlayerSkillMessage.class, 0x13);
	}

	@Override
	public PlayerSkillMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readInt();
		boolean endCooldown = buffer.readByte() != 0;
		return new PlayerSkillMessage(id, endCooldown);
	}

	@Override
	public ChannelBuffer encode(PlayerSkillMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getSkill());
		buffer.writeByte(message.getEndCoolDown() ? 1 : 0);
		return buffer;
	}
}
