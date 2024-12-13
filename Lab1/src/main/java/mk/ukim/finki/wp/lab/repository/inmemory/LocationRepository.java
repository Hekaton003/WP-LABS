package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id){
        return DataHolder.locations.stream().filter(location -> location.getId().equals(id)).findFirst();
    }
}
