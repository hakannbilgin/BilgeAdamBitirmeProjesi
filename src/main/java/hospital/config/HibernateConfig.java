package hospital.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
	private static final SessionFactory SESSION_FACTORY;

	static {

		Configuration configuration = new Configuration().configure();
		SESSION_FACTORY = configuration.buildSessionFactory();
	}

	private HibernateConfig() {
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
