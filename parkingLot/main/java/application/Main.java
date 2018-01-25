package main.java.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.java.parkinglot.IParkingLotManager;
import main.java.parkinglot.ParkingLotManager;
import main.java.parkinglot.core.datastore.DataStore;
import main.java.parkinglot.core.datastore.IDataStore;
import main.java.parkinglot.core.parkinglot.ParkingLotRepository;
import main.java.parkinglot.parkinglot.IParkingLotRepository;

import static main.java.application.ParkingLotCommandHandler.handleCreateParkingLot;
import static main.java.application.ParkingLotCommandHandler.handleGetRegistrationNumbersForColour;
import static main.java.application.ParkingLotCommandHandler.handleGetSlotNumberForRegistrationNumber;
import static main.java.application.ParkingLotCommandHandler.handleGetSlotNumbersForColour;
import static main.java.application.ParkingLotCommandHandler.handleGetStatus;
import static main.java.application.ParkingLotCommandHandler.handleLeave;
import static main.java.application.ParkingLotCommandHandler.handlePark;

public class Main {

	  private static final String SPACE = " ";
	  private static final IDataStore dataStore = new DataStore();
	  private static final IParkingLotRepository parkingLotRepository = new ParkingLotRepository(dataStore);
	  private static final IParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotRepository);

	  public static void main(String[] args) {
	    if (args.length == 0) {
	      handleInteractiveShellCommands();
	    } else {
	      handleTextFileInput(args);
	    }
	  }

	  private static void handleTextFileInput(String[] args) {
	    String textFileName = args[0];
	    List<String> commandList = readCommandsFromFile(textFileName);
	    commandList.forEach(Main::executeParkingLotCommand);
	  }

	  private static List<String> readCommandsFromFile(String textFileName) {
	    List<String> commandList = new ArrayList<>();
	    readFileAndGenerateList(textFileName, commandList);
	    return commandList;
	  }

	  private static void readFileAndGenerateList(String textFileName, List<String> commandList) {
	    try (BufferedReader br = new BufferedReader(new FileReader(textFileName))) {
	      String line = br.readLine();
	      while (line != null) {
	        commandList.add(line);
	        line = br.readLine();
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  private static void handleInteractiveShellCommands() {
	    Scanner scanner = new Scanner(System.in);
	    while (scanner.hasNextLine()) {
	      String input = scanner.nextLine();
	      executeParkingLotCommand(input);
	    }
	  }

	  private static void executeParkingLotCommand(String input) {
	    String command = extractCommandFromInput(input);
	    List<String> arguments = extractArgumentsFromInput(input);
	    switch (command) {
	      case "create_parking_lot":
	        handleCreateParkingLot(arguments, parkingLotManager);
	        break;

	      case "park":
	        handlePark(arguments, parkingLotManager);
	        break;

	      case "leave":
	        handleLeave(arguments, parkingLotManager);
	        break;

	      case "status":
	        handleGetStatus(parkingLotManager);
	        break;

	      case "registration_numbers_for_cars_with_colour":
	        handleGetRegistrationNumbersForColour(arguments, parkingLotManager);
	        break;

	      case "slot_numbers_for_cars_with_colour":
	        handleGetSlotNumbersForColour(arguments, parkingLotManager);
	        break;

	      case "slot_number_for_registration_number":
	        handleGetSlotNumberForRegistrationNumber(arguments, parkingLotManager);
	        break;

	      default:
	        System.out.printf("Invalid Command: %s%n", command);
	    }
	  }


	  private static List<String> extractArgumentsFromInput(String input) {
	    String[] splitInput = input.split(SPACE);
	    if (splitInput.length > 1) {
	      return Arrays.asList(Arrays.copyOfRange(splitInput, 1, splitInput.length));
	    } else {
	      return new ArrayList<>();
	    }
	  }

	  private static String extractCommandFromInput(String input) {
	    return input.split(SPACE)[0];
	  }
	}
