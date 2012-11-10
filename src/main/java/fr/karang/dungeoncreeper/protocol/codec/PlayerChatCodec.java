package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;

public class PlayerChatCodec extends MessageCodec<PlayerChatMessage> {

	public PlayerChatCodec() {
		super(PlayerChatMessage.class, 0x15);
	}

	@Override
	public PlayerChatMessage decode(ChannelBuffer buffer) throws IOException {
		String message = ChannelBufferUtils.readString(buffer);
		return new PlayerChatMessage(message);
	}

	@Override
	public ChannelBuffer encode(PlayerChatMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		ChannelBufferUtils.writeString(buffer, message.getMessage());
		return buffer;
	}
}
