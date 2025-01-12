package org.youcode.trackprocraservice.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Aspect // Active l'AOP
@Configuration // Marque la classe comme une configuration Spring
public class PasswordHashAspect {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Intercepte les méthodes annotées avec @HashPassword.
     * Hache le mot de passe avant de l'enregistrer.
     */

    @Around("@annotation(HashPassword) && args(password)")
    public Object hashPassword(ProceedingJoinPoint joinPoint, String password) throws Throwable {
        // Hache le mot de passe
        String hashedPassword = passwordEncoder.encode(password);
        // Passe le mot de passe haché à la méthode originale
        return joinPoint.proceed(new Object[]{hashedPassword});
    }

    /**
     * Vérifie si un mot de passe correspond à un hachage.
     */
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
