package fr.karang.dungeoncreeper.world.populator;

import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.World;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class OreObject extends WorldGeneratorObject {
	public static OreObject GOLD_ORE = new OreObject(DCMaterials.GOLD_ORE);
	public static OreObject GEM_ORE = new OreObject(DCMaterials.GEM_ORE);
	
	private final BlockMaterial material;
	
	public OreObject(BlockMaterial material) {
		this.material = material;
	}
	
	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return true;
	}

	@Override
	public void placeObject(World w, int x, int y, int z) {
		
	}

}
