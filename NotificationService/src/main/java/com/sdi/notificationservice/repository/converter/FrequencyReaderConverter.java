package com.sdi.notificationservice.repository.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import com.sdi.notificationservice.domain.Frequency;
import org.springframework.stereotype.Component;

@Component
public class FrequencyReaderConverter implements Converter<Integer, Frequency> {

    @Override
    public Frequency convert(@NotNull Integer days) {
        return Frequency.withDays(days);
    }
}
