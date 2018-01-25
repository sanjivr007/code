package main.java.parkinglot;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import main.java.parkinglot.core.parkinglot.ParkingLot;
import main.java.parkinglot.core.slot.Slot;
import main.java.parkinglot.core.vehicle.Vehicle;
import main.java.parkinglot.parkinglot.IParkingLotRepository;

public class ParkingLotManager implements IParkingLotManager {

	  private static final String COMMA = ", ";
	  private final IParkingLotRepository parkingLotRepository;


	  public ParkingLotManager(IParkingLotRepository parkingLotRepository) {
	    this.parkingLotRepository = parkingLotRepository;
	  }

	  public void createParkingLot(String name, int numberOfSlots) {
	    if (!parkingLotRepository.findParkingLotByName(name).isPresent()) {
	      ParkingLot parkingLot = new ParkingLot(name, numberOfSlots);
	      parkingLotRepository.createParkingLot(parkingLot);
	      System.out.printf("Created a parking lot with %d slots%n", numberOfSlots);
	    } else {
	      System.out.println("Invalid command, parking lot with given name is already created, please use some other command");
	    }
	  }

	  @Override
	  public void park(String parkingLotName, String registrationNumber, String colour) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    if (isRegistrationNumberAlreadyPresent(registrationNumber, parkingLot)) return;

	    if (checkIfSlotsArePresent(parkingLot)) return;

	    Slot slot = parkingLot.getUnallocatedSlots().remove();

	    Vehicle vehicle = new Vehicle(registrationNumber, colour);

	    updateParkingLotAfterParking(registrationNumber, colour, parkingLot, slot, vehicle);

