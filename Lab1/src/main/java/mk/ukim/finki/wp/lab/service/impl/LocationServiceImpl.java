package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.inmemory.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepositoryJpa;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepositoryJpa locationRepository;
    public LocationServiceImpl(LocationRepositoryJpa locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

}
