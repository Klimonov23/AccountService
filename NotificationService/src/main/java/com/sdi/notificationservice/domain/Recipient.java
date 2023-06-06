package com.sdi.notificationservice.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "recipients")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Recipient {
    @Id

   private String accountName;

    @NotNull
    @Email
    private String email;

    @Valid
    private Map<NotificationType, NotificationSettings> scheduledNotifications;


}
