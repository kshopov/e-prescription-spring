package kshopov.web.eprescription.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String COOKIE_NAME = "chocolate-cookie";

	private static final String COOKIE_KEY = "kukurujuVaf1a";

	private static final int SECONDS_IN_WEEK = 604800;

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
				.antMatchers(
					"/**/*.js", 
					"/**/*.css", 
					"/register", 
					"/registrationConfirm",
					"/forgotPassword",
					"/resetPassword")
				.permitAll()
				.anyRequest().authenticated()
			.and()

			.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/doLogin")

			.and()
			.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/doLogout", "GET"))

			.and()
			.rememberMe()
			.tokenValiditySeconds(SECONDS_IN_WEEK)
			.key(COOKIE_KEY)
			.rememberMeCookieName(COOKIE_NAME)

			.and()
			.csrf().disable();

	}// @formatter:on
	
}
