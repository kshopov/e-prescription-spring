package kshopov.web.eprescription.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private final UserDetailsService userDetailsService;
    
    private final PasswordEncoder passwordEncoder;

	private final DataSource dataSource;
	
	public WebSecurityConfig(PasswordEncoder passwordEncoder,
			 DataSource dataSource, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
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
				.tokenRepository(persistentTokenRepository())

			.and()
			.csrf().disable();

	}// @formatter:on

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

}
