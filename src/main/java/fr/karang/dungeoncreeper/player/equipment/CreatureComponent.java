package fr.karang.dungeoncreeper.player.equipment;

import org.spout.api.component.components.EntityComponent;
import org.spout.api.inventory.Inventory;

import fr.karang.dungeoncreeper.data.DungeonData;

public abstract class CreatureComponent extends EntityComponent {

	@Override
	public void onAttached() {
		getData().put(DungeonData.HEALTH, 10);
		getData().put(DungeonData.MAX_HEALTH, 10);
		getData().put(DungeonData.DAMAGES, 0);
	}
	
	public abstract Inventory getInventory();

}
