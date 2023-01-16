package CustomerWebsiteAuth.CustomerWebsiteAuth.repositories;
import CustomerWebsiteAuth.CustomerWebsiteAuth.models.RentalBike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<RentalBike, Long> {
    RentalBike findByCustomerId(Long id);
}
