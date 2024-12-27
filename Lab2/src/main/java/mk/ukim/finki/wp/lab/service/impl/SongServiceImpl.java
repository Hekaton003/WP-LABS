package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Price;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.web.exceptions.AlbumException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.service.specification.SpecificationFilter.filterEquals;
import static mk.ukim.finki.wp.lab.service.specification.SpecificationFilter.filterEqualsV;

@Service
public class SongServiceImpl implements SongService {
    private SongRepositoryJpa songRepository = null;
    private ArtistRepositoryJpa artistRepository = null;
    private AlbumRepositoryJpa albumRepository = null;
    public SongServiceImpl(SongRepositoryJpa songRepository, AlbumRepositoryJpa albumRepository, ArtistRepositoryJpa artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;

    }
    @Override
    public List<Song> listSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Long artistId, Long songId) {
        Song song = this.songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));
        Artist artist = this.artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + artistId));

        if (song.getPerformers().stream().noneMatch(a -> a.getId().equals(artistId))) {
            song.getPerformers().add(artist);
        }
        this.songRepository.save(song);
    }

    @Override
    public Optional<Song> findById(Long Id) {
        return this.songRepository.findById(Id);
    }

    @Override
    @Transactional
    public Song addSong(String trackId, String title, String genre, Integer releaseYear, Long albumID, Price price) {
        if(trackId==null || title==null || genre==null  || releaseYear==null || price==null){
            throw new IllegalArgumentException();
        }
        this.songRepository.deleteByTrackId(trackId);
        Album album = albumRepository.findById(albumID).orElseThrow(()->new AlbumException("Not Found"));
        Song song = new Song(trackId, title, genre, releaseYear, album,price);
        return this.songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return this.songRepository.findAllByAlbumId(albumId);
    }
    @Override
    public Song findByTrackId(String songID) {
        return this.songRepository.findByTrackId(songID);
    }

    @Override
    public List<Song> findBySearch(String title, String genre, int releaseYear) {
        Specification<Song> specification = Specification.where(filterEquals(Song.class, "title", title))
                .and(filterEquals(Song.class, "genre", genre));
        if (releaseYear != -1) {
            specification = Specification.where(filterEqualsV(Song.class,"releaseYear",releaseYear));
        }
        return this.songRepository.findAll(specification);
    }

}
