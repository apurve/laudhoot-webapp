package laudhoot.config.core;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import laudhoot.core.repository.BaseRepositoryFactoryBean;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * An application context Java configuration class. The usage of Java
 * configuration requires Spring Framework 3.0 or higher with following
 * exceptions:
 * 
 * @author Apurve Gupta
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "laudhoot.core.repository", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "packages.to.scan.for.domain";
	private static final String PROPERTY_NAME_HIBERNATE_UPDATE_SCHEMA = "hibernate.hbm2ddl.auto";

	@Resource
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setJdbcUrl(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager()
			throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactoryBean()
				.getObject());

		return transactionManager;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
			throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setPackagesToScan(environment
						.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean
				.setPersistenceProviderClass(HibernatePersistence.class);

		Properties jpaProterties = new Properties();
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_UPDATE_SCHEMA, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_UPDATE_SCHEMA));

		entityManagerFactoryBean.setJpaProperties(jpaProterties);

		return entityManagerFactoryBean;
	}

}
