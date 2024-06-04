package com.projekt.security.data;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.projekt.security.entities.Role;
import com.projekt.security.entities.User;
import com.projekt.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadUsers implements CommandLineRunner {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private void createUsers() {
        User admin = new User(1L, "admin", passwordEncoder.encode("admin"), Role.ADMIN);
        User user1 = new User(2L, "user1", passwordEncoder.encode("user1"), Role.USER);
        repository.saveAll(List.of(admin, user1));
        List<User> users = repository.findAll();
        System.out.println("All users: " + users);
    }

    @Override
    public void run(String... args) throws Exception {
        createUsers();
    }
}