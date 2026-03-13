package booking.com.OnlineBookingHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import booking.com.OnlineBookingHotel.domain.Booking;

import booking.com.OnlineBookingHotel.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking, @RequestParam String userId, @RequestParam String roomId) {

        String response = bookingService.saveBooking(booking, userId, roomId);

        if (response.equals("Booking saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllBookings() {

        List<Booking> bookings = bookingService.getAllBookings();

        if (bookings != null && !bookings.isEmpty()) {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No bookings found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/byUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookingsByUser(@RequestParam String userId) {

        List<Booking> bookings = bookingService.getBookingsByUser(userId);

        if (bookings != null && !bookings.isEmpty()) {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No bookings found for this user.", HttpStatus.NOT_FOUND);
        }
    }
}
