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

import booking.com.OnlineBookingHotel.domain.Room;
import booking.com.OnlineBookingHotel.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRoom(@RequestBody Room room, @RequestParam String hotelId) {

        String response = roomService.saveRoom(room, hotelId);

        if (response.equals("Room saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    // Many-to-Many: add an amenity to a room
    @PostMapping(value = "/addAmenity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAmenityToRoom(@RequestParam String roomId, @RequestParam String amenityId) {

        String response = roomService.addAmenityToRoom(roomId, amenityId);

        if (response.equals("Amenity added to room successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // One-to-Many: get all rooms belonging to a hotel
    @GetMapping(value = "/byHotel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomsByHotel(@RequestParam String hotelId) {

        List<Room> rooms = roomService.getRoomsByHotel(hotelId);

        if (rooms != null && !rooms.isEmpty()) {
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No rooms found for this hotel.", HttpStatus.NOT_FOUND);
        }
    }
}
