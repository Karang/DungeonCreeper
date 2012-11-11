package fr.karang.dungeoncreeper.room.type;

import org.spout.api.math.Rectangle;

import fr.karang.dungeoncreeper.player.Team;

public abstract class Room {

	public enum Rooms{
		LAIR(new Lair()),
		HATCHERY(new Hatchery());
		
		private Room room;
		
		Rooms(Room room){
			this.room = room;
		}
		
		public Room getRoom(){
			return room;
		}
	}
	
	private int buyPrice = 0;
	private int sellPrice = 0;
	
	public int getBuyPrice() {
		return buyPrice;
	}

	protected void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	protected void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public boolean hasRequired(Team team) {
		// TODO Auto-generated method stub
		return false;
	}

	public Rectangle getUv() {
		// TODO Auto-generated method stub
		return null;
	}



}
