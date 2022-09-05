package pl.coderslab.leaflets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LeafletsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeafletsApplication.class, args);
	}
	
}
