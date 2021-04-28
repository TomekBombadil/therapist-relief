package pl.waw.psychologmaja.therapistrelief.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeConverter implements Converter<String, LocalTime> {

    public LocalTime convert(String s) {
        return LocalTime.parse(s);
    }
}
