package booking.com.OnlineBookingHotel.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.Hotel;
import booking.com.OnlineBookingHotel.domain.Location;
import booking.com.OnlineBookingHotel.repository.HotelRepository;
import booking.com.OnlineBookingHotel.repository.LocationRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private LocationRepository locationRepo;

    public String saveHotel(Hotel hotel, String locationId) {

        if (locationId != null) {
            Location location = locationRepo.findById(UUID.fromString(locationId)).orElse(null);
            if (location == null) {
                return "Location not found.";
            }
            hotel.setLocation(location);
        }

        Boolean exists = hotelRepo.existsByName(hotel.getName());
        if (exists) {
            return "Hotel with this name already exists.";
        }

        hotelRepo.save(hotel);
        return "Hotel saved successfully.";
    }

    public Page<Hotel> getAllHotelsWithSortingAndPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return hotelRepo.findAll(pageable);
    }
}
