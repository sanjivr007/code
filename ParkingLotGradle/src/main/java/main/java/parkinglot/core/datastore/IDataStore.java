package main.java.parkinglot.core.datastore;

import java.util.Optional;
import main.java.parkinglot.core.parkinglot.*;

public interface IDataStore {

	Optional<ParkingLot> getParkingLotByName(String name);
	  void addParkingLot(ParkingLot parkingLot);
}
