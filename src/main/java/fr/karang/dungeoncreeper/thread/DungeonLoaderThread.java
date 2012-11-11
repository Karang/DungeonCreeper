package fr.karang.dungeoncreeper.thread;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.spout.api.Spout;
import org.spout.api.geo.World;
import org.spout.api.math.IntVector3;

public class DungeonLoaderThread extends Thread {
	private final static AtomicInteger size = new AtomicInteger();
	private final static ConcurrentLinkedQueue<IntPoint3> queue = new ConcurrentLinkedQueue<IntPoint3>();
	private final int step;
	private final int total;

	public DungeonLoaderThread(int total, int step) {
		this.step = step;
		this.total = total;
	}

	public static void addChunk(World w, int x, int y, int z) {
		queue.add(new IntPoint3(w, x, y, z));
		size.incrementAndGet();
	}

	public void run() {
		boolean done = false;
		int remaining = size.get();
		while (!done) {
			IntPoint3 p = queue.poll();
			if (p == null) {
				done = true;
				continue;
			}
			remaining = size.decrementAndGet();
			if (remaining % step == 0) {
				Spout.getLogger().info("Generating [" + p.getWorld().getName() + "], " + (((total - remaining) * 100) / total) + "% complete");
			}
			p.getWorld().getChunk(p.getX(), p.getY(), p.getZ());
		}
	}

	private static class IntPoint3 extends IntVector3 {
		private final World w;

		public IntPoint3(World w, int x, int y, int z) {
			super(x, y, z);
			this.w = w;
		}

		public World getWorld() {
			return w;
		}
	}
}
