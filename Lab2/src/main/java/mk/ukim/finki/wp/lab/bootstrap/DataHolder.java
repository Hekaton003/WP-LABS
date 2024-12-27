package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Price;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Song>songs = new ArrayList<>();
    public static List<Artist>artists = new ArrayList<>();
    public static List<Album>albums = new ArrayList<>();
    @PostConstruct
    public void init() {
        albums.add(new Album("album1","rock","2009"));
        albums.add(new Album("album2","jazz","2016"));
        albums.add(new Album("album3","pop","2039"));
        albums.add(new Album("album4","classic","2029"));
        albums.add(new Album("album5","rap","2019"));
        songs.add(new Song("1a","Thunder","rock",2013,albums.get(0),new Price(150,"$")));
        songs.add(new Song("2b","Queen","pop",2015,albums.get(2),new Price(104.7,"$")));
        songs.add(new Song("3c","God's wrath","rock",2023,albums.get(0),new Price(30.5,"$")));
        songs.add(new Song("4x","Houston","jazz",2000,albums.get(1),new Price(350,"$")));
        songs.add(new Song("5y","Mozzart","clasic",1956,albums.get(3),new Price(150,"$")));
        artists.add(new Artist("Petko","Petkov","bio1"));
        artists.add(new Artist("Patrick","Bellard","bio2"));
        artists.add(new Artist("Bob","Stones","bio3"));
        artists.add(new Artist("David","Stick","bio4"));
        artists.add(new Artist("Melisa","Bryan","bio5"));
    }
}
