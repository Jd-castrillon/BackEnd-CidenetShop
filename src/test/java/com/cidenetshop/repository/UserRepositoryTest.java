package com.cidenetshop.repository;

import static org.assertj.core.api.Assertions.assertThat; // main one

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cidenetshop.model.entity.DocumentType;
import com.cidenetshop.model.entity.Role;
import com.cidenetshop.model.entity.User;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void itShouldReturnOptionalIfExistsUserByEmail() {
	
	}

	@Test
	void itShouldReturnTrueIfUserExistByEmail() {
		// given
		String email = "jdkastrillon@gmail.com";
		Role role = new Role(1, "admin");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		User user = new User();
		user.setIdUser(1l);
		user.setDocumentNumber("111111");
		user.setName("Juan David");
		user.setEmail(email);
		user.setDocumentType(new DocumentType(1, "tarjeta de identidad"));
		user.setPassword("123");
		user.setRoles(roles);

		userRepository.save(user);
		// when
		boolean exepected = userRepository.existsByEmail(email);
		// then
		assertThat(exepected).isTrue();

	}
}
