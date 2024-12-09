package com.kullad.services;

public interface EmailService {
    public void sendMail(String to, String subject, String body);
}
