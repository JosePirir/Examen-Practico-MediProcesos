package com.examen.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hash(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean verificar(String passwordPlano, String passwordHasheado) {
        return BCrypt.checkpw(passwordPlano, passwordHasheado);
    }
}