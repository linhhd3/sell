package com.linhhd.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.linhhd.**")
@PropertySource(value={"classpath:db.properties"})
@EnableTransactionManagement
public class SpringConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	Environment enviroment;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public TilesConfigurer tileConfigurer(){
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("classpath:tiles.xml");
		configurer.setCheckRefresh(true);
		
		return configurer;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		// With spring view
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
		// With tiles
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;	
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/files/**").addResourceLocations("file:C:/Uploads/");
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:messages");
		bundleMessageSource.setDefaultEncoding("utf-8");
		return bundleMessageSource;
	}
	
	
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(-1);
		return commonsMultipartResolver;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(enviroment.getProperty("driver"));
//		dataSource.setUrl(enviroment.getProperty("url"));
//		dataSource.setUsername(enviroment.getProperty("username"));
//		dataSource.setPassword(enviroment.getProperty("password"));
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ban_hang");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	

//	@Bean
//	public JdbcTemplate jdbcTemplate(){
//		return new JdbcTemplate(dataSource());
//	}
//	
//	@Bean(name="TransactionManager")
//	public DataSourceTransactionManager dataSourceTransactionManager(){
//		return new DataSourceTransactionManager(dataSource());
//	}
	
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(){
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.linhhd.entity");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", enviroment.getProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", enviroment.getProperty("hibernate.show_sql"));
		
		bean.setHibernateProperties(hibernateProperties);
		
		return bean;
	}
	
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
	}
	
	@Bean    
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		//Using gmail        
		mailSender.setHost("smtp.gmail.com");        
		mailSender.setPort(587);        
		mailSender.setUsername("Your-gmail-id");        
		mailSender.setPassword("Your-gmail-password");                 
		Properties javaMailProperties = new Properties();        
		javaMailProperties.put("mail.smtp.starttls.enable", "true");        
		javaMailProperties.put("mail.smtp.auth", "true");        
		javaMailProperties.put("mail.transport.protocol", "smtp");        
		javaMailProperties.put("mail.debug", "true");
		//Prints out everything on screen                 
		mailSender.setJavaMailProperties(javaMailProperties);        
		return mailSender;    
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
}