package com.sdi.notificationservice.controller;

import com.sdi.notificationservice.domain.Recipient;
import com.sdi.notificationservice.service.RecipientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/recipients")
public class RecipientController {


    @Autowired
    private RecipientService recipientService;

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public Object getCurrentNotificationsSettings(Principal principal) {
        return recipientService.findRecipientByName(principal.getName());
    }

    @RequestMapping(path = "/current", method = RequestMethod.PUT)
    public Object saveCurrentNotificationsSettings(Principal principal, @Valid @RequestBody Recipient recipient) {
        return recipientService.save(principal.getName(), recipient);
    }
}
