package laudhoot.config.core;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:util.properties")
@ComponentScan(basePackages = "laudhoot.core.seed")
public class SeedConfig {

	private static final String PROPERTY_PERFORM_SEEDING = "perform.seeding.operation";

	@Resource
	private Environment environment;

	@Autowired
	private List<DatabaseSeed> databaseSeeds;

	@PostConstruct
	public void seedData() {
		if (Boolean.valueOf(environment.getRequiredProperty(PROPERTY_PERFORM_SEEDING)))
			for (DatabaseSeed seed : databaseSeeds) {
				seed.seed();
			}
	}

	public interface DatabaseSeed {
		public void seed();
	}
}
