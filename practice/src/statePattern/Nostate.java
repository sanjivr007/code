package statePattern;

public class Nostate implements Nodestate {
	
	Machine machine;

	public Nostate(Machine newmachine){
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
			machine.setNodeState(machine.getErrorState());
			
			
		}
		
	}

}
