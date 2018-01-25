package main.java.parkinglot.core.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import main.java.parkinglot.core.vehicle.*;
import main.java.parkinglot.core.slot.*;

public class ParkingLot {
	
	private int id;

	  private String name;

	  private List<Slot> slots;

	  private PriorityQueue<Slot> unallocatedSlots = new PriorityQueue<>();

	  private int numberOfLevels;

	  private Map<Vehicle, Slot> parkedVehicleSlots = new HashMap<>();
	  private Map<String, Vehicle> registrationNumberVehicleMap = new HashMap<>();
	  private Map<Slot, Vehicle> slotVehicleMap = new LinkedHashMap<>();
	  private Map<Integer, Slot> slotNumberSlotMap = new HashMap<>();
	  private Map<String, Set<Vehicle>> colourVehiclesMap = new LinkedHashMap<>();


	  public ParkingLot(String name, int numberOfSlots) {
	    this.name = name;
	    this.slots = new ArrayList<>(numberOfSlots);
	    createSlots(numberOfSlots);
	    unallocatedSlots.addAll(slots);
	    updateSlotNumberSlotMap();
	  }

	  private void updateSlotNumberSlotMap() {
	    for (Slot slot : this.slots) {
	      this.getSlotNumberSlotMap().put(slot.getslotNumber(), slot);
	    }
	  }


	  private void createSlots(int numberOfSlots) {
	    for (int i = 0; i < numberOfSlots; i++) {
	      slots.add(new Slot(i+1));
	    }
	  }

	  public String getName() {
	    return name;
	  }

	  public List<Slot> getSlots() {
	    return slots;
	  }

	  public PriorityQueue<Slot> getUnallocatedSlots() {
	    return unallocatedSlots;
	  }

	  public Map<Vehicle, Slot> getParkedVehicleSlots() {
	    return parkedVehicleSlots;
	  }

	  public Map<String, Vehicle> getRegistrationNumberVehicleMap() {
	    return registrationNumberVehicleMap;
	  }

	  public Map<Slot, Vehicle> getSlotVehicleMap() {
	    return slotVehicleMap;
	  }

	  public Map<Integer, Slot> getSlotNumberSlotMap() {
	    return slotNumberSlotMap;
	  }

	  public Map<String, Set<Vehicle>> getColourVehiclesMap() {
	    return colourVehiclesMap;
	  }

}
