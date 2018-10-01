/**
 * Car models car in a passenger train.
 *
 * @author L.S. Marshall, SCE
 * @version 1.01 October 9, 2007
 * @version 1.2 January 2018 - printTicket now returns String, method uses
 *          getId() convention
 */

public class Car {
	/** This car's identifier. */
	private int id;

	/**
	 * true == this car is a business-class car, false == this car is an
	 * economy-class car.
	 */
	private boolean businessClass;

	public static final double BUSINESS_SEAT_COST = 125.0;
	public static final double ECONOMY_SEAT_COST = 50.0;

	/** The number of seats in a car. */
	public static final int ECONOMY_SEATS = 40;
	public static final int BUSINESS_SEATS = 30;

	/** The list of this car's seats. */
	private Seat[] seats;

	/**
	 * Constructs a new Car object with the specified id. If parameter
	 * isBusinessClass is true, the car is a business-class car. If parameter
	 * isBusinessClass is false, the car is an economy-class car.
	 */
	public Car(int carId, boolean isBusinessClass) {
		id = carId;
		businessClass = isBusinessClass;
		int numSeats;
		double cost;
		if (businessClass) {
			numSeats = BUSINESS_SEATS;
			cost = BUSINESS_SEAT_COST;
		} else {
			numSeats = ECONOMY_SEATS;
			cost = ECONOMY_SEAT_COST;
		}
		seats = new Seat[numSeats];
		for (int i = 0; i < seats.length; ++i) {
			seats[i] = new Seat(i+1,cost);
		}
	}

	public int getNumberOfSeats() {
		return seats.length;
	};

	public double getCost(int seatNo) {
		return seats[seatNo].getPrice();
	}

	public boolean isBooked(int seatNo) {
		return seats[seatNo].isBooked();
	}

	public int getNumberofFreeSeats() {
		int nfree = 0;
		for (int i = 0; i < seats.length; ++i) {
			if(!seats[i].isBooked()) nfree++;
		}
		return nfree;
	}

	/**
	 * Returns true if this is a business-class car, false if this is an
	 * economy-class car.
	 * 
	 * @return
	 */
	public boolean isBusinessClass() {
		return businessClass;
	}

	/**
	 * Returns the id of this car.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method is called when the specified seat has been booked, to print a
	 * ticket for that seat.
	 * 
	 * @return Returns a string representation of the ticket
	 */
	private String printTicket(int seatNo) {
		return seats[seatNo].toString();
	}

	/**
	 * Attempts to book a seat. If successful, this method prints a String
	 * containing the ticket If no seats are available, this method returns null.
	 * Throws IllegalStateException if internal inconsistency found. (exception
	 * optional)
	 */
	public String bookNextSeat() {
		for (int i = 0; i < seats.length; ++i) {
			if(!seats[i].isBooked()) {
				seats[i].book();
				return printTicket(i);
			}
		}
		return null;
	}

	/**
	 * Cancels the booking for the specified seat, which must be between 1 and the
	 * maximum number of seats in the car. If the seat number is valid and if the
	 * seat has been reserved, this method cancels the booking for that seat and
	 * returns true. If the seat number is not valid, this method returns false. If
	 * the seat number is valid, but the seat has not been reserved, this method
	 * returns false. Throws IllegalStateException if internal inconsistency found.
	 * (exception optional)
	 */
	public boolean cancelSeat(int seatNo) {
		if (seatNo >= 1 && seatNo <= getNumberOfSeats()) {
			return seats[seatNo-1].cancelBooking();
		}
		return false;
	}

	public String toString() {
		return "Car " + id + " : " + getNumberofFreeSeats() + " free, $" + getCost(1);
	}
}
