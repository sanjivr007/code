package observerPattern;

public interface Subject {

	public void register(Client o);
	
	public void unregister(Client o);
	
	public void notifyClient();

}
