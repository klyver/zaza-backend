package zaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;

@SpringBootApplication(scanBasePackages = "zaza", exclude = SessionAutoConfiguration.class)
public class BootZazaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootZazaApplication.class, args);
    }
}
