package booking.com.OnlineBookingHotel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.ELocationType;
import booking.com.OnlineBookingHotel.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Boolean existsByCode(String code);

    List<Location> findByParent(Location parent);



    Optional<Location> findByNameIgnoreCaseAndType(String name, ELocationType type);

    

}

