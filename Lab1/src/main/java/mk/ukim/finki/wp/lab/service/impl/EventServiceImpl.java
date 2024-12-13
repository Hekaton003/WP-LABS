package mk.ukim.finki.wp.lab.service.impl;


import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.inmemory.EventRepository;
import mk.ukim.finki.wp.lab.repository.inmemory.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepositoryJpa;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.web.exceptions.LocationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.service.specification.SpecificationFilter.*;

@Service
public class EventServiceImpl implements EventService {

    private EventRepositoryJpa eventRepository =null;
    private LocationRepositoryJpa locationRepository =null;
    public EventServiceImpl(EventRepositoryJpa eventRepository,LocationRepositoryJpa locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,int num) {
        Specification<Event> specification = Specification.where(filterContainsText(Event.class,"name",text))
                .and(greaterThan(Event.class,"popularityScore",num));
        return this.eventRepository.findAll(specification);
    }

    @Override
    public void deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> getEvent(Long id) {
      return this.eventRepository.findById(id);
    }

    @Override
    public List<Event> findByLocatiion(Long locationID) {
        return this.eventRepository.findAllByLocation_Id(locationID);
    }

    @Override
    @Transactional
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Long location) {
        Location location1 = this.locationRepository.findById(location).orElseThrow(()->new LocationException(location));
        this.eventRepository.deleteByName(name);
        Event event = new Event(name, description, popularityScore, location1);
        return Optional.of(this.eventRepository.save(event));
    }
}
