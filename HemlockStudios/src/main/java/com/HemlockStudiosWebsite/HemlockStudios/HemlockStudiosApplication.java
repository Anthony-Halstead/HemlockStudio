package com.HemlockStudiosWebsite.HemlockStudios;

import java.util.HashSet;
import java.util.Set;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.HemlockStudiosWebsite.entity.Role;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.repo.RoleRepo;
import com.HemlockStudiosWebsite.repo.UserRepo;



@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "com.HemlockStudiosWebsite")
public class  HemlockStudiosApplication {
    
public static void main(String[] args) {
SpringApplication.run(HemlockStudiosApplication.class, args);
}

@Bean
CommandLineRunner run(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder ){
return args ->{
if(roleRepo.findByAuthority("ADMIN").isPresent()) return;
Role adminRole = roleRepo.save(new Role("ADMIN"));
roleRepo.save(new Role("USER"));
Set<Role> roles = new HashSet<>();
roles.add(adminRole);
User admin = new User(1, "admin", passwordEncoder.encode("password"), "admin@HemlockStudios.com", roles);
admin.setEmailConfirmed(true);
userRepo.save(admin);
};
}
}

