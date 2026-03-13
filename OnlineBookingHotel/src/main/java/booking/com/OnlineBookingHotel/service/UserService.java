package booking.com.OnlineBookingHotel.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;      
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.ELocationType;
import booking.com.OnlineBookingHotel.domain.Location;
import booking.com.OnlineBookingHotel.domain.User;
import booking.com.OnlineBookingHotel.repository.LocationRepository;
import booking.com.OnlineBookingHotel.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private LocationService locationService;

    

    public String saveUser(User user, String locationId) {

        if (locationId != null) {
            Location location = locationRepo.findById(UUID.fromString(locationId)).orElse(null);
            if (location == null) {
                return "Location not found.";
            }
            user.setLocation(location);
        }

        Boolean exists = userRepo.existsByEmail(user.getEmail());
        if (exists) {
            return "User with this email already exists.";
        }

        userRepo.save(user);
        return "User saved successfully.";
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<User> getUsersByFirstName(String firstName) {
        return userRepo.findByFirstName(firstName);
    }

    public List<User> getUsersByLastName(String lastName) {
        return userRepo.findByLastName(lastName);
    }

    // Recursive: get all users under a location (province, district, sector, cell or village)
    public List<User> getUsersByLocationId(UUID locationId) {
        List<Location> allLocations = locationService.getAllChildren(locationId);
        return userRepo.findByLocationIn(allLocations);
    }

    public List<User> getUsersByProvinceName(String provinceName) {

       
        Optional<Location> provinceOpt = locationRepo
                .findByNameIgnoreCaseAndType(provinceName, ELocationType.PROVINCE);

        if (provinceOpt.isEmpty()) {
            return Collections.emptyList();
        }

        List<Location> allLocations = new ArrayList<>();
        collectAllDescendants(provinceOpt.get(), allLocations);

        
        return userRepo.findByLocationIn(allLocations);
    }

    // Walks the location tree downward, adding every node to the list
    private void collectAllDescendants(Location location, List<Location> result) {
        result.add(location);
        List<Location> children = locationRepo.findByParent(location);
        for (Location child : children) {
            collectAllDescendants(child, result);
        }  
    }
}