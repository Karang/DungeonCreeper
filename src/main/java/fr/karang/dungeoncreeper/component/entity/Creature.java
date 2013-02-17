package fr.karang.dungeoncreeper.component.entity;

import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.component.entity.creature.BileDemon;
import fr.karang.dungeoncreeper.component.entity.creature.BlackKnight;
import fr.karang.dungeoncreeper.component.entity.creature.DarkAngel;
import fr.karang.dungeoncreeper.component.entity.creature.DarkElf;
import fr.karang.dungeoncreeper.component.entity.creature.DarkMistress;
import fr.karang.dungeoncreeper.component.entity.creature.Firefly;
import fr.karang.dungeoncreeper.component.entity.creature.Gobelin;
import fr.karang.dungeoncreeper.component.entity.creature.HornedReaper;
import fr.karang.dungeoncreeper.component.entity.creature.Imp;
import fr.karang.dungeoncreeper.component.entity.creature.Maiden;
import fr.karang.dungeoncreeper.component.entity.creature.Rogue;
import fr.karang.dungeoncreeper.component.entity.creature.Salamender;
import fr.karang.dungeoncreeper.component.entity.creature.Skeleton;
import fr.karang.dungeoncreeper.component.entity.creature.Troll;
import fr.karang.dungeoncreeper.component.entity.creature.Vampire;
import fr.karang.dungeoncreeper.component.entity.creature.Warlock;
import fr.karang.dungeoncreeper.material.dungeon.DungeonResource;

public enum Creature {
	
	
	BILE_DEMON(BileDemon.class),
	BLACK_KNIGHT(BlackKnight.class),
	DARK_ANGEL(DarkAngel.class),
	DARK_ELF(DarkElf.class),
	DARK_MISTRESS(DarkMistress.class),
	FIREFLY(Firefly.class),
	GOBELIN(Gobelin.class),
	HORNEDREAPER(HornedReaper.class),
	IMP(Imp.class),
	MAIDEN(Maiden.class),
	ROGUE(Rogue.class),
	SALAMENDER(Salamender.class),
	SKELETON(Skeleton.class),
	TROLL(Troll.class),
	VAMPIRE(Vampire.class),
	WARLOCK(Warlock.class);

	static Map<Class<? extends CreatureComponent>,Creature> map = new HashMap<Class<? extends CreatureComponent>,Creature>();
	
	private final Class<? extends CreatureComponent> type;
	private final Map<Class<? extends DungeonResource>,Integer> required = new HashMap<Class<? extends DungeonResource>,Integer>();
	
	Creature(Class<? extends CreatureComponent> type){
		this.type = type;
	}
	
	public Class<? extends CreatureComponent> getComponent(){
		return type;
	}
	
	public Map<Class<? extends DungeonResource>,Integer> getRequired(){
		return required;
	}
	
	public static Creature getCreature(Class<? extends CreatureComponent> type){
		return map.get(type);
	}
	
	//Register required
	static{
		//Inversed map
		for(Creature c : Creature.values()){
			map.put(c.getComponent(), c);
		}
		
		//Imp nothing
		
		//Gobelin
		//GOBELIN.required.put(Bed, 1);
		
		//etc...
	}
	
}
