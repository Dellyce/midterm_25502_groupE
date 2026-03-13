package booking.com.OnlineBookingHotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, UUID> {

    Boolean existsByName(String name);
}
