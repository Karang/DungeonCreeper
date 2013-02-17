package fr.karang.dungeoncreeper.protocol.message.world;

import org.spout.api.protocol.Message;

public class BlockBreakMessage implements Message {
	
	private int x;
	private int z;
	
	public BlockBreakMessage(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public boolean isAsync() {
		return false;
	}

	public int getChannelId() {
		return 0;
	}

}
