package CustomerWebsiteAuth.CustomerWebsiteAuth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerWebsiteAuthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWebsiteAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Authority userAuth = Authority.builder().authority(RoleEnum.ROLE_USER).build();
		Authority adminAuth = Authority.builder().authority(RoleEnum.ROLE_ADMIN).build();
		if (authorityRepo.findAll().isEmpty()) {
			authorityRepo.save(userAuth);
			authorityRepo.save(adminAuth);
		}

		if (userPrincipalRepo.findAll().isEmpty()) {
			userPrincipalRepo.save(
					new UserPrincipal("USER", passwordEncoder.encode("user"),
							Collections.singletonList(userAuth))
			);
		}

		 */
	}

}
