package com.christianj98.online_store.view;

import com.christianj98.online_store.dto.RegisterRequestDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Route("register")
public class RegisterView extends VerticalLayout {

    public RegisterView() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        final var emailField = new TextField("Email");
        final var passwordField = new PasswordField("Password");

        Button registerButton = new Button("Zarejestruj", event -> {
            final String email = emailField.getValue();
            final String password = passwordField.getValue();

            if (email.isEmpty() || password.isEmpty()) {
                Notification.show("Wprowadź dane do rejestracji", 3000, Notification.Position.MIDDLE);
                return;
            }

            try {
                final var registerRequestDto = new RegisterRequestDto(email, password);

                final var webClient = WebClient.builder()
                        .baseUrl("http://localhost:8080")
                        .build();

                String response = webClient.post()
                        .uri("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(registerRequestDto)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                Notification.show("Zarejestrowano pomyślnie! Odpowiedź serwera: " + response,
                        3000, Notification.Position.MIDDLE);

            } catch (WebClientResponseException exception) {
                Notification.show("Błąd rejestracji: " + exception.getResponseBodyAsString(),
                        3000, Notification.Position.MIDDLE);
            } catch (Exception e) {
                Notification.show("Wystąpił błąd: " + e.getMessage(),
                        3000, Notification.Position.MIDDLE);
            }
        });

        emailField.setWidth("300px");
        passwordField.setWidth("300px");
        registerButton.setWidth("300px");
        registerButton.getStyle().set("cursor", "pointer");
        add(emailField, passwordField, registerButton);
    }
}
