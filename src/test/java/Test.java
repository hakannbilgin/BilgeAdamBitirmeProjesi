import org.hibernate.Session;
import org.hibernate.Transaction;

import hospital.config.HibernateConfig;
import hospital.domain.Doctor;



public final class Test {

	@org.junit.Test
	public void testInsertPerson() {
		
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		Doctor doctor = new Doctor();
		doctor.setFirstName("John");
		doctor.setLastName("Doe");
		doctor.setCitizenNumber("10101010101");
		doctor.setId(1L);
		doctor.setBranch("Genel Cerrah");
		
		session.save(doctor);
		System.out.println(doctor);
		transaction.commit();
		session.close();
		HibernateConfig.getSessionFactory().close();
	}
	
	
	
	
	
	
}
