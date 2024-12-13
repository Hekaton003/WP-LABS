package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final ConversionService conversionService;
    public EventController(EventService eventService, LocationService locationService, ConversionService conversionService) {
        this.eventService = eventService;
        this.conversionService = conversionService;
        this.locationService = locationService;
    }
    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("hasError",true);
            model.addAttribute("error", error);
        }
        model.addAttribute("events",this.eventService.listAll());
        model.addAttribute("locations",this.locationService.findAll());
        return "listEvents";
    }
    @PostMapping()
    public String getSearchEventsPage(@RequestParam(required = false) String eventNameSearch,@RequestParam(required = false) String minRating,@RequestParam(required = false)Long locationId,Model model){
        Integer num=0;
        if(minRating != null && !minRating.isEmpty()){
            num = this.conversionService.convert(minRating,Integer.class);
        }
        List<Event>eventList = this.eventService.searchEvents(eventNameSearch,num);
        if (locationId != null) {
            List<Event> eventList2 = this.eventService.findByLocatiion(locationId);
            eventList = eventList.stream().filter(eventList2::contains).collect(Collectors.toList());
        }
        model.addAttribute("events",eventList);
        model.addAttribute("locations",this.locationService.findAll());
        return "listEvents";
    }
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        this.eventService.deleteEvent(id);
        return "redirect:/events";
    }
    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model){
        if (this.eventService.getEvent(id).isPresent()) {
            Event event = this.eventService.getEvent(id).get();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("locations",locations);
            model.addAttribute("event",event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }
    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations",locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String popularityScore,
                              @RequestParam Long  locationId) {
        double popularityScoreDouble = Double.parseDouble(popularityScore);
        this.eventService.saveEvent(name,description,popularityScoreDouble,locationId);
        return "redirect:/events";
    }
}
