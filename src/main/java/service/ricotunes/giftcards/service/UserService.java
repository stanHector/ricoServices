package service.ricotunes.giftcards.service;


import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.payload.UserIdentityAvailability;
import service.ricotunes.giftcards.payload.UserProfile;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.security.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	UserProfile getUserProfile(String username);

	Users addUser(Users users);

	Users updateUser(Users newUsers, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

//	UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}