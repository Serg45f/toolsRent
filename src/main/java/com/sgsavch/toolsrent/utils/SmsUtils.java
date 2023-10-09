package com.sgsavch.toolsrent.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.twilio.rest.api.v2010.Account.creator;

public class SmsUtils {
    public static final String FROM_NUMBER = "+19789653217";
    public static final String SID_KEY = "";
    public static final String TOKEN_KEY = "";

    public static void sendSMS(String to, String messageBody) {
        Twilio.init(SID_KEY, TOKEN_KEY);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+" + to), new PhoneNumber(FROM_NUMBER), messageBody).create();
        System.out.println(message);
    }
}
