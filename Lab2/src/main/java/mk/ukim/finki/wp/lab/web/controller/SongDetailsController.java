package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {
    private final SongService songService;
    public SongDetailsController(SongService songService) {
        this.songService = songService;
    }
    @GetMapping("/view/{id}")
    public String getSongDetails(@PathVariable Long id, Model model) {
        if(this.songService.findById(id).isPresent()) {
            Song song = this.songService.findById(id).get();
            model.addAttribute("song", song);
            return "songDetails";
        }
        return "redirect:/songs";
    }
}
