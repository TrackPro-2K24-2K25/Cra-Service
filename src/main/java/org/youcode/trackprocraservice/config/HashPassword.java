package org.youcode.trackprocraservice.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Disponible à l'exécution
@Target(ElementType.METHOD) // Applicable aux méthodes
public @interface HashPassword {
}
