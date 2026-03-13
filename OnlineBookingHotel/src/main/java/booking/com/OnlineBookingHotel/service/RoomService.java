package booking.com.OnlineBookingHotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.Amenity;
import booking.com.OnlineBookingHotel.domain.Hotel;
import booking.com.OnlineBookingHotel.domain.Room;
import booking.com.OnlineBookingHotel.repository.AmenityRepository;
import booking.com.OnlineBookingHotel.repository.HotelRepository;
import booking.com.OnlineBookingHotel.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private AmenityRepository amenityRepo;

    public String saveRoom(Room room, String hotelId) {

        Hotel hotel = hotelRepo.findById(UUID.fromString(hotelId)).orElse(null);

        if (hotel == null) {
            return "Hotel not found.";
        }

        Boolean exists = roomRepo.existsByRoomNumber(room.getRoomNumber());
        if (exists) {
            return "Room with this room number already exists.";
        }

        room.setHotel(hotel);
        roomRepo.save(room);
        return "Room saved successfully.";
    }

    public String addAmenityToRoom(String roomId, String amenityId) {

        Room room = roomRepo.findById(UUID.fromString(roomId)).orElse(null);
        if (room == null) {
            return "Room not found.";
        }

        Amenity amenity = amenityRepo.findById(UUID.fromString(amenityId)).orElse(null);
        if (amenity == null) {
            return "Amenity not found.";
        }

        List<Amenity> amenities = room.getAmenities();
        for (Amenity existing : amenities) {
            if (existing.getId().equals(amenity.getId())) {
                return "Amenity already added to this room.";
            }
        }

        amenities.add(amenity);
        room.setAmenities(amenities);
        roomRepo.save(room);
        return "Amenity added to room successfully.";
    }

    public List<Room> getRoomsByHotel(String hotelId) {
        Hotel hotel = hotelRepo.findById(UUID.fromString(hotelId)).orElse(null);
        if (hotel == null) {
            return null;
        }
        return roomRepo.findByHotel(hotel);
    }
}
