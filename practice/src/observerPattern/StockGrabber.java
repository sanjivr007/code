package observerPattern;

import java.util.ArrayList;

public class StockGrabber implements Subject {
	
	private ArrayList<Client> clients;
	String payload;
	
	public StockGrabber(){
		clients = new ArrayList<Client>();
	}

	@Override
	public void register(Client o) {
		clients.add(o);
		}

	@Override
	public void unregister(Client o) {
		clients.remove(o);		
	}

	@Override
	public void notifyClient() {		
		for(Client client:clients){
			client.pushData(payload);
		}
		
	}
    public void setPayload(String payload){
		
		this.payload = payload;
		
		notifyClient();
		
	}
	


}
