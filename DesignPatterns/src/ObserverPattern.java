import java.util.ArrayList;
import java.util.Collections;

public class ObserverPattern {
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(2);
		System.out.println(Collections.frequency(list, 2));
		
		Subject observable = new Subject();
		
		new SpecificObserver(observable);
		SpecificObserver o2 = new SpecificObserver(observable);
		
		System.out.println("--- --- --- FIRST REPORT; VALUE = 0 --- --- --- ");
		
		for(Observer o : observable.getObservers()) {
			((SpecificObserver) o).act();
		}
		
		observable.setData(3);
		
		System.out.println("--- --- --- SECOND REPORT; VALUE = 3 --- --- --- ");
		
		for(Observer o : observable.getObservers()) {
			((SpecificObserver) o).act();
		}
		
		System.out.println("--- --- --- REGISTER AND UNREGISTER --- --- --- ");

		o2.unregister();
		System.out.println("Is o2 listening? " + o2.isObserving());
		o2.register(observable);
		System.out.println("Is o2 listening? " + o2.isObserving());
		observable.setData(300);
		
		System.out.println("--- --- --- THIRD REPORT; VALUE = 300 --- --- --- ");
		
		for(Observer o : observable.getObservers()) {
			((SpecificObserver) o).act();
		}
	}

}

interface Observer {
	void update(int data);
}

interface Observable {
	void register(Observer o);
	void unregister(Observer o);
	void notifyObservers();
}

class Subject implements Observable {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	private int data;
	
	public void register(Observer o) {
		// if the observer is not already registered
		if(observers.indexOf(o) == -1) {
			observers.add(o);
		}
	}

	public void unregister(Observer o) {
		int index = observers.indexOf(o);
		// if the observer is registered
		if(index != -1) {
			observers.remove(index);
		}
	}

	public void notifyObservers() {
		for(Observer o : observers) {
			o.update(data);
		}
	}
	
	public void setData(int data) {
		this.data = data;
		notifyObservers();
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
	
}

class SpecificObserver implements Observer {
	
	private int data;
	
	private static int observerIDTracker;
	private int observerID;
	
	private Observable observable;
	
	SpecificObserver(Observable o) {
		observerID = ++observerIDTracker;
		observable = o;
		observable.register(this);
	}
	
	public void update(int data) {
		this.data = data;
	}
	
	public void act() {
		System.out.println("Observer " + observerID + " reporting: ");
		System.out.println("Latest data: " + data);
	}
	
	public void register(Observable o) {
		observable = o;
		observable.register(this);
	}
	
	public void unregister() {
		observable.unregister(this);
		observable = null;
	}
	
	public boolean isObserving() {
		return observable != null;
	}
	
}