package de.meshcloud.example.springkotlin.model

import java.time.Instant
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateConverter : AttributeConverter<Instant, Date> {

  override fun convertToDatabaseColumn(instant: Instant?): Date? {
    return instant?.let { Date.from(it) }
  }

  override fun convertToEntityAttribute(value: Date?): Instant? {
    return value?.run { toInstant() }
  }
}