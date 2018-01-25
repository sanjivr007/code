package main.test.parkinglot.model;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import main.java.parkinglot.core.datastore.IDataStore;
import main.java.parkinglot.core.parkinglot.ParkingLot;
import main.java.parkinglot.core.parkinglot.ParkingLotRepository;

public class ParkingLotRepositoryTest {


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	  private ParkingLotRepository parkingLotRepository;
	  @Mock
	  private IDataStore dataStore;

	  @Before
	  public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    System.setOut(new PrintStream(outContent));
	    parkingLotRepository = new ParkingLotRepository(dataStore);
	  }

	  @Test
	  public void createParkingLotTest() {
	    ParkingLot parkingLot = new ParkingLot("parkinglot", 6);
	    parkingLotRepository.createParkingLot(parkingLot);
	    verify(dataStore, times(1)).addParkingLot(parkingLot);
	  }

	  @Test
	  public void findParkingLotByNameTest() {
	    parkingLotRepository.findParkingLotByName("parkinglot");
	    verify(dataStore, times(1)).getParkingLotByName("parkinglot");
	  }

	}
