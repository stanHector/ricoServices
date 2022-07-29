package service.ricotunes.giftcards.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.enums.RoleName;
import service.ricotunes.giftcards.exception.AppException;
import service.ricotunes.giftcards.exception.EmailExistsException;
import service.ricotunes.giftcards.exception.UsernameExistsException;
import service.ricotunes.giftcards.model.Role;
import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.repository.RoleRepository;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.security.UserPrincipal;
import service.ricotunes.giftcards.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstname(), currentUser.getLastname(), currentUser.getPhone(),
                currentUser.getEmail());
    }

//    @Override
//    public UserIdentityAvailability checkUsernameAvailability(String username) {
//        Boolean isAvailable = !userRepository.existsByUsername(username);
//        return new UserIdentityAvailability(isAvailable);
//    }

//    @Override
//    public UserIdentityAvailability checkEmailAvailability(String email) {
//        Boolean isAvailable = !userRepository.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }
//
//    @Override
//    public UserProfile getUserProfile(String username) {
//        Users users = userRepository.getUserByName(username);
//
//        return new UserProfile(users.getId(), users.getFirstname(), users.getLastname(), users.getPhone(),
//                users.getEmail()
//        );
//    }

    @Override
    public Users addUser(Users users) {
        if (userRepository.existsByUsername(users.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new UsernameExistsException(apiResponse);
        }

        if (userRepository.existsByEmail(users.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new EmailExistsException(apiResponse);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(
                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("Users role not set")));
        users.setRoles(roles);

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

//    @Override
//    public ApiResponse giveAdmin(String username) {
//        Users users = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
//                .orElseThrow(() -> new AppException("Users role not set")));
//        roles.add(
//                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("Users role not set")));
//        users.setRoles(roles);
//        userRepository.save(users);
//        return new ApiResponse(Boolean.TRUE, "You gave ADMIN role to users: " + username);
//    }

//    @Override
//    public ApiResponse removeAdmin(String username) {
//        Users users = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(
//                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("Users role not set")));
//        users.setRoles(roles);
//        userRepository.save(users);
//        return new ApiResponse(Boolean.TRUE, "You took ADMIN role from users: " + username);
//    }

//    @Override
//    public Users updateUser(Users newUsers, String username, UserPrincipal currentUser) {
//        Users users = userRepository.getUserByName(username);
//        if (users.getId().equals(currentUser.getId())
//                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
////            users.setUsername(newUsers.getUsername());
//            users.setEmail(newUsers.getEmail());
//            users.setPhone(newUsers.getPhone());
//            users.setPassword(passwordEncoder.encode(newUsers.getPassword()));
//
//            return userRepository.save(users);
//
//        }
//
//        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update profile of: " + username);
//        throw new UnauthorizedException(apiResponse);
//
//    }

//    @Override
//    public ApiResponse deleteUser(String username, UserPrincipal currentUser) {
//        Users users = userRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", username));
//        if (!users.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
//                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
//            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + username);
//            throw new AccessDeniedException(apiResponse);
//        }
//
//        userRepository.deleteById(users.getId());
//
//        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of: " + username);
//    }
}
