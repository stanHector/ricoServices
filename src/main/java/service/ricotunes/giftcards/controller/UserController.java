package service.ricotunes.giftcards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.payload.UserProfile;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.security.CurrentUser;
import service.ricotunes.giftcards.security.UserPrincipal;
import service.ricotunes.giftcards.service.UserService;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //get all users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = userService.getCurrentUser(currentUser);
        return new ResponseEntity<>(userSummary, HttpStatus.OK);
    }

    @GetMapping("/{email}/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfile> getUSerProfile(@PathVariable(value = "email") String email) {
        UserProfile userProfile = userService.getUserProfile(email);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }


//    @PostMapping("/create-user")
//    @PreAuthorize("hasRole('ADMIN')or hasRole('ADMIN')")
//    public ResponseEntity<Users> addUser(@Valid @RequestBody Users users) {
//        Users newUsers = userService.addUser(users);
//        return new ResponseEntity<>(newUsers, HttpStatus.CREATED);
//    }

    @PutMapping("/user/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Users> updateUser(@Valid @RequestBody Users newUsers,
                                            @PathVariable(value = "email") String email, @CurrentUser UserPrincipal currentUser) {
        Users updatedUSer = userService.updateUser(newUsers, email, currentUser);

        return new ResponseEntity<>(updatedUSer, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "email") String email,
                                                  @CurrentUser UserPrincipal currentUser) {
        ApiResponse apiResponse = userService.deleteUser(email, currentUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @PutMapping("/user/{email}/giveAdmin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ApiResponse> giveAdmin(@PathVariable(name = "email") String email) {
//        ApiResponse apiResponse = userService.giveAdmin(email);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
//
//    @PutMapping("/users/{email}/takeAdmin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ApiResponse> takeAdmin(@PathVariable(name = "email") String email) {
//        ApiResponse apiResponse = userService.removeAdmin(email);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
}
