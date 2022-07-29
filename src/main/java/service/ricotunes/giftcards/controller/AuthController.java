package service.ricotunes.giftcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ricotunes.giftcards.enums.RoleName;
import service.ricotunes.giftcards.exception.AppException;
import service.ricotunes.giftcards.model.Role;
import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.payload.request.LoginRequest;
import service.ricotunes.giftcards.payload.request.SignUpRequest;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.payload.response.JwtAuthenticationResponse;
import service.ricotunes.giftcards.repository.RoleRepository;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.security.JwtTokenProvider;
import service.ricotunes.giftcards.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final String USER_ROLE_NOT_SET = "Users role not set";

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
//        Users users = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse("Bearer " + jwt, "Login Successful!"));
//        return ResponseEntity.ok(new JwtAuthenticationResponse());
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByPhone(signUpRequest.getPhone()))) {

            return new ResponseEntity<>(new ApiResponse(false, "Phone number is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        String username = signUpRequest.getUsername().toLowerCase();

        String firstname = signUpRequest.getFirstname().toLowerCase();
        String lastname = signUpRequest.getLastname().toLowerCase();
        String phone = signUpRequest.getPhone().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();

        String password = passwordEncoder.encode(signUpRequest.getPassword());

        Users users = new Users(username, firstname, lastname, phone, email, password);

        List<Role> roles = new ArrayList<>();

        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        } else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }
        users.setRoles(roles);

//        Map<Object, Object> payload = new HashMap<>();
//
//        userService.addUser(users);
//
//        payload.put("payload", users);

//     userRepository.save(users);
//        return new ResponseEntity<>(new ApiResponse(true, "User created successfully", payload, HttpStatus.OK), HttpStatus.OK);

           return new ResponseEntity<>(userRepository.save(users), HttpStatus.CREATED);
    }
}
