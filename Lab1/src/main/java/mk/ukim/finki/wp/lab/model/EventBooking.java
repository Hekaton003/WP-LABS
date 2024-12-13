package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class EventBooking {
    @Id
    private String attendeeName;
    @ManyToMany
    private List<Event> event;

    private String attendeeAddress;

    private Long numberOfTickets;

    public EventBooking(String attendeeName,String attendeeAddress,Long numberOfTickets) {
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
        this.event = new ArrayList<>();
    }
}
