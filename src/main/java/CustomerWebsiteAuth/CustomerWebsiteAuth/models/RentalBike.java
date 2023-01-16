package CustomerWebsiteAuth.CustomerWebsiteAuth.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bikes")
@Builder
@Getter
@Setter
public class RentalBike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;
    private String brand;
    private String model;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Rental Bike #" + id + ": " +
                " Color=" + color +
                ", Brand=" + brand +
                ", Model=" + model;
    }
}
