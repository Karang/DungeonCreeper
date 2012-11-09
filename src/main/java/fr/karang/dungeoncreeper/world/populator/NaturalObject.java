package fr.karang.dungeoncreeper.world.populator;

import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.World;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class NaturalObject extends WorldGeneratorObject {
	public static NaturalObject GOLD_ORE = new NaturalObject(DCMaterials.GOLD_ORE);
	public static NaturalObject GEM_ORE = new NaturalObject(DCMaterials.GEM_ORE);
	public static NaturalObject CAVERN = new NaturalObject(DCMaterials.AIR);
	public static NaturalObject LAVA = new NaturalObject(DCMaterials.LAVA);
	
	private final BlockMaterial material;
	
	public NaturalObject(BlockMaterial material) {
		this.material = material;
	}
	
	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return w.getBlock(x, y, z).getMaterial().isMaterial(DCMaterials.DIRT);
	}

	@Override
	public void placeObject(World w, int x, int y, int z) {
		if (material.isMaterial(DCMaterials.LAVA)) {
			w.getBlock(x, y-1, z).setMaterial(material);
			w.getBlock(x, y, z).setMaterial(DCMaterials.AIR);
			w.getBlock(x, y+1, z).setMaterial(DCMaterials.AIR);
		} else {
			w.getBlock(x, y, z).setMaterial(material);
			w.getBlock(x, y+1, z).setMaterial(material);
		}
	}

}
