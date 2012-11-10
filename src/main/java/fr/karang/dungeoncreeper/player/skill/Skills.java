package fr.karang.dungeoncreeper.player.skill;

import fr.karang.dungeoncreeper.player.skill.attacks.AttackSword;
import fr.karang.dungeoncreeper.player.skill.attacks.HandToHand;
import fr.karang.dungeoncreeper.player.skill.utils.Claim;
import fr.karang.dungeoncreeper.player.skill.utils.Dig;
import fr.karang.dungeoncreeper.player.skill.utils.Research;
import fr.karang.dungeoncreeper.player.skill.utils.Speed;
import fr.karang.dungeoncreeper.player.skill.utils.Teleport;

public class Skills {
	
	/**
	 * Attack
	 */
	public static Skill ATTACKSWORD = new AttackSword();
	public static Skill HANDTOHAND = new HandToHand();

	
	/**
	 * Utils
	 */
	public static Skill CLAIM = new Claim();
	public static Skill DIG = new Dig();
	public static Skill RESEARCH = new Research();
	public static Skill SPEED = new Speed();
	public static final Skill TELEPORT = new Teleport();
}
