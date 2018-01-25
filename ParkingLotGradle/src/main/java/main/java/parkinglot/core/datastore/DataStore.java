package main.java.parkinglot.core.datastore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import main.java.parkinglot.core.parkinglot.*;

public class DataStore implements IDataStore {
	private Map<String, ParkingLot> parkingLots = new HashMap<>();
	  @Override
	  public Optional<ParkingLot> getParkingLotByName(String name) {
	    if (parkingLots.containsKey(name)) {
	      return Optional.of(parkingLots.get(name));
	    }
	    return Optional.empty();
	  }

	  @Override
	  public void addParkingLot(ParkingLot parkingLot) {
	    parkingLots.put(parkingLot.getName(), parkingLot);
	  }

	  public Map<String, ParkingLot> getParkingLots() {
	    return parkingLots;
	  }

}
