package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet",urlPatterns = "/servlet/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    public EventBookingServlet(SpringTemplateEngine templateEngine, EventBookingService eventBookingService,EventService eventService) {
        this.templateEngine = templateEngine;
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        Long eventID =Long.parseLong(req.getParameter("eventID"));
        int number;
        try {
            number= Integer.parseInt(req.getParameter("numTickets"));
        } catch (NumberFormatException e) {
            number = 1; // Default to 1 if parsing fails
        }
        String atended = req.getParameter("attendName");
        if (this.eventService.getEvent(eventID).isPresent()){
            Event event = this.eventService.getEvent(eventID).get();
            EventBooking booking = this.eventBookingService.placeBooking(event,atended, req.getRemoteAddr(),number);
            context.setVariable("eventBookings",booking);
            templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
        }
    }
}
