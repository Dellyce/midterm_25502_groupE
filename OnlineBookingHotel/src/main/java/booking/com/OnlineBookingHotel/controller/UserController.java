package booking.com.OnlineBookingHotel.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import booking.com.OnlineBookingHotel.domain.User;
import booking.com.OnlineBookingHotel.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user, @RequestParam(required = false) String locationId) {

        String response = userService.saveUser(user, locationId);

        if (response.equals("User saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userService.getAllUsers();

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByFirstName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByFirstName(@RequestParam String firstName) {

        List<User> users = userService.getUsersByFirstName(firstName);

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found with this first name.", HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all users under a given location (province, district, sector, cell or village)
    // Uses recursive location traversal
    @GetMapping(value = "/byLocation/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByLocation(@PathVariable UUID locationId) {

        List<User> users = userService.getUsersByLocationId(locationId);

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found under this location.", HttpStatus.NOT_FOUND);
        }
    }


    // retrieve users by province name supplied as a request param
    // Example: GET /api/users/byProvince?name=Kigali City
    @GetMapping(value = "/byProvince", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByProvinceName(
            @RequestParam(name = "name") String provinceName) {

        List<User> users = userService.getUsersByProvinceName(provinceName);

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "No users found under province: " + provinceName,
                HttpStatus.NOT_FOUND
            );
        }
}
}
