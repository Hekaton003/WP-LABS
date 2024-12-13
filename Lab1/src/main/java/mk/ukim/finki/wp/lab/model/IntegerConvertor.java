package mk.ukim.finki.wp.lab.model;

import org.springframework.core.convert.converter.Converter;

public class IntegerConvertor implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }
}
