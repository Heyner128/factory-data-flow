package me.heyner.manusim.core.persistence

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.Duration

@Converter(autoApply = false)
class DurationInMillisecondsConverter : AttributeConverter<Duration, Long> {
    override fun convertToDatabaseColumn(attribute: Duration?): Long? = attribute?.toMillis()

    override fun convertToEntityAttribute(dbData: Long?): Duration? = dbData?.let(Duration::ofMillis)
}
