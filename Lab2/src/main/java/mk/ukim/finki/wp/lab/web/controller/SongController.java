package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Price;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ConversionService conversionService;
    public SongController(SongService songService, AlbumService albumService, ConversionService conversionService) {
        this.songService = songService;
        this.albumService = albumService;
        this.conversionService = conversionService;
    }
    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
            if(error != null && !error.isEmpty()){
                model.addAttribute("hasError", true);
                model.addAttribute("error", error);
            }
            List<Song> songs = songService.listSongs();
            model.addAttribute("songs", songs);
            model.addAttribute("albums",this.albumService.findAll());
            return "listSongs";
    }
    @PostMapping("/search")
    public String searchSongs(@RequestParam String albumID,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String genre,
                              @RequestParam(required = false) String releaseYear,
                              Model model){
        int year = -1;
        if(releaseYear != null && !releaseYear.isEmpty()){
            year = Integer.parseInt(releaseYear);
        }
        List<Song> songs = this.songService.findBySearch(title, genre, year);
        if(Long.parseLong(albumID) != 0){
            List<Song> songList = this.songService.findByAlbumId(Long.parseLong(albumID));
            songs = songs.stream().filter(songList::contains).collect(Collectors.toList());
        }
        model.addAttribute("songs", songs);
        model.addAttribute("albums", this.albumService.findAll());
        return "listSongs";
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam String price,
                           @RequestParam Long albumID){
        Price priceObj = conversionService.convert(price, Price.class);
        this.songService.addSong(trackId,title,genre,Integer.valueOf(releaseYear),albumID,priceObj);
        return "redirect:/songs";
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long id, Model model){
        if(this.songService.findById(id).isPresent()){
            Song song = this.songService.findById(id).get();
            model.addAttribute("song", song);
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("albums", albums);

            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model){
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }
    @GetMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) String releaseYear,
                           @RequestParam(required = false) String price,
                           @RequestParam(required = false) Long albumId){

        Optional<Song> Optionalsong = this.songService.findById(songId);
        if(Optionalsong.isPresent()){
            Song song = Optionalsong.get();
            if(title != null && !title.isEmpty()){
                song.setTitle(title);
            }
            if(genre != null && !genre.isEmpty()){
                song.setGenre(genre);
            }
            if(releaseYear!= null && !releaseYear.isEmpty()){
                int releaseYearInt = Integer.parseInt(releaseYear);
                song.setReleaseYear(releaseYearInt);
            }
            if(albumId == null){
                albumId = song.getAlbum().getId();
            }
            if(price != null && !price.isEmpty()){
                Price priceObj = conversionService.convert(price, Price.class);
                song.setPrice(priceObj);
            }
            this.songService.addSong(song.getTrackId(),song.getTitle(),song.getGenre(),song.getReleaseYear(),albumId,song.getPrice());
            return "redirect:/songs";
        }
        return "redirect:/songs?error=SongNotFound";
    }
}
