package booking.com.OnlineBookingHotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.Location;
import booking.com.OnlineBookingHotel.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByLocationIn(List<Location> locations);
}
