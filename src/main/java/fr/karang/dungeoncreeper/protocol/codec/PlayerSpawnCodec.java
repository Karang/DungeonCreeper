package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.PlayerSpawnMessage;

public class PlayerSpawnCodec extends MessageCodec<PlayerSpawnMessage>{
	public PlayerSpawnCodec() {
		super(PlayerSpawnMessage.class, 0x10);
	}

	@Override
	public PlayerSpawnMessage decode(ChannelBuffer buffer) throws IOException {
		String name = ChannelBufferUtils.readString(buffer);
		float x = buffer.readFloat();
		float y = buffer.readFloat();
		float z = buffer.readFloat();
		return new PlayerSpawnMessage(name, x, y, z);
	}

	@Override
	public ChannelBuffer encode(PlayerSpawnMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		ChannelBufferUtils.writeString(buffer, message.getName());
		buffer.writeFloat(message.getX());
		buffer.writeFloat(message.getY());
		buffer.writeFloat(message.getZ());
		return buffer;
	}
}
