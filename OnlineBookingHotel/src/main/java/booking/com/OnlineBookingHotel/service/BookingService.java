package booking.com.OnlineBookingHotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.Booking;
import booking.com.OnlineBookingHotel.domain.EBookingStatus;
import booking.com.OnlineBookingHotel.domain.Room;
import booking.com.OnlineBookingHotel.domain.User;
import booking.com.OnlineBookingHotel.repository.BookingRepository;
import booking.com.OnlineBookingHotel.repository.RoomRepository;
import booking.com.OnlineBookingHotel.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoomRepository roomRepo;

    public String saveBooking(Booking booking, String userId, String roomId) {

        User user = userRepo.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        Room room = roomRepo.findById(UUID.fromString(roomId)).orElse(null);
        if (room == null) {
            return "Room not found.";
        }

        booking.setUser(user);
        booking.setRoom(room);
        booking.setStatus(EBookingStatus.PENDING);

        bookingRepo.save(booking);
        return "Booking saved successfully.";
    }

    public List<Booking> getBookingsByUser(String userId) {
        User user = userRepo.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return null;
        }
        return bookingRepo.findByUser(user);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
