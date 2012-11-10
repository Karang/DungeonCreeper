package fr.karang.dungeoncreeper.player.skill.projectiles;

import org.spout.api.entity.Entity;

import fr.karang.dungeoncreeper.player.skill.Skill;

public abstract class Projectile extends Skill {

	public Projectile(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Entity source) {
		// TODO Auto-generated method stub

	}
	
	public abstract Object getProjectile();

}
