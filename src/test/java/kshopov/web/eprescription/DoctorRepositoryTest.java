package kshopov.web.eprescription;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.UserType;
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
		
		doctor.setEmail("k.shopov@nisetbg.com");
		doctor.setVerified(false);
		doctor.setLzName("Niset");
		doctor.setPassword("4567465465");
		doctor.setPhone("0885474879");
		doctor.setRcz("1234567890");
		doctor.setToken("ewqedasjh2i1yh32#!@#");
		doctor.setTypeOfSender(UserType.MEDIC);
		doctor.setUin("1234567890");
		
		Doctor savedDoctor = doctorRepository.save(doctor);
		
		Doctor existUser = entityManager.find(Doctor.class, savedDoctor.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(doctor.getEmail());
	}
}