package kshopov.web.eprescription.security;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.repositories.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserDetailsServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    void GIVEN_username_THEN_return_user_details() {
        userDetailsServiceImpl = new UserDetailsServiceImpl(doctorRepository);

        Doctor doctor = new Doctor();
        doctor.setEmail("k.shopov@abv.bg");
        doctor.setPassword("k.shopov@abv.bg");

        String username = "krasimir.shopov@gmail.com";

        when(doctorRepository.findByEmail(anyString())).thenReturn(doctor);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(doctor.getEmail(), ReflectionTestUtils.getField(userDetails, "username"));
    }

    @Test
    void NOT_GIVEN_username_THEN_return_user_throw_exception() {
        userDetailsServiceImpl = new UserDetailsServiceImpl(doctorRepository);

        String username = "krasimir.shopov@gmail.com";

        when(doctorRepository.findByEmail(anyString())).thenReturn(null);

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername(username);
        }); 

        String expectedMessage = "Не успяхме да намерим";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}