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
import org.springframework.web.bind.annotation.RestController;


import booking.com.OnlineBookingHotel.domain.Amenity;
import booking.com.OnlineBookingHotel.service.AmenityService;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAmenity(@RequestBody Amenity amenity) {

        String response = amenityService.saveAmenity(amenity);

        if (response.equals("Amenity saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAmenities() {

        List<Amenity> amenities = amenityService.getAllAmenities();

        if (amenities != null && !amenities.isEmpty()) {
            return new ResponseEntity<>(amenities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No amenities found.", HttpStatus.NOT_FOUND);
        }
    }
}
