package com.christianj98.online_store.view;

import com.christianj98.online_store.dto.LoginRequestDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        final var emailField = new TextField("Email");
        final var passwordField = new PasswordField("Password");

        Button loginButton = new Button("Zaloguj", event -> {
            String email = emailField.getValue();
            String password = passwordField.getValue();

            if (email.isEmpty() || password.isEmpty()) {
                Notification.show("Wprowadź dane logowania", 3000, Notification.Position.MIDDLE);
                return;
            }

            try {
                var loginRequestDto = new LoginRequestDto(email, password);

                WebClient webClient = WebClient.builder()
                        .baseUrl("http://localhost:8080")
                        .build();

                String response = webClient.post()
                        .uri("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(loginRequestDto)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                Notification.show("Zalogowano pomyślnie! Odpowiedź serwera: " + response,
                        3000, Notification.Position.MIDDLE);

            } catch (WebClientResponseException exception) {
                Notification.show("Błąd logowania: " + exception.getResponseBodyAsString(),
                        3000, Notification.Position.MIDDLE);
            } catch (Exception e) {
                Notification.show("Wystąpił błąd: " + e.getMessage(),
                        3000, Notification.Position.MIDDLE);
            }
        });

        add(emailField, passwordField, loginButton);
    }
}
