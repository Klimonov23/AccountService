package com.sdi.notificationservice.service;

import com.sdi.notificationservice.domain.NotificationType;
import com.sdi.notificationservice.domain.Recipient;
import jakarta.mail.MessagingException;


import java.io.IOException;

public interface EmailService {

    void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;
}
