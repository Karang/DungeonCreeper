package fr.karang.dungeoncreeper.protocol.message.world;

import org.spout.api.protocol.Message;

public class BlockPlaceMessage implements Message {

	private final int x;
	private final int z;
	private final byte type;
	private final byte data;
	
	public BlockPlaceMessage(int x, int z, byte type, byte data) {
		this.x = x;
		this.z = z;
		this.type = type;
		this.data = data;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public byte getType() {
		return type;
	}
	
	public byte getData() {
		return data;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
