package fr.karang.dungeoncreeper.world.populator;

import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.World;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class PortalRoomObject extends WorldGeneratorObject{

	private static int WIDTH = 1;
	private static int HEIGHT = 4;
	
	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return true;
	}

	@Override
	public void placeObject(World w, int x, int y, int z) {
		// Make place
		fill(w, x-WIDTH-1, y+1, z-1, x+WIDTH, y+HEIGHT, z+1, DCMaterials.AIR);
		fill(w, x-WIDTH-1, y, z-1, x+WIDTH, y, z+1, DCMaterials.FLOOR);
		
		// Make portal // TODO: make proper materials
		fill(w, x-WIDTH, y, z, x+WIDTH, y+HEIGHT-1, z, DCMaterials.WALL);
		fill(w, x-WIDTH+1, y+1, z, x+WIDTH-1, y+HEIGHT-2, z, DCMaterials.AIR);
	}

	private void fill(World w, int x1, int y1, int z1, int x2, int y2, int z2, BlockMaterial material) {
		for (int x=x1 ; x<=x2 ; x++) {
			for (int y=y1 ; y<=y2 ; y++) {
				for (int z=z1 ; z<=z2 ; z++) {
					w.getBlock(x, y, z).setMaterial(material);
				}
			}
		}
	}
}
