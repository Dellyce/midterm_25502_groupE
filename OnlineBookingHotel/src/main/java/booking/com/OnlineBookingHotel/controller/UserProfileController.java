package booking.com.OnlineBookingHotel.controller;

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

import booking.com.OnlineBookingHotel.domain.UserProfile;
import booking.com.OnlineBookingHotel.service.UserProfileService;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProfile(@RequestBody UserProfile userProfile, @RequestParam String userId) {

        String response = userProfileService.saveUserProfile(userProfile, userId);

        if (response.equals("User profile saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getByUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProfileByUser(@RequestParam String userId) {

        UserProfile profile = userProfileService.getProfileByUserId(userId);

        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profile not found for this user.", HttpStatus.NOT_FOUND);
        }
    }
}
