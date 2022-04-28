package kshopov.web.eprescription.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kshopov.web.eprescription.services.UserDetailsService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;
	
	public WebSecurityConfig() {
		super();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(this.passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/**/*.js", "/**/*.css", "/register").permitAll()
				.anyRequest().authenticated()
			.and()

			.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/doLogin");
	}// @formatter:on
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
