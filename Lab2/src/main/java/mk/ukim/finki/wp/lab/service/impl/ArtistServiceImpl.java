package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.inmemory.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepositoryJpa artistRepository=null;
    public ArtistServiceImpl(ArtistRepositoryJpa artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Override
    public List<Artist> listArtists() {
        return this.artistRepository.findAll();
    }
    @Override
    public Artist findById(Long id) {
        return this.artistRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Artist"));
    }
}
