package stepDefinitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAutomationApplication {
    public WebAutomationApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(WebAutomationApplication.class, args);
    }
}