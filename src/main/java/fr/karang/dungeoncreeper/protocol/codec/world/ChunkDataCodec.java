package fr.karang.dungeoncreeper.protocol.codec.world;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

import fr.karang.dungeoncreeper.protocol.message.world.ChunkDataMessage;


public class ChunkDataCodec  extends MessageCodec<ChunkDataMessage>{

	public ChunkDataCodec() {
		super(ChunkDataMessage.class, 0x00);
	}
	
	@Override
	public ChunkDataMessage decode(ChannelBuffer buffer) throws IOException {
		int x = buffer.readInt();
		int z = buffer.readInt();
		byte[] blockType = new byte[256];
		byte[] blockData = new byte[256];
		byte[] lightType = new byte[256];
		byte[] lightIntensity = new byte[256];
		buffer.getBytes(8, blockType, 0, 256);
		return new ChunkDataMessage(x, z, blockType, blockData, lightType, lightIntensity);
	}

	@Override
	public ChannelBuffer encode(ChunkDataMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getX());
		buffer.writeInt(message.getZ());
		buffer.writeBytes(message.getBlockType());
		return buffer;
	}

}
