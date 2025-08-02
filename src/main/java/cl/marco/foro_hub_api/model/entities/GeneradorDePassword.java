package cl.marco.foro_hub_api.model.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorDePassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345"; // La contraseña que quieres usar
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
