package kshopov.web.eprescription.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.repositories.DoctorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final DoctorRepository doctorRepository;

	public UserDetailsServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Doctor doctor = doctorRepository.findByEmail(username);
		
		if(doctor == null) {
			throw new UsernameNotFoundException("Не успяхме да намерим потребител с мейл: " + username);
		}
		
		return new User(doctor.getEmail(), doctor.getPassword(), doctor.isVerified(), true,
				true, true, getAuthorities("ROLE_USER"));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

}