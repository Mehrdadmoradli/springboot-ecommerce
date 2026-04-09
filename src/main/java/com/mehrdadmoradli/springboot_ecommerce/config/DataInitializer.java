package com.mehrdadmoradli.springboot_ecommerce.config;

import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import com.mehrdadmoradli.springboot_ecommerce.entity.Address;
import com.mehrdadmoradli.springboot_ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();
                Address adminAddress = new Address("England", "London", "Baker Street", "221");

                admin.setEmail("admin@springboot_ecommerce.de");
                admin.setPhoneNumber("004911111111111");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setFirstName("System");
                admin.setLastName("Admin");
                admin.addAddress(adminAddress);
                admin.setRoles(Set.of("ROLE_ADMIN"));

                userRepository.save(admin);

                System.out.println("Default admin user created");
            }
        };
    }
}