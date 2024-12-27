package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Price;
import mk.ukim.finki.wp.lab.model.Song;


import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    void addArtistToSong(Long artistId, Long songId);
    Optional<Song> findById(Long Id);
    Song addSong(String trackId, String title, String genre, Integer releaseYear, Long albumID, Price price);
    void deleteById(Long id);
    List<Song> findByAlbumId(Long albumId);
    Song findByTrackId(String songID);
    List<Song> findBySearch(String title,String genre,int releaseYear);
}
