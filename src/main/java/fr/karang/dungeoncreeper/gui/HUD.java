package fr.karang.dungeoncreeper.gui;

import org.spout.api.gui.Screen;
import org.spout.api.gui.Widget;

import fr.karang.dungeoncreeper.DungeonCreeper;

public class HUD extends Screen {

	private Widget skillBar = new Widget();
	
	public HUD() {
		this.setTakesInput(false);
		
		this.attachWidget(DungeonCreeper.getInstance(), skillBar);
	}
	
	private void buildSkillBar() {
		
	}
}
