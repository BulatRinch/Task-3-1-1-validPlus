package app.SpringBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityUsersApplication {
	private static Start start;

	public SpringBootSecurityUsersApplication(Start start) {
		this.start = start;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityUsersApplication.class, args);

			start.save();

	}

}