	    System.out.printf("Allocated slot number: %d%n", slot.getslotNumber());
	  }

	  private void updateParkingLotAfterParking(String registrationNumber, String colour, ParkingLot parkingLot, Slot slot, Vehicle vehicle) {
	    parkingLot.getRegistrationNumberVehicleMap().put(registrationNumber, vehicle);

	    parkingLot.getParkedVehicleSlots().put(vehicle, slot);

	    parkingLot.getSlotVehicleMap().put(slot, vehicle);

	    updateColoursMap(parkingLot, colour, vehicle);
	  }

	  @Override
	  public void emptySlot(String parkingLotName, int slotNumber) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    if (isVehicleNotPresentInGivenSlotNumber(slotNumber, parkingLot)) return;

	    Slot slot = parkingLot.getSlotNumberSlotMap().get(slotNumber);

	    Vehicle vehicle = parkingLot.getSlotVehicleMap().get(slot);

	    updateParkingLotAfterVehicleLeaving(parkingLot, slot, vehicle);

	    System.out.printf("Slot number %d is free%n", slotNumber);

	  }

	  @Override
	  public void getStatus(String parkingLotName) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    System.out.println("Slot No.\tRegistration No\tColour");

	    for (Slot slot : parkingLot.getSlotVehicleMap().keySet()) {
	      System.out.printf("%d\t%s\t%s%n", slot.getslotNumber(), parkingLot.getSlotVehicleMap().get(slot).getRegistrationNumber(), parkingLot.getSlotVehicleMap().get(slot).getColour());
	    }

	  }

	  @Override
	  public void getRegistrationNumbersForColour(String parkingLotName, String colour) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    if (isColourNotPresent(colour, parkingLot)) return;

	    System.out.println(String.join(COMMA, parkingLot.getColourVehiclesMap().get(colour).stream().map(Vehicle::getRegistrationNumber).collect(Collectors.toList())));

	  }

	  @Override
	  public void getSlotNumbersForColour(String parkingLotName, String colour) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    if (isColourNotPresent(colour, parkingLot)) return;

	    System.out.println(String.join(COMMA, parkingLot.getColourVehiclesMap().get(colour).stream().map(vehicle -> Integer.toString(parkingLot.getParkedVehicleSlots().get(vehicle).getslotNumber())).collect(Collectors.toList())));

	  }

	  private boolean isColourNotPresent(String colour, ParkingLot parkingLot) {
	    if (!parkingLot.getColourVehiclesMap().containsKey(colour)) {
	      System.out.println("Not found");
	      return true;
	    }
	    return false;
	  }

	  @Override
	  public void getSlotNumberForRegistrationNumber(String parkingLotName, String registrationNumber) {
	    Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByName(parkingLotName);

	    if (isParkingLotInvalid(parkingLotOptional)) return;

	    ParkingLot parkingLot = parkingLotOptional.get();

	    if (isRegistrationNumberNotPresent(registrationNumber, parkingLot)) return;

	    System.out.println(parkingLot.getParkedVehicleSlots().get(parkingLot.getRegistrationNumberVehicleMap().get(registrationNumber)).getslotNumber());

	  }

	  private boolean isRegistrationNumberNotPresent(String registrationNumber, ParkingLot parkingLot) {
	    if (!parkingLot.getRegistrationNumberVehicleMap().containsKey(registrationNumber)) {
	      System.out.println("Not found");
	      return true;
	    }
	    return false;
	  }

	  private void updateParkingLotAfterVehicleLeaving(ParkingLot parkingLot, Slot slot, Vehicle vehicle) {
	    parkingLot.getSlotVehicleMap().remove(slot);

	    parkingLot.getRegistrationNumberVehicleMap().remove(vehicle.getRegistrationNumber());

	    parkingLot.getUnallocatedSlots().add(slot);

	    parkingLot.getParkedVehicleSlots().remove(vehicle);

	    updateColourVehiclesMapAfterVehicleLeaving(parkingLot, vehicle);
	  }

	  private void updateColourVehiclesMapAfterVehicleLeaving(ParkingLot parkingLot, Vehicle vehicle) {
	    parkingLot.getColourVehiclesMap().get(vehicle.getColour()).remove(vehicle);
	    if (parkingLot.getColourVehiclesMap().get(vehicle.getColour()).size() == 0) {
	      parkingLot.getColourVehiclesMap().remove(vehicle.getColour());
	    }
	  }

	  private boolean isVehicleNotPresentInGivenSlotNumber(int slotNumber, ParkingLot parkingLot) {
	    if (!parkingLot.getSlotVehicleMap().containsKey(parkingLot.getSlotNumberSlotMap().get(slotNumber))) {
	      System.out.println("Not found");
	      return true;
	    }
	    return false;
	  }

	  private boolean checkIfSlotsArePresent(ParkingLot parkingLot) {
	    if (parkingLot.getUnallocatedSlots().size() == 0) {
	      System.out.println("Sorry, parking lot is full");
	      return true;
	    }
	    return false;
	  }

	  private boolean isRegistrationNumberAlreadyPresent(String registrationNumber, ParkingLot parkingLot) {
	    if (parkingLot.getRegistrationNumberVehicleMap().containsKey(registrationNumber)) {
	      System.out.println("Vehicle is already present in parking lot. Can't park. Please check if you have entered correct registration number");
	      return true;
	    }
	    return false;
	  }

	  private boolean isParkingLotInvalid(Optional<ParkingLot> parkingLotOptional) {
	    if (!parkingLotOptional.isPresent()) {
	      System.out.println("Incorrect parking lot name. Please check");
	      return true;
	    }
	    return false;
	  }

	  private void updateColoursMap(ParkingLot parkingLot, String colour, Vehicle vehicle) {
	    if (parkingLot.getColourVehiclesMap().containsKey(colour)) {
	      parkingLot.getColourVehiclesMap().get(colour).add(vehicle);
	    } else {
	      Set<Vehicle> vehicles = new LinkedHashSet<>();
	      vehicles.add(vehicle);
	      parkingLot.getColourVehiclesMap().put(colour, vehicles);
	    }
	  }


	}
