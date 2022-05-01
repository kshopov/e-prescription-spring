package kshopov.web.eprescription.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    private final PasswordEncoder passwordEncoder;
	
	public WebSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder.bCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/**/*.js", "/**/*.css", "/register", "/registrationConfirm").permitAll()
				.anyRequest().authenticated()
			.and()

			.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/doLogin");
	}// @formatter:on
	
}
