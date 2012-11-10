package fr.karang.dungeoncreeper.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.chat.ChatArguments;
import org.spout.api.command.Command;
import org.spout.api.exception.UnknownPacketException;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageCodec;
import org.spout.api.protocol.Protocol;
import org.spout.api.protocol.Session;

import fr.karang.dungeoncreeper.protocol.message.PlayerChatMessage;
import fr.karang.dungeoncreeper.protocol.message.PlayerKickMessage;

public class DungeonProtocol extends Protocol {

	public static final int defaultPort = 25565;
	
	public DungeonProtocol() {
		super("Dungeon", defaultPort, new DungeonCodecLookupService(), new DungeonHandlerLookupService());
	}

	@Override
	public MessageCodec<?> readHeader(ChannelBuffer buf) throws UnknownPacketException {
		int opcode = buf.readUnsignedByte();
		MessageCodec<?> codec = getCodecLookupService().find(opcode);
		if (codec == null) {
			throw new UnknownPacketException(opcode);
		}
		return codec;
	}

	@Override
	public ChannelBuffer writeHeader(MessageCodec<?> codec, ChannelBuffer data) {
		ChannelBuffer buffer = ChannelBuffers.buffer(1);
		buffer.writeByte(codec.getOpcode());
		return buffer;
	}

	@Override
	public Message getKickMessage(ChatArguments message) {
		return new PlayerKickMessage(message.toFormatString());
	}

	@Override
	public Message getCommandMessage(Command command, ChatArguments arguments) {
		if (command.getPreferredName().equals("kick")) {
			return getKickMessage(arguments);
		} else if (command.getPreferredName().equals("say")) {
			return new PlayerChatMessage(arguments.toFormatString());
		}
		return new PlayerChatMessage('/' + command.getPreferredName() + ' ' + arguments.toFormatString());
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
