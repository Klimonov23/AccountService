package com.sdi.notificationservice.service;

import com.sdi.notificationservice.domain.NotificationType;
import com.sdi.notificationservice.domain.Recipient;
import com.sdi.notificationservice.repository.RecipientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
@Service
public class RecipientServiceImpl implements RecipientService{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecipientRepository repository;

    @Override
    public Recipient findRecipientByName(String accountName) {
        Assert.hasLength(accountName,"such name  does not exist");
        return repository.findByAccountName(accountName);
    }

    @Override
    public List<Recipient> findReadyToNotify(NotificationType type) {
        switch (type){
            case BACKUP -> {
                return repository.findReadyForBackup();
            }
            case REMIND -> {
                return repository.findReadyForRemind();
            }
            default -> {
                throw new IllegalArgumentException("There are only 2 type of notification");
            }
        }
    }

    @Override
    public Recipient save(String accountName, Recipient recipient) {
        recipient.setAccountName(accountName);
        recipient.getScheduledNotifications().values()
                .forEach(settings -> {
                    if (settings.getLastNotified() == null) settings.setLastNotified(new Date());
                });
        repository.save(recipient);

        log.info("recipient {} settings has been updated", recipient);

        return recipient;
    }

    @Override
    public void markNotified(NotificationType type, Recipient recipient) {
        recipient.getScheduledNotifications().get(type).setLastNotified(new Date());
        repository.save(recipient);
    }
}
