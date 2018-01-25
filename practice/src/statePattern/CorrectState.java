package statePattern;

public class CorrectState implements Nodestate {

	Machine machine;

	public CorrectState(Machine newmachine){
		machine=newmachine;
		
	}
	
	@Override
	public void insertCode(String pin) {
	
			System.out.println("Already entered correct pin");
		
	}

}
