package laudhoot.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "laudhoot.rest.controller")
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	//private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    //private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
	
	/**
	 * To point container to some directory for serving static serources like css and javascript.
	 */
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}*/
	
	/**
	 * Configure container to some directory for serving static serources like css and javascript.
	 */
	/*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/
	
	/*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return viewResolver;
    }*/
}