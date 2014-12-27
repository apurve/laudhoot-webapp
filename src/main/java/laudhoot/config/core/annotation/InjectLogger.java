package laudhoot.config.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Allows an org.apache.commons.logging.Log instance to be initialized under
 * Spring 3.0 or later with a custom annotation @InjectLogger
 * Based on an example at:
 *           http://blog.alexis-hassler.com/2011/06/injection-de-logger-avec-spring.html
 * @author Apurve Gupta
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLogger {
}
