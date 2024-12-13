package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EventBookingRepository {
    public Optional<EventBooking> findByName(String name){
        return DataHolder.eventBookings.stream().filter(e->e.getAttendeeName().equals(name)).findFirst();
    }
    public Optional<EventBooking> save(EventBooking eventBooking){
        DataHolder.eventBookings.add(eventBooking);
        return Optional.of(eventBooking);
    }
}
