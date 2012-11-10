package fr.karang.dungeoncreeper.player.skill.utils;

import org.spout.api.component.components.HitBlockComponent;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.material.BlockMaterial;

import fr.karang.dungeoncreeper.material.DCMaterials;
import fr.karang.dungeoncreeper.player.skill.Skill;

public class Dig extends Skill {

	public Dig() {
		super(0);
	}

	@Override
	public void handle(Entity source) {
		Block block = source.get(HitBlockComponent.class).getTargetBlock();
		if (block!=null){
			if(block == DCMaterials.DIRT){
				block.setMaterial(BlockMaterial.AIR);
			}else if(block == DCMaterials.GEM_ORE){
				//TODO : Give gold
			}else if(block == DCMaterials.GOLD_BAG){
				//TODO : Give gold
				block.setMaterial(BlockMaterial.AIR);
			}
		}
	}
}
