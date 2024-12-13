package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;
    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }
    @GetMapping("/add/{id}")
    public String getArtistPage(@PathVariable Long id, @RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("error", error);
        }
        if(this.songService.findById(id).isPresent()){
            Song song = this.songService.findById(id).get();
            List<Artist> artists = this.artistService.listArtists();
            model.addAttribute("artists", artists);
            model.addAttribute("song", song);
            return "artistsList";
        }
        return "redirect:/songs?error=SongNotFound";
    }
    @PostMapping("/addArtist")
    public String addArtist(@RequestParam Long artistId,@RequestParam Long songID){
        this.songService.addArtistToSong(artistId, songID);
        return "redirect:/songs";
    }
}
