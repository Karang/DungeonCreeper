package fr.karang.dungeoncreeper.player.skill.attacks;

import org.spout.api.entity.Entity;
import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.player.skill.Skill;

public class HandToHand extends Skill {

	public HandToHand() {
		super(0,1000);
	}
	
	public void handle(Entity source){
		//TODO : raycast on entity, damage it
	}
	
	@Override
	public Rectangle getUv() {
		return new Rectangle(32f/256f, 0, 32f/256f, 32f/256f);
	}
}
