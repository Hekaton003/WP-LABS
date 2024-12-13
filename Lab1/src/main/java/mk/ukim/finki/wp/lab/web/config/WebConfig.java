package mk.ukim.finki.wp.lab.web.config;

import mk.ukim.finki.wp.lab.model.IntegerConvertor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IntegerConvertor());
    }
}
