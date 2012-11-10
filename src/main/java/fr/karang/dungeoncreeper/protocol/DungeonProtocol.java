package fr.karang.dungeoncreeper.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.spout.api.chat.ChatArguments;
import org.spout.api.command.Command;
import org.spout.api.exception.UnknownPacketException;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageCodec;
import org.spout.api.protocol.Protocol;
import org.spout.api.protocol.Session;

public class DungeonProtocol extends Protocol {

	public static final int defaultPort = 25565;
	
	public DungeonProtocol() {
		super("Dungeon", defaultPort, new DungeonCodecLookupService(), new DungeonHandlerLookupService());
	}

	@Override
	public MessageCodec<?> readHeader(ChannelBuffer buf) throws UnknownPacketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelBuffer writeHeader(MessageCodec<?> codec, ChannelBuffer data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getKickMessage(ChatArguments message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getCommandMessage(Command command, ChatArguments arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getIntroductionMessage(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeSession(Session session) {
		// TODO Auto-generated method stub
		
	}

}
