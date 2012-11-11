package fr.karang.dungeoncreeper.room.instance;

import java.util.ArrayList;
import java.util.List;

import fr.karang.dungeoncreeper.room.type.Room.Rooms;

public class RoomContainer {

	private final Rooms type;
	private final List<RoomInstance> rooms = new ArrayList<RoomInstance>();
	
	private int surface = 0;

	public RoomContainer(Rooms type){
		this.type = type;
	}

	public void addRoom(RoomInstance room){
		rooms.add(room);
		surface += room.getSurface();
	}
	
	public void removeRoom(RoomInstance room){
		rooms.remove(room);
		surface -= room.getSurface();
	}
	
	public Rooms getType() {
		return type;
	}

	public int getSurface() {
		return surface;
	}
}
