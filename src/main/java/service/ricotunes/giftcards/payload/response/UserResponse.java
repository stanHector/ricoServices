package service.ricotunes.giftcards.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.model.Wallet;

public class UserResponse {
    @JsonProperty("user")
    private User user;

    @JsonProperty("wallet")
    private Wallet wallet;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
