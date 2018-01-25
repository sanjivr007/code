package main.test.datastore;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import main.java.parkinglot.core.datastore.DataStore;
import main.java.parkinglot.core.parkinglot.ParkingLot;

public class DataStoreTest {

	 @Test
	  public void testAddParkingLot() {
	    DataStore dataStore = new DataStore();
	    assertEquals(0, dataStore.getParkingLots().size());
	    dataStore.addParkingLot(new ParkingLot("parkinglot", 6));
	    assertEquals(1, dataStore.getParkingLots().size());
	  }

	  @Test
	  public void testGetParkingLotByName() {
	    DataStore dataStore = new DataStore();
	    assertEquals(Optional.empty(), dataStore.getParkingLotByName("parkinglot"));
	    dataStore.addParkingLot(new ParkingLot("parkinglot", 6));
	    assertEquals(true, dataStore.getParkingLotByName("parkinglot").isPresent());
	    assertEquals(6, dataStore.getParkingLotByName("parkinglot").get().getSlots().size());
	  }
	  

	}
