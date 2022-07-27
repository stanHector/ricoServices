package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.Users;
import service.ricotunes.giftcards.security.UserPrincipal;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(@NotBlank String username);

	Users findByEmail(@NotBlank String email);

	Boolean existsByUsername(@NotBlank String username);

	Boolean existsByEmail(@NotBlank String email);

	Boolean existsByPhone(@NotBlank String phone);

	Optional<Users> findByUsernameOrEmail(String username, String email);

	default Users getUser(UserPrincipal currentUser) {
		return getUserByName(currentUser.getUsername());
	}

	default Users getUserByName(String username) {
		return findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));
	}

}
