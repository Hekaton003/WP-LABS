package mk.ukim.finki.wp.lab.model;

import org.springframework.core.convert.converter.Converter;

public class PriceConverter implements Converter<String,Price> {
    @Override
    public Price convert(String source) {
        String[] data = source.split(" ");
        return new Price(Double.parseDouble(data[0]), data[1]);
    }
}
