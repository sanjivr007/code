package main.test.parkinglot.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.parkinglot.core.parkinglot.ParkingLot;

public class ParkingLotTest {
	
	@Test
	public void testInit() {
		ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    assertEquals(6, parkingLot.getSlots().size());
	    assertEquals(6, parkingLot.getUnallocatedSlots().size());
	    assertEquals(1, parkingLot.getUnallocatedSlots().peek().getslotNumber());
	    assertEquals(6, parkingLot.getSlotNumberSlotMap().size());
	}
	    

}
