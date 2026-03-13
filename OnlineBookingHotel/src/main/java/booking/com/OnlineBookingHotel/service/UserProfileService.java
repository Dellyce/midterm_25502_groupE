package booking.com.OnlineBookingHotel.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.com.OnlineBookingHotel.domain.User;
import booking.com.OnlineBookingHotel.domain.UserProfile;
import booking.com.OnlineBookingHotel.repository.UserProfileRepository;
import booking.com.OnlineBookingHotel.repository.UserRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private UserRepository userRepo;

    public String saveUserProfile(UserProfile userProfile, String userId) {

        User user = userRepo.findById(UUID.fromString(userId)).orElse(null);

        if (user == null) {
            return "User not found.";
        }

        Boolean profileExists = userProfileRepo.existsByUser(user);
        if (profileExists) {
            return "Profile for this user already exists.";
        }

        Boolean nationalIdExists = userProfileRepo.existsByNationalId(userProfile.getNationalId());
        if (nationalIdExists) {
            return "Profile with this national ID already exists.";
        }

        userProfile.setUser(user);
        userProfileRepo.save(userProfile);
        return "User profile saved successfully.";
    }

    public UserProfile getProfileByUserId(String userId) {
        User user = userRepo.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return null;
        }
        return userProfileRepo.findByUser(user);
    }
}
