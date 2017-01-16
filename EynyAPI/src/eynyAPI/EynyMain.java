package eynyAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan  
public class EynyMain extends SpringBootServletInitializer{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EynyMain.class, args);  
	}

}
