package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Price {
    double price;
    String currency;
    public String PriceFormat(){
        return String.format("%.2f %s", price, currency);
    }
    public Price(double price, String currency){
        this.price = price;
        this.currency = currency;
    }
}
