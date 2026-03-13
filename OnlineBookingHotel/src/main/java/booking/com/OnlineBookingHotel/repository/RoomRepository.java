package booking.com.OnlineBookingHotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booking.com.OnlineBookingHotel.domain.Hotel;
import booking.com.OnlineBookingHotel.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    Boolean existsByRoomNumber(String roomNumber);

    List<Room> findByHotel(Hotel hotel);
}
