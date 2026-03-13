package booking.com.OnlineBookingHotel.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    Boolean existsByName(String name);

    @Override
    Page<Hotel> findAll(Pageable pageable);
}
