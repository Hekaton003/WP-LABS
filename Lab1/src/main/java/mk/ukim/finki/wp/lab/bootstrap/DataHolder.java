package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> eventBookings = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();
    @PostConstruct
    public void init(){
        locations.add(new Location("loc1","adr1","569","des1Loc"));
        locations.add(new Location("loc2","adr2","459","des2Loc"));
        locations.add(new Location("loc3","adr3","89","des3Loc"));
        locations.add(new Location("loc4","adr4","56","des4Loc"));
        locations.add(new Location("loc5","adr5","874","des5Loc"));
        events.add(new Event("event1","event1Description",5.7,locations.get(4)));
        events.add(new Event("event2","event2Description",3.5,locations.get(0)));
        events.add(new Event("event3","event3Description",9.6,locations.get(2)));
        events.add(new Event("event4","event4Description",7.4,locations.get(1)));
        events.add(new Event("event5","event5Description",3.8,locations.get(3)));
    }
}
