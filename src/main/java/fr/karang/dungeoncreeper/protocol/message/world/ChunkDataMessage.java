package fr.karang.dungeoncreeper.protocol.message.world;

import org.spout.api.protocol.Message;

public class ChunkDataMessage implements Message {
	private final int chunkX;
	private final int chunkZ;
	private final byte[] blockType;
	private final byte[] blockData;
	private final byte[] lightType;
	private final byte[] lightIntensity;
	
	public ChunkDataMessage(int x, int z, byte[] blockType, byte[] blockData, byte[] lightType, byte[] lightIntensity) {
		this.chunkX = x;
		this.chunkZ = z;
		this.blockType = blockType;
		this.blockData = blockData;
		this.lightType = lightType;
		this.lightIntensity = lightIntensity;
	}
	
	public int getX() {
		return chunkX;
	}
	
	public int getZ() {
		return chunkZ;
	}
	
	public byte[] getBlockType() {
		return blockType;
	}
	
	public byte[] getBlockData() {
		return blockData;
	}
	
	public byte[] getLightType() {
		return lightType;
	}
	
	public byte[] getLightIntensity() {
		return lightIntensity;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
