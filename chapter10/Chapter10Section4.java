package chapter10;

import chapter10.model.User;
import chapter10.service.EmailProvider;
import chapter10.service.EmailSender;
import chapter10.service.MakeMoreFriendsEmailProvider;
import chapter10.service.VerifyYourEmailAddressEmailProvider;

import java.util.Arrays;
import java.util.List;

/**
 * Strategy Pattern
 * - 대표적인 행동 패턴
 * - 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 해준다.
 * - 전략들을 캡슐화하여 간단하게 교체할 수 있게 해준다.
 */
public class Chapter10Section4 {
    public static void main(String[] args) {
        User user1 = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@gmail.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();
        User user2 = User.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@gmail.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(211, 212, 213);
                }).build();
        User user3 = User.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "alice@gmail.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }).build();
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        // 전략 1. verify email
        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);  // 전략 설정
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        // 전략 2. make more friends
        emailSender.setEmailProvider(makeMoreFriendsEmailProvider); // 전략 설정
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);

        // 실시간으로 클래스 만들지 않고 하기(EmailProvider가 functional interface(메서드가 1개인 interface) 이므로 가능)
        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);
    }
}
