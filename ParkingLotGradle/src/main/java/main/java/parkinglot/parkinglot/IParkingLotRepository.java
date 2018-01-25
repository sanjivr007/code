package main.java.parkinglot.parkinglot;

import java.util.Optional;

import main.java.parkinglot.core.parkinglot.ParkingLot;

public interface IParkingLotRepository {
	
	void createParkingLot(ParkingLot parkingLot);

	  Optional<ParkingLot> findParkingLotByName(String name);

}
