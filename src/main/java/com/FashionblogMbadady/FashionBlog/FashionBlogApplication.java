package com.FashionblogMbadady.FashionBlog;

import com.FashionblogMbadady.FashionBlog.entity.Role;
import com.FashionblogMbadady.FashionBlog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FashionBlogApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(FashionBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		to save the role in the database
//		Role adminRole = new Role();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//
//		Role userRole = new Role();
//		userRole.setName("ROLE_USER");
//		roleRepository.save(userRole);
	}
}
