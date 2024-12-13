package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class EventRepository {
    public List<Event> findAll(){
        return DataHolder.events;
    }
    public List<Event> searchEvents(String text,int num){
        String lowerCaseText = text.toLowerCase();
        List<Event> Selected =  DataHolder.events.stream().filter(e->e.getName().toLowerCase().contains(lowerCaseText) || e.getDescription().toLowerCase().contains(lowerCaseText)).toList();
        Selected = Selected.stream().filter(e->e.getPopularityScore() >= num).toList();
        return Selected;
    }
    public void deleteEvent(Long id){
        DataHolder.events.removeIf(e->e.getId().equals(id));
    }
    public Optional<Event> getEvent(Long id){
        return DataHolder.events.stream().filter(e->e.getId().equals(id)).findFirst();
    }
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Location location){
        DataHolder.events.removeIf(e->e.getName().equals(name));
        Event event = new Event(name, description, popularityScore, location);
        DataHolder.events.add(event);
        return Optional.of(event);
    }

}
