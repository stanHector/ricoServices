package service.ricotunes.giftcards.service;


import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.payload.UserIdentityAvailability;
import service.ricotunes.giftcards.payload.UserProfile;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.security.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);


	Users addUser(Users users);

}