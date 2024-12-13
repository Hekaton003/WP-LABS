package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.Optional;

public interface EventBookingService {
    EventBooking placeBooking(Event event, String attendeeName, String attendeeAddress, int numberOfTickets);
    Optional<EventBooking> findBookingByAttendeeName(String attendeeName);
}
