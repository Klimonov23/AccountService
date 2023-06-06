package com.sdi.notificationservice.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class NotificationSettings {
    @NotNull
    private Boolean active;

    @NotNull
    private Frequency frequency;

    private Date lastNotified;
}
