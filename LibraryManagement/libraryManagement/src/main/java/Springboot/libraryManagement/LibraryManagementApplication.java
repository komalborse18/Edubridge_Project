package Springboot.libraryManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import com.twilio.Twilio;

import Springboot.libraryManagement.Model.TwilioConfig;
import jakarta.annotation.PostConstruct;


@SpringBootApplication
@EnableConfigurationProperties
public class LibraryManagementApplication {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}
}
