package com.bootcamp.signuplogin.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {
        String password = "Kpjenkins$321";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        System.out.println("$2a$10$0oQwar1al3qdZojsISkpcuyfbyL0MXxFJ.KMankFHQEGVpB.k5TNW");
        boolean isMatch = passwordEncoder.matches(password, hashedPassword);
        System.out.println(isMatch);

    }
}