package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet",urlPatterns = "/servlet/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongService songService;
    public SongDetailsServlet(SpringTemplateEngine templateEngine,SongService songService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        Song song = (Song) req.getSession().getAttribute("song");
        Artist artist = (Artist) req.getSession().getAttribute("artist");
        this.songService.addArtistToSong(artist.getId(), song.getId());
        context.setVariable("Song", song);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
