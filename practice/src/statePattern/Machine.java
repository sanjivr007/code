package statePattern;

public class Machine {
	Nodestate errorstate;
	Nodestate correctstate;
	Nodestate nostate;
	
	Nodestate nodestate;
	
	private String pin="1234";
	boolean enteredPinStatus = false;
	
	public Machine(){
		errorstate=new ErrorState(this);
		correctstate=new CorrectState(this);
		nostate = new Nostate(this);
		
		nodestate=nostate;
				
	}
	
	public String getPin(){return this.pin;}
	public boolean getenteredPinStatus(){return this.enteredPinStatus;}
	
	void setNodeState(Nodestate newnodestate){nodestate =newnodestate; }
	void insertCode(String code){nodestate.insertCode(code);}
	
	public Nodestate getErrorState(){return errorstate;}
	public Nodestate getCorrectState(){return correctstate; }
	public Nodestate getNoState(){return nostate; }
	
	

}
