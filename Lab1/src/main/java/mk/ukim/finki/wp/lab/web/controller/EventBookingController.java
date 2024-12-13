package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }
    @GetMapping
    public String eventBooking() {
        return "redirect:/events";
    }
    @PostMapping
    public String showEventBooking(@RequestParam Long eventID, @RequestParam String attendName, @RequestParam int numTickets, HttpServletRequest req,Model model) {
        String address = req.getRemoteAddr();
        if(this.eventService.getEvent(eventID).isPresent()) {
            Event event = this.eventService.getEvent(eventID).get();
            EventBooking book = this.eventBookingService.placeBooking(event,attendName, address, numTickets);
            model.addAttribute("booking", book);
            return "bookingConfirmation";
        }
        return "redirect:/eventBooking?error=EventConfirmationNotFound";
    }
}
