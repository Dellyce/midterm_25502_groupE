package booking.com.OnlineBookingHotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.User;
import booking.com.OnlineBookingHotel.domain.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    Boolean existsByNationalId(String nationalId);

    Boolean existsByUser(User user);

    UserProfile findByUser(User user);
}
