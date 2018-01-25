package statePattern;

public class ErrorState implements Nodestate {
	Machine machine;

	public ErrorState(Machine newmachine){
		machine=newmachine;
		
	}
	@Override
	public void insertCode(String pin) {
		if(machine.getPin().equals(pin)){
			machine.setNodeState(machine.getCorrectState());
			machine.enteredPinStatus=true;
		}
		else{
			System.out.println("PinEntred is wrong");
			machine.enteredPinStatus=false;
			
			
		}
	
	}

}
