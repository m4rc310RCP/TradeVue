package br.com.m4rc310.tv.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.m4rc310.gql.MUserProvider;
import br.com.m4rc310.gql.dto.MUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfig {

	@Bean
	MUser loadUserTest() {
		MUser user = new MUser();
		user.setUsername("test");
		user.setPassword("test");
		user.setRoles("admin".split(";"));
		return user;
	}

	@Bean
	MUserProvider loadUserProvider(MUser user) {
		return new MUserProvider() {

			@Override
			public MUser authUser(String username, Object password) throws Exception {
				return user;
			}

			@Override
			public MUser getUserFromUsername(String username) {
				return user;
			}

			@Override
			public boolean isValidUser(MUser user) {
				log.info("isValidUser {}", user);
				return true;
			}
		};
	}
	
	
}
