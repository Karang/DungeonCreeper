package fr.karang.dungeoncreeper.player;

import java.util.HashMap;
import java.util.Map;

import fr.karang.dungeoncreeper.player.equipment.BileDemon;
import fr.karang.dungeoncreeper.player.equipment.BlackKnights;
import fr.karang.dungeoncreeper.player.equipment.CreatureComponent;
import fr.karang.dungeoncreeper.player.equipment.DarkAngels;
import fr.karang.dungeoncreeper.player.equipment.DarkElf;
import fr.karang.dungeoncreeper.player.equipment.DarkMistress;
import fr.karang.dungeoncreeper.player.equipment.Firefly;
import fr.karang.dungeoncreeper.player.equipment.Gobelin;
import fr.karang.dungeoncreeper.player.equipment.HornedReaper;
import fr.karang.dungeoncreeper.player.equipment.Imp;
import fr.karang.dungeoncreeper.player.equipment.Maiden;
import fr.karang.dungeoncreeper.player.equipment.Rogue;
import fr.karang.dungeoncreeper.player.equipment.Salamender;
import fr.karang.dungeoncreeper.player.equipment.Skeleton;
import fr.karang.dungeoncreeper.player.equipment.Troll;
import fr.karang.dungeoncreeper.player.equipment.Vampire;
import fr.karang.dungeoncreeper.player.equipment.Warlock;

public class Equipments {

	private static Map<Integer, Class<? extends CreatureComponent>> byID = new HashMap<Integer, Class<? extends CreatureComponent>>();
	private static Map<Class<? extends CreatureComponent>,Integer> byClass = new HashMap<Class<? extends CreatureComponent>,Integer>();
	private static int id = 0;
	
	private static void register(Class<? extends CreatureComponent> component){
		byID.put(id, component);
		byClass.put(component,id++);
	}
	
	public static int getIdByCreatureComponent(Class<? extends CreatureComponent> component){
		return byClass.get(component);
	}
	
	public static Class<? extends CreatureComponent> getCreatureComponentById(int id){
		return byID.get(id);
	}
	
	static{
		register(BileDemon.class);
		register(BlackKnights.class);
		register(DarkAngels.class);
		register(DarkElf.class);
		register(DarkMistress.class);
		register(Firefly.class);
		register(Gobelin.class);
		register(HornedReaper.class);
		register(Imp.class);
		register(Maiden.class);
		register(Rogue.class);
		register(Salamender.class);
		register(Skeleton.class);
		register(Troll.class);
		register(Vampire.class);
		register(Warlock.class);
	}
	
}
