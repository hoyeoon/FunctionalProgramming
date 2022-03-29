package chapter10.service;

import chapter10.model.User;

public interface EmailProvider {
    String getEmail(User user);
}
