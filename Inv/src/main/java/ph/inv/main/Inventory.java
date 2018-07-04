package ph.inv.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableAutoConfiguration
@ComponentScan("ph.inv")
@EntityScan(basePackages={"ph.inv.entity"})
public class Inventory {

	public static void main(String [] args) {
		SpringApplication.run(Inventory.class, args);
	}
	
	
	
	/*@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheMillis(10);
		return messageSource;
	}*/
	
	/*@Bean
	public DispatcherServlet getDispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	public ServletRegistrationBean<Servlet> dispatchServletRegistration() {
		ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<Servlet>(getDispatcherServlet(), "/inv/*");
		
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
		
		return registration;
	}*/
}
