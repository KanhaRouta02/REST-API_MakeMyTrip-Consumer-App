package in.kanha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.kanha.exception.TicketNumberNotFoundException;
import in.kanha.exception.UserNotFoundException;
import in.kanha.request.Passenger;
import in.kanha.response.Ticket;
import in.kanha.service.MakeMyTripServiceImpl;

@Controller
public class MakeMyTripController {
	
	@Autowired
	private MakeMyTripServiceImpl makeMyTripServiceImpl;

	@GetMapping("/")
	public String loadTicketForm(Model model)
	{
		model.addAttribute("passenger", new Passenger());
		return "index";
	}
	
	@PostMapping("/save")
	public String bookTicket( @ModelAttribute("passenger") Passenger passenger, Model model)
	{
		System.out.println(passenger);
		Ticket bookTicket = makeMyTripServiceImpl.bookTicket(passenger);
		model.addAttribute("msg", "Your ticket booked with ID : "+bookTicket.getTicketNumber());
		return "index";
	}
	
	@GetMapping("/view-ticket")
	public String viewTicket(Model model)
	{
		model.addAttribute("ticket", new Ticket());
		return "ticketform";
	}
	
	@GetMapping("/get-ticket")
	public String getTicket(@RequestParam Integer ticketNumber,Model model)
	{
		
		Ticket ticket = makeMyTripServiceImpl.getTicket(ticketNumber);
		if(ticket.getTicketNumber() != null) {
			model.addAttribute("ticket", ticket);
			return "ticketdetails";
		}
		else throw new TicketNumberNotFoundException("No Ticket Booked on this TicketNumber..");
		
	}
	
}
