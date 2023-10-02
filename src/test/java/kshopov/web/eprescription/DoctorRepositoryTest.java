package kshopov.web.eprescription;

import static kshopov.web.eprescription.model.UserType.MEDIC;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.repositories.DoctorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DoctorRepositoryTest {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateDoctor() {
		Doctor doctor = new Doctor();
		
		doctor.setEmail(getRandomEmail());
		doctor.setVerified(false);
		doctor.setLzName("Niset");
		doctor.setPassword("4567465465");
		doctor.setPhone("0885474879");
		doctor.setRcz("1234567890");
		doctor.setToken("ewqedasjh2i1yh32#!@#");
		doctor.setTypeOfSender(MEDIC);
		doctor.setUin("1234567890");
		
		Doctor savedDoctor = doctorRepository.save(doctor);
		
		Doctor existUser = entityManager.find(Doctor.class, savedDoctor.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(doctor.getEmail());
	}

	private String getRandomEmail() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}

		return buffer.toString() + "@gmail.com";
	}
}