package main.java.parkinglot.core.vehicle;

public class Vehicle {
	private int id;
	  private String registrationNumber;
	  private String colour;
	  public Vehicle(String registrationNumber, String colour) {
	    this.registrationNumber = registrationNumber;
	    this.colour = colour;
	  }

	  public String getRegistrationNumber() {
	    return registrationNumber;
	  }

	  public String getColour() {
	    return colour;
	  }

}
