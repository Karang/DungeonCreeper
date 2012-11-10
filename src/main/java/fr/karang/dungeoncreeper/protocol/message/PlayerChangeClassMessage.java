package fr.karang.dungeoncreeper.protocol.message;

import org.spout.api.component.components.EntityComponent;
import org.spout.api.protocol.Message;

import fr.karang.dungeoncreeper.player.equipment.Imp;

public class PlayerChangeClassMessage implements Message {

	private String name;
	private short classId;
	
	public PlayerChangeClassMessage(String name, short classId) {
		this.name = name;
		this.classId = classId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getClassId() {
		return classId;
	}
	
	public Class<? extends EntityComponent> getPlayerClass() {
		if (classId==1) { // Imp
			return Imp.class;
		}
		return Imp.class; // Fallback
	}
}
