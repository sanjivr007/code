package observerPattern;

public class StockObserver implements Client{
	
	String payload;
	
	 private static int observerIDTracker = 0;
     // Used to track the observers

	 private int observerID;

	// Will hold reference to the StockGrabber object

	 private Subject stockGrabber;
	 
	 public StockObserver(Subject stockGrabber){
		 this.stockGrabber=stockGrabber;
		 this.observerID=++observerIDTracker;
		 System.out.println("New Client " + this.observerID);
		 stockGrabber.register(this);
		 
	 }


	@Override
	public void pushData(String payload) {
		this.payload=payload;
		printThePayload();
	
		
	}
	private void printThePayload() {
		System.out.println(observerID + "\npayload: "+payload );
		
	}

}
