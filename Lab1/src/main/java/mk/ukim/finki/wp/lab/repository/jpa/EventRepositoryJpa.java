package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositoryJpa extends EventSpecification<Event,Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    void deleteByName(String eventName);
}
