package org.condueetpension.notification;
import lombok.RequiredArgsConstructor;
import org.condueetpension.data.models.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService{
    private final JavaMailSender javaMailSender;
    @Override
    public void send(User user, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("condueetpension@gmail.com");
        mailMessage.setSubject("We are pleased to inform you that you have successfully registered"
        + "Enter the following digits to verify our account"+
                token +
                "Thank you for your time"+
                "At your service, "+
                "The Condueet Team"
        );
        javaMailSender.send(mailMessage);
    }
}
