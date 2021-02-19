package umb.carp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan("umb.carp") posso evitarlo di scrivere se il rest controller è 
// in una cartella figlia.....ad esempio la classe main è in umb.carp mentre 
// la classe controller è in umb.carp.controller  
@SpringBootApplication
public class WebApplicationService {

	public static void main(String[] args) {
		SpringApplication.run(WebApplicationService.class, args);
	}

}
