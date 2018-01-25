package main.java.parkinglot.core.parkinglot;

import java.util.Optional;

import main.java.parkinglot.core.datastore.IDataStore;
import main.java.parkinglot.parkinglot.IParkingLotRepository;

public class ParkingLotRepository implements IParkingLotRepository {

	  private final IDataStore dataStore;

	  public ParkingLotRepository(IDataStore dataStore) {
	    this.dataStore = dataStore;
	  }

	  @Override
	  public void createParkingLot(ParkingLot parkingLot) {
	    dataStore.addParkingLot(parkingLot);
	  }

	  @Override
	  public Optional<ParkingLot> findParkingLotByName(String name) {
	    return dataStore.getParkingLotByName(name);
	  }

	}
