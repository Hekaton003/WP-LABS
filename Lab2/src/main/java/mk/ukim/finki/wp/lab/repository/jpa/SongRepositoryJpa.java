package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepositoryJpa extends JpaRepository<Song, Long> {
    Song findByTrackId(String trackId);
    void deleteByTrackId(String trackId);
    List<Song> findAllByAlbumId(Long albumId);
}
