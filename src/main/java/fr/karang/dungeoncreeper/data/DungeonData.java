package fr.karang.dungeoncreeper.data;

import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;

public class DungeonData {
	public static final DefaultedKey<Integer> HEALTH = new DefaultedKeyImpl<Integer>("health", 1);
	public static final DefaultedKey<Integer> MAX_HEALTH = new DefaultedKeyImpl<Integer>("max_health", 1);
	public static final DefaultedKey<Integer> GOLD_AMOUNT = new DefaultedKeyImpl<Integer>("gold_amount", 0);
	public static final DefaultedKey<Integer> DAMAGES = new DefaultedKeyImpl<Integer>("damages", 0);
	public static final DefaultedKey<Integer> SKILLSLOT = new DefaultedKeyImpl<Integer>("skillslot", 0);
}
