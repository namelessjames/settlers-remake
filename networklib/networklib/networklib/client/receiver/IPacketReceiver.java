package networklib.client.receiver;

import networklib.client.NetworkClient;
import networklib.infrastructure.channel.packet.Packet;

/**
 * This interface defines a receiver for packets send by the server and received by the {@link NetworkClient}.
 * 
 * @author Andreas Eberle
 */
public interface IPacketReceiver<T extends Packet> {

	void receivePacket(T packet);
}