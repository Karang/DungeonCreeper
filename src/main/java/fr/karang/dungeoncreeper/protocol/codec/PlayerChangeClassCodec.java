package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.PlayerChangeClassMessage;

public class PlayerChangeClassCodec extends MessageCodec<PlayerChangeClassMessage> {

	public PlayerChangeClassCodec() {
		super(PlayerChangeClassMessage.class, 0x0);
	}

	@Override
	public PlayerChangeClassMessage decode(ChannelBuffer buffer) throws IOException {
		String name = ChannelBufferUtils.readString(buffer);
		short classId = buffer.readShort();
		return new PlayerChangeClassMessage(name, classId);
	}

	@Override
	public ChannelBuffer encode(PlayerChangeClassMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		ChannelBufferUtils.writeString(buffer, message.getName());
		buffer.writeFloat(message.getClassId());
		return buffer;
	}
}
