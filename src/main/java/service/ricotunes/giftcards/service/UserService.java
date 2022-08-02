package service.ricotunes.giftcards.service;


import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.security.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);


	User addUser(User user);

}