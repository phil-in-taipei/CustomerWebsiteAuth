package CustomerWebsiteAuth.CustomerWebsiteAuth.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Builder
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String givenName;

    private String surname;

    private String emailAddress;
    private Integer age;
    private String address;

    @OneToOne(mappedBy = "customer")
    private UserPrincipal user;

    @OneToOne(mappedBy = "customer")
    private RentalBike bike;
}
