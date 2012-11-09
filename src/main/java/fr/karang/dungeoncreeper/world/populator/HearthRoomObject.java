package fr.karang.dungeoncreeper.world.populator;

import org.spout.api.generator.WorldGeneratorObject;
import org.spout.api.geo.World;

import fr.karang.dungeoncreeper.material.DCMaterials;

public class HearthRoomObject extends WorldGeneratorObject {

	private static int HALFSIZE = 5;
	
	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return true;
	}

	@Override
	public void placeObject(World w, int x, int y, int z) {
		// Make place
		for (int xx=x-HALFSIZE ; xx<x+HALFSIZE ; xx++) {
			for (int zz=z-HALFSIZE ; zz<z+HALFSIZE ; zz++) {
				for (int yy=y ; y<y+2 ; y++) {
					w.getBlock(xx, yy, zz).setMaterial(DCMaterials.AIR);
				}
			}
		}
	}

}
