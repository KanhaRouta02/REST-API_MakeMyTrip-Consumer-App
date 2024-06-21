package in.kanha.service;

import in.kanha.request.Passenger;
import in.kanha.response.Ticket;

public interface MakeMyTripService {

	public Ticket bookTicket(Passenger passenger);
	
	public Ticket getTicket(Integer ticketNumber);

}
