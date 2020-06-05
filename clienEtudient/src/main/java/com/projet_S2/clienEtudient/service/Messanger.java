package com.projet_S2.clienEtudient.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class Messanger {




    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACdb3e3ccd1216d379446c86c178f54872";
    public static final String AUTH_TOKEN = "e21bff790cc215587a178ce7565d0c1a";

    public void sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21623500770"),
                new PhoneNumber("+15108673627"),
                "3aslama ya ejma3a :)").create();

        System.out.println(message.getSid());
    }
};


