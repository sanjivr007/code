package main.java.parkinglot;

public interface IParkingLotManager {
	  public void createParkingLot(String name, int numberOfSlots);

	  void park(String parkingLotName, String registrationNumber, String colour);

	  void emptySlot(String parkingLotName, int slotNumber);

	  void getStatus(String parkingLotName);

	  void getRegistrationNumbersForColour(String parkingLotName, String colour);

	  void getSlotNumbersForColour(String parkingLotName, String colour);

	  void getSlotNumberForRegistrationNumber(String parkingLotName, String registrationNumber);
	}
