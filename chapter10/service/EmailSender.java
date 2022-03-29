package chapter10.service;

import chapter10.model.User;

public class EmailSender {
    private EmailProvider emailProvider;

    // 실시간으로 전략 교체 가능
    public EmailSender setEmailProvider(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
        return this;
    }

    public void sendEmail(User user) {
        String email = emailProvider.getEmail(user);
        System.out.println("Sending " + email);
    }
}
