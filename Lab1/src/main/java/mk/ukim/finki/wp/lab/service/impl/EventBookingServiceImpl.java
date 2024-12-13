package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.inmemory.EventBookingRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventBookingRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepositoryJpa;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private EventBookingRepositoryJpa eventBookingRepository=null;
    public EventBookingServiceImpl(EventBookingRepositoryJpa eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(Event event,String attendeeName, String attendeeAddress, int numberOfTickets) {
        if (attendeeName == null || attendeeName.isEmpty() || attendeeAddress == null || attendeeAddress.isEmpty() || numberOfTickets <1) {
            throw new RuntimeException("Invalid booking event");
        }
        EventBooking eventBook = new EventBooking(attendeeName,attendeeAddress,(long)numberOfTickets);
        eventBook.getEvent().add(event);
        return this.eventBookingRepository.save(eventBook);
    }

    @Override
    public Optional<EventBooking> findBookingByAttendeeName(String attendeeName) {
        return this.eventBookingRepository.findById(attendeeName);
    }


}
