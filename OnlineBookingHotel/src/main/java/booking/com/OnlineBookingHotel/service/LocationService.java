package booking.com.OnlineBookingHotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.ELocationType;
import booking.com.OnlineBookingHotel.domain.Location;
import booking.com.OnlineBookingHotel.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;

    public String saveLocation(Location location, String parentId) {

        if (parentId != null) {
            Location parentLocation = locationRepo.findById(UUID.fromString(parentId)).orElse(null);

            if (parentLocation == null) {
                return "Parent location not found.";
            } else {
                location.setParent(parentLocation);
            }
        }

        // if (!location.getType().equals(ELocationType.PROVINCE) && parentId == null) {
        //     return "Parent location is required for non-province locations.";
        // }

        if (!location.getType().equals(ELocationType.PROVINCE) && parentId == null) {
            return "Parent location is required for non-province locations.";
        }

        Boolean exists = locationRepo.existsByCode(location.getCode());
        if (exists) {
            return "Location with this code already exists.";
        }

        locationRepo.save(location);
        return "Location saved successfully.";
    }

    public List<Location> getAllChildren(UUID locationId) {
        Location location = locationRepo.findById(locationId).orElse(null);
        if (location == null) {
            return new ArrayList<>();
        }

        List<Location> result = new ArrayList<>();
        result.add(location);
        collectChildren(location, result);
        return result;
    }

    private void collectChildren(Location parent, List<Location> result) {
        List<Location> children = locationRepo.findByParent(parent);
        for (Location child : children) {
            result.add(child);
            collectChildren(child, result);
        }
    }
}
