package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.PlayerKickMessage;

public class PlayerKickCodec extends MessageCodec<PlayerKickMessage> {

	public PlayerKickCodec() {
		super(PlayerKickMessage.class, 0x16);
	}
	
	@Override
	public PlayerKickMessage decode(ChannelBuffer buffer) throws IOException {
		String reason = ChannelBufferUtils.readString(buffer);
		return new PlayerKickMessage(reason);
	}

	@Override
	public ChannelBuffer encode(PlayerKickMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		ChannelBufferUtils.writeString(buffer, message.getReason());
		return buffer;
	}
}
