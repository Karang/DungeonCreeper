package fr.karang.dungeoncreeper.player.skill.utils;

import org.spout.api.component.components.HitBlockComponent;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;

import fr.karang.dungeoncreeper.player.skill.Skill;

public class Claim extends Skill {

	public Claim() {
		super(0);
	}

	@Override
	public void handle(Entity source) {
		Block block = source.get(HitBlockComponent.class).getTargetBlock();
		if (block!=null && isNextClaimedBlock(block)){
			//TODO : Claim territory & Declaim enemy territory
		}
	}
	
	private boolean isNextClaimedBlock(Block block){
		//TODO : Check if neigbour block is claim
		
		return true;
	}
	
}
