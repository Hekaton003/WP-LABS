package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventListServlet",urlPatterns = "/servlet/events")
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventService eventService;
    public EventListServlet(SpringTemplateEngine templateEngine, EventService eventService) {
        this.templateEngine = templateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("events",eventService.listAll());
        templateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String name = req.getParameter("eventName");
        int num;
        try {
            num = Integer.parseInt(req.getParameter("minRating"));

        }catch (NumberFormatException e){
            num = 0;
        }
        context.setVariable("events",eventService.searchEvents(name,num));
        templateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
