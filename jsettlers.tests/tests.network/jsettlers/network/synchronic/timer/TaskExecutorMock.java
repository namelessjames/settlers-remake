package jsettlers.network.synchronic.timer;

import java.util.LinkedList;
import java.util.List;

import jsettlers.network.client.task.packets.TaskPacket;
import jsettlers.network.synchronic.timer.ITaskExecutor;

/**
 * This class is a mock implementation of the interface {@link ITaskExecutor}, that buffers the received tasks.
 * 
 * @author Andreas Eberle
 * 
 */
public class TaskExecutorMock implements ITaskExecutor {

	private LinkedList<TaskPacket> buffer = new LinkedList<TaskPacket>();

	@Override
	public void executeTask(TaskPacket task) {
		buffer.add(task);
	}

	public List<TaskPacket> popBufferedPackets() {
		List<TaskPacket> temp = buffer;
		buffer = new LinkedList<TaskPacket>();
		return temp;
	}
}
