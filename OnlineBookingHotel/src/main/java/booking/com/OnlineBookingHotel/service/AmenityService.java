package booking.com.OnlineBookingHotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.Amenity;
import booking.com.OnlineBookingHotel.repository.AmenityRepository;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepo;

    public String saveAmenity(Amenity amenity) {

        Boolean exists = amenityRepo.existsByName(amenity.getName());
        if (exists) {
            return "Amenity with this name already exists.";
        }

        amenityRepo.save(amenity);
        return "Amenity saved successfully.";
    }

    public List<Amenity> getAllAmenities() {
        return amenityRepo.findAll();
    }
}
