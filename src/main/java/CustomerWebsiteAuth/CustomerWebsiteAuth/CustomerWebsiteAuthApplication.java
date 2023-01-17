package CustomerWebsiteAuth.CustomerWebsiteAuth;

import CustomerWebsiteAuth.CustomerWebsiteAuth.models.Authority;
import CustomerWebsiteAuth.CustomerWebsiteAuth.models.Customer;
import CustomerWebsiteAuth.CustomerWebsiteAuth.models.RoleEnum;
import CustomerWebsiteAuth.CustomerWebsiteAuth.models.UserPrincipal;
import CustomerWebsiteAuth.CustomerWebsiteAuth.repositories.AuthorityRepo;
import CustomerWebsiteAuth.CustomerWebsiteAuth.repositories.UserPrincipalRepo;
import CustomerWebsiteAuth.CustomerWebsiteAuth.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class CustomerWebsiteAuthApplication implements CommandLineRunner {

	@Autowired
	private AuthorityRepo authorityRepo;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserPrincipalRepo userPrincipalRepo;


	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(CustomerWebsiteAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("*******Starting logic to check whether or not to populate database*******");

		Authority userAuth = Authority.builder().authority(RoleEnum.ROLE_USER).build();
		Authority adminAuth = Authority.builder().authority(RoleEnum.ROLE_ADMIN).build();
		if (authorityRepo.findAll().isEmpty()) {
			System.out.println("*******Populating authority database table*******");
			authorityRepo.save(userAuth);
			authorityRepo.save(adminAuth);
		}

		if (userPrincipalRepo.findAll().isEmpty()) {
			if (customerService.getAllCustomers().isEmpty()) {
				System.out.println(
						"*******Populating User database table (one admin, 3 regular users with customer relation)*******");
				Customer customer1 = Customer.builder()
						.givenName("Customer")
						.surname("One")
						.emailAddress("customer1@gmail.com")
						.address("Customer Address One")
						.age(30)
						.build();
				Customer customer2 = Customer.builder().givenName("Customer").
						surname("Two").
						emailAddress("customer2@gmail.com").
						address("Customer Address Two").
						age(28).build();
				Customer customer3 = Customer.builder().givenName("Customer")
						.surname("Three")
						.emailAddress("customer3@gmail.com")
						.address("Customer Address Three")
						.age(32).build();
				customerService.saveAllCustomer(Arrays.asList(customer1, customer2, customer3));
				userPrincipalRepo.saveAll(
						Arrays.asList(
								new UserPrincipal("admin", passwordEncoder.encode("admin"),
										Arrays.asList(userAuth, adminAuth)),
								new UserPrincipal("user1", passwordEncoder.encode("testpassword"),
										Collections.singletonList(userAuth),
										customer1),
								new UserPrincipal("user2", passwordEncoder.encode("testpassword"),
										Collections.singletonList(userAuth),
										customer2),
								new UserPrincipal("user3", passwordEncoder.encode("testpassword"),
										Collections.singletonList(userAuth),
										customer3)
						)
				);
			}
		}
	}

}
