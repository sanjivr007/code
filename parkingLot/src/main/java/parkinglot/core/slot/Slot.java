package main.java.parkinglot.core.slot;

public class Slot implements Comparable<Slot> {
	public Slot(int slotNumber) {
	    this.slotNumber = slotNumber;
	    this.occupied = false;
	  }

	  private int id;
	  private int level;
	  private boolean occupied;
	  private int slotNumber;

	  public int getslotNumber() {
	    return slotNumber;
	  }

	  @Override
	  public int compareTo(Slot o) {
	    return this.getslotNumber() - o.getslotNumber();
	  }

}
