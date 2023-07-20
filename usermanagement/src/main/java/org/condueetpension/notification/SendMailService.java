package org.condueetpension.notification;

import org.condueetpension.data.models.User;

public interface SendMailService {
    void send(User user, String token);
}
