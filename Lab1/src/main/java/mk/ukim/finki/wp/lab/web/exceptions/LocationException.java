package mk.ukim.finki.wp.lab.web.exceptions;

public class LocationException extends RuntimeException {
    public LocationException(Long id) {

      super("The location with id " + id + " was not found");
    }
}
