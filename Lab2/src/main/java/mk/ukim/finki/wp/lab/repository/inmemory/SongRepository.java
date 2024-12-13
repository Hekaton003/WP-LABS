package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songs;
    }
    public Optional<Song> findById(Long id){
        return DataHolder.songs.stream().filter(song -> song.getId().equals(id)).findFirst();
    }
    public  Song findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }
    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }
    public Song saveSong(Song song) {
        DataHolder.songs.removeIf(s->s.getTrackId().equals(song.getTrackId()));
        DataHolder.songs.add(song);
        return song;
    }
    public void deleteById(Long id){
        DataHolder.songs.removeIf(s->s.getId().equals(id));
    }
}
