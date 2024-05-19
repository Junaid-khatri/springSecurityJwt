package com.springBoot.security_project_LDAP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	      .authorizeHttpRequests((authorize) -> authorize
	        .anyRequest().fullyAuthenticated()
	      )
	      .formLogin(Customizer.withDefaults());

	    return http.build();
	  }
	
	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
	
	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource ldapSource = new LdapContextSource();
		ldapSource.setUrl("ldap://localhost:10389");
		ldapSource.setUserDn("uid=admin,ou=system");
		ldapSource.setPassword("123456");
		return ldapSource;
	}
	
	@Bean
	public AuthenticationManager authManager(BaseLdapPathContextSource source) {
		LdapBindAuthenticationManagerFactory factory = 
				new LdapBindAuthenticationManagerFactory(source);
		factory.setUserDnPatterns("cn={0},ou=users,ou=system");
		return factory.createAuthenticationManager();
	}
}
