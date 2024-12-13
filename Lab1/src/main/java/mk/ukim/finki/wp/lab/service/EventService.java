package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,int num);
    void deleteEvent(Long id);
    Optional<Event> getEvent(Long id);
    List<Event> findByLocatiion(Long locationID);
    Optional<Event>saveEvent(String name, String description, double popularityScore,Long location);

}
