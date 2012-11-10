package fr.karang.dungeoncreeper.protocol.codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.ChannelBufferUtils;
import fr.karang.dungeoncreeper.protocol.message.WorldListMessage;

public class WorldListCodec extends MessageCodec<WorldListMessage> {

	public WorldListCodec() {
		super(WorldListMessage.class, 0x1);
	}

	@Override
	public WorldListMessage decode(ChannelBuffer buffer) throws IOException {
		String name = ChannelBufferUtils.readString(buffer);
		int online = buffer.readInt();
		int max = buffer.readInt();
		return new WorldListMessage(name, online, max);
	}

	@Override
	public ChannelBuffer encode(WorldListMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		ChannelBufferUtils.writeString(buffer, message.getName());
		buffer.writeInt(message.getOnlinePlayers());
		buffer.writeInt(message.getMaxPlayers());
		return buffer;
	}
	
}
