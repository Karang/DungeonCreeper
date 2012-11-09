package fr.karang.dungeoncreeper.world.populator;

import java.util.Random;

import org.spout.api.generator.Populator;
import org.spout.api.geo.cuboid.Chunk;

import fr.karang.dungeoncreeper.world.DungeonGenerator;

public class OrePopulator extends Populator {

	private static OreObject[] ORES = new OreObject[] {
		OreObject.GEM_ORE,
		OreObject.GOLD_ORE
	};
	
	@Override
	public void populate(Chunk chunk, Random random) {
		DungeonGenerator gen = (DungeonGenerator) chunk.getWorld().getGenerator();
		if (!gen.isChunkInDungeon(chunk.getX(), chunk.getY(), chunk.getZ())) {
			return;
		}
	}

}
