package laudhoot.config.core.annotation;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

/**
 * BeanPostProcessor for @InjectLogger annotation. Based on an example at:
 * http://blog.alexis-hassler.com/2011/06/injection-de-logger-avec-spring.html
 * 
 * @author Apurve Gupta
 */

public class InjectLoggerAnnotationBeanPostProcessor implements
		BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(InjectLogger.class) != null) {
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, bean,
						Logger.getLogger(field.getDeclaringClass()));
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
