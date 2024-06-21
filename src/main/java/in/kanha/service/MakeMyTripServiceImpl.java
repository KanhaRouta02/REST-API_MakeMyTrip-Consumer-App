package in.kanha.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import in.kanha.request.Passenger;
import in.kanha.response.Ticket;

@Service
public class MakeMyTripServiceImpl implements MakeMyTripService{

	private String BOOK_TICKET_URL="http://localhost:8080/book";
	
	private String GET_TICKET_URL="http://localhost:8080/ticket{ticketNumber}";
	
	@Override
	public Ticket bookTicket(Passenger passenger) {
		
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<Ticket> entity = rt.postForEntity(BOOK_TICKET_URL, passenger, Ticket.class);
//		Ticket ticket = entity.getBody();
//		return ticket;
		
		WebClient client = WebClient.create();
		Ticket ticket = client.post()
		      .uri(BOOK_TICKET_URL)
		      .bodyValue(passenger)
		      .retrieve()
		      .bodyToMono(Ticket.class)
		      .block();
		return ticket;
	}

	@Override
	public Ticket getTicket(Integer ticketNumber) {
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<Ticket> entity = rt.getForEntity(GET_TICKET_URL, Ticket.class, ticketNumber);
//        Ticket ticket = entity.getBody();
		  WebClient client = WebClient.create();
		  Ticket ticket = client.get()
				  .uri(GET_TICKET_URL, ticketNumber)
				  .retrieve()
				  .bodyToMono(Ticket.class)
				  .block();
		return ticket;
	}

	
	
}
