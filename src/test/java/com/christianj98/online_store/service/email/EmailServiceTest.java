package com.christianj98.online_store.service.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> simpleMailMessageCaptor;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void shouldSendEmail() {
        // given
        final String to = "email@gmail.com";
        final String subject = "Important email";
        final String emailBody = "Email content";

        // when
        emailService.sendEmail(to, subject, emailBody);

        // then
        verify(mailSender).send(simpleMailMessageCaptor.capture());
        final SimpleMailMessage capturedMailMessage = simpleMailMessageCaptor.getValue();
        assertThat(capturedMailMessage.getTo()).containsOnly(to);
        assertThat(capturedMailMessage.getSubject()).isEqualTo(subject);
        assertThat(capturedMailMessage.getText()).isEqualTo(emailBody);
    }
}
