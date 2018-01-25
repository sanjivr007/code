package main.test;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import main.java.parkinglot.ParkingLotManager;
import main.java.parkinglot.core.parkinglot.ParkingLot;
import main.java.parkinglot.core.vehicle.Vehicle;
import main.java.parkinglot.parkinglot.IParkingLotRepository;


public class ParkingLotManagerTest {
	
	 private ParkingLotManager parkingLotManager;
	  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	  @Mock
	  private IParkingLotRepository parkingLotRepository;

	  @Before
	  public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    System.setOut(new PrintStream(outContent));
	    parkingLotManager = new ParkingLotManager(parkingLotRepository);
	  }

	  @Test
	  public void shouldCreateParkingLotTest() {
	    final ArgumentCaptor<ParkingLot> captor = ArgumentCaptor.forClass(ParkingLot.class);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.empty());
	    parkingLotManager.createParkingLot("parkinglot", 6);
	    verify(parkingLotRepository).createParkingLot(captor.capture());
	    ParkingLot parkingLot = captor.getValue();
	    assertEquals(6, parkingLot.getSlots().size());
	    assertEquals(1, parkingLot.getSlots().get(0).getslotNumber());
	    assertEquals(1, parkingLot.getUnallocatedSlots().remove().getslotNumber());
	    verify(parkingLotRepository, times(1)).createParkingLot(parkingLot);
	    assertEquals("Created a parking lot with 6 slots\n", outContent.toString());
	  }

	 /* @Test
	  public void shouldNotCreateParkingLotAlreadyPresent() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(new ParkingLot("pl", 3)));
	    parkingLotManager.createParkingLot("parkinglot", 6);
	    verify(parkingLotRepository, times(0)).createParkingLot(any(ParkingLot.class));
	    assertEquals("Invalid command, parking lot with given name is already created, please use some other command\n", outContent.toString());
	  }*/

	  @Test
	  public void shouldNotParkIncorrectParkingLot() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.park("parkinglot1", "r1", "white");
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void shouldNotParkRegistrationNumberAlreadyPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    parkingLot.getRegistrationNumberVehicleMap().put("r1", new Vehicle("r1", "white"));
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "r1", "white");
	    assertEquals("Vehicle is already present in parking lot. Can't park. Please check if you have entered correct registration number\n", outContent.toString());
	  }

	  @Test
	  public void shouldNotParkSlotsFull() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLot.getUnallocatedSlots().remove();
	    parkingLotManager.park("parkinglot", "r1", "white");
	    assertEquals("Sorry, parking lot is full\n", outContent.toString());
	  }

	  @Test
	  public void shouldParkAndFillTheMaps() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "r1", "white");
	    assertEquals(5, parkingLot.getUnallocatedSlots().size());
	    parkingLotManager.park("parkinglot", "r2", "white");
	    assertEquals(true, parkingLot.getColourVehiclesMap().containsKey("white"));
	    assertEquals(true, parkingLot.getRegistrationNumberVehicleMap().containsKey("r1"));
	    assertEquals("white", parkingLot.getRegistrationNumberVehicleMap().get("r1").getColour());
	    assertEquals("r1", parkingLot.getRegistrationNumberVehicleMap().get("r1").getRegistrationNumber());
	    assertEquals(4, parkingLot.getUnallocatedSlots().size());
	    assertEquals(2, parkingLot.getColourVehiclesMap().get("white").size());
	  }

	  @Test
	  public void shouldNotEmptySlotInvalidParkingLot() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.emptySlot("parkinglot1", 1);
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void shouldEmptySlotAndUpdateMaps() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "r1", "white");
	    assertEquals(5, parkingLot.getUnallocatedSlots().size());
	    parkingLotManager.emptySlot("parkinglot", 1);
	    assertEquals(6, parkingLot.getUnallocatedSlots().size());
	    assertEquals(0, parkingLot.getColourVehiclesMap().size());
	    assertEquals(0, parkingLot.getRegistrationNumberVehicleMap().size());
	    assertEquals(0, parkingLot.getSlotVehicleMap().size());
	    assertEquals(0, parkingLot.getParkedVehicleSlots().size());
	    assertEquals("Allocated slot number: 1\nSlot number 1 is free\n", outContent.toString());
	  }

	  @Test
	  public void shouldNotEmptySlotVehicleNotPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    assertEquals(6, parkingLot.getUnallocatedSlots().size());
	    parkingLotManager.emptySlot("parkinglot", 1);
	    assertEquals(6, parkingLot.getUnallocatedSlots().size());
	    assertEquals(0, parkingLot.getColourVehiclesMap().size());
	    assertEquals(0, parkingLot.getRegistrationNumberVehicleMap().size());
	    assertEquals(0, parkingLot.getSlotVehicleMap().size());
	    assertEquals(0, parkingLot.getParkedVehicleSlots().size());
	    assertEquals("Not found\n", outContent.toString());
	  }

	  @Test
	  public void testGetStatusParkingLotNotPresent() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.getStatus("parkinglot1");
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void testGetStatusEmptyParkingLot() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.getStatus("parkinglot");
	    assertEquals("Slot No.\tRegistration No\tColour\n", outContent.toString());
	  }

	  @Test
	  public void testGetStatusParkingLotNotEmpty() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getStatus("parkinglot");
	    assertEquals("Allocated slot number: 1\nSlot No.\tRegistration No\tColour\n1\tv1\twhite\n", outContent.toString());
	  }

	  @Test
	  public void testGetRegistrationNumbersForColourParkingLotNotPresent() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.getStatus("parkinglot1");
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void testGetRegistrationNumbersForColourColourNotPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getRegistrationNumbersForColour("parkinglot", "black");
	    assertEquals("Allocated slot number: 1\nNot found\n", outContent.toString());
	  }

	  @Test
	  public void testGetRegistrationNumbersForColourSingleCar() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getRegistrationNumbersForColour("parkinglot", "white");
	    assertEquals("Allocated slot number: 1\nv1\n", outContent.toString());
	  }

	  @Test
	  public void testGetRegistrationNumbersForColourMoreThanSingleCar() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.park("parkinglot", "v2", "white");
	    parkingLotManager.getRegistrationNumbersForColour("parkinglot", "white");
	    assertEquals("Allocated slot number: 1\nAllocated slot number: 2\nv1, v2\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumbersForColourParkingLotNotPresent() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.getStatus("parkinglot1");
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumbersForColourColourNotPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getSlotNumbersForColour("parkinglot", "black");
	    assertEquals("Allocated slot number: 1\nNot found\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumbersForColourSingleCar() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getSlotNumbersForColour("parkinglot", "white");
	    assertEquals("Allocated slot number: 1\n1\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumbersForColourMoreThanSingleCar() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.park("parkinglot", "v2", "white");
	    parkingLotManager.getSlotNumbersForColour("parkinglot", "white");
	    assertEquals("Allocated slot number: 1\nAllocated slot number: 2\n1, 2\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumberForRegistrationNumberParkingLotNotPresent() {
	    when(parkingLotRepository.findParkingLotByName("parkinglot1")).thenReturn(Optional.empty());
	    parkingLotManager.getStatus("parkinglot1");
	    assertEquals("Incorrect parking lot name. Please check\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumberForRegistrationNumberNotPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getSlotNumberForRegistrationNumber("parkinglot", "v2");
	    assertEquals("Allocated slot number: 1\nNot found\n", outContent.toString());
	  }

	  @Test
	  public void testGetSlotNumberForRegistrationNumberPresent() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    when(parkingLotRepository.findParkingLotByName("parkinglot")).thenReturn(Optional.of(parkingLot));
	    parkingLotManager.park("parkinglot", "v1", "white");
	    parkingLotManager.getSlotNumberForRegistrationNumber("parkinglot", "v1");
	    assertEquals("Allocated slot number: 1\n1\n", outContent.toString());
	  }


	  
	  

}

