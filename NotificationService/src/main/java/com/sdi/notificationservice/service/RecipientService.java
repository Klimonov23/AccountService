package com.sdi.notificationservice.service;

import com.sdi.notificationservice.domain.NotificationType;
import com.sdi.notificationservice.domain.Recipient;

import java.util.List;

public interface RecipientService {
    Recipient findRecipientByName(String accountName);

    List<Recipient> findReadyToNotify(NotificationType type);

    Recipient save(String accountName, Recipient recipient);

    void markNotified(NotificationType type, Recipient recipient);

}
