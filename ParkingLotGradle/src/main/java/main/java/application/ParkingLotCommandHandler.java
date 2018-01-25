package main.java.application;

import java.util.List;

import main.java.parkinglot.IParkingLotManager;

public class ParkingLotCommandHandler {
	  private static final String GOJEKPARKINGLOT = "gojekparkinglot";

	  public static void handlePark(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 2) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 2%n", arguments.size());
	      return;
	    }
	    parkingLotManager.park(GOJEKPARKINGLOT, arguments.get(0), arguments.get(1));
	  }

	  public static void handleCreateParkingLot(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 1) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 1%n", arguments.size());
	      return;
	    }
	    parkingLotManager.createParkingLot(GOJEKPARKINGLOT, Integer.parseInt(arguments.get(0)));
	  }

	  public static void handleLeave(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 1) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 1%n", arguments.size());
	      return;
	    }
	    parkingLotManager.emptySlot(GOJEKPARKINGLOT, Integer.parseInt(arguments.get(0)));
	  }


	  public static void handleGetStatus(IParkingLotManager parkingLotManager) {
	    parkingLotManager.getStatus(GOJEKPARKINGLOT);
	  }

	  public static void handleGetRegistrationNumbersForColour(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 1) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 1%n", arguments.size());
	      return;
	    }
	    parkingLotManager.getRegistrationNumbersForColour(GOJEKPARKINGLOT, arguments.get(0));

	  }

	  public static void handleGetSlotNumbersForColour(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 1) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 1%n", arguments.size());
	      return;
	    }
	    parkingLotManager.getSlotNumbersForColour(GOJEKPARKINGLOT, arguments.get(0));

	  }

	  public static void handleGetSlotNumberForRegistrationNumber(List<String> arguments, IParkingLotManager parkingLotManager) {
	    if (arguments.size() != 1) {
	      System.out.printf("Invalid number of arguments %d , please enter correct number of arguments i.e 1%n", arguments.size());
	      return;
	    }
	    parkingLotManager.getSlotNumberForRegistrationNumber(GOJEKPARKINGLOT, arguments.get(0));

	  }
	}
