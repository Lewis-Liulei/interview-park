package rt.lewis.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
		"rt.lewis.parking"
		},
		exclude={
				HibernateJpaAutoConfiguration.class})
public class HomeApplication {
	public static void main(String[] args) {
		SpringApplication springApplication =new SpringApplication(HomeApplication.class);
		springApplication.run(args);
	}
}
