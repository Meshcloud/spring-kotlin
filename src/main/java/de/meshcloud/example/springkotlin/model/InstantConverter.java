package de.meshcloud.example.springkotlin.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.util.Date;

@Converter(autoApply = true)
public class InstantConverter implements AttributeConverter<Instant, Date> {

    public Date convertToDatabaseColumn(Instant instant) {
        return instant != null ? Date.from(instant) : null;
    }

    public Instant convertToEntityAttribute(Date value) {
        return value != null ? value.toInstant() : null;
    }
}