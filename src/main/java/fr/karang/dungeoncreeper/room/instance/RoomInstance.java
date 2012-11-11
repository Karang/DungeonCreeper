package fr.karang.dungeoncreeper.room.instance;

import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.room.type.Room.Rooms;

public class RoomInstance {

	private final Rooms room;
	private final Rectangle rect;
	private final int surface;

	public RoomInstance(Rooms room, Rectangle rect){
		this.room = room;
		this.rect = rect;
		surface = (int)(rect.getHeight() * rect.getWidth());
	}

	public Rooms getRoom() {
		return room;
	}

	public Rectangle getRect() {
		return rect;
	}

	public int getSurface() {
		return surface;
	}

	public int getSellPrice() {
		return room.getRoom().getSellPrice() * surface;
	}


}
