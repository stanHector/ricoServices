package service.ricotunes.giftcards.payload.request;


import javax.validation.constraints.NotBlank;


public class LoginRequest {
	@NotBlank
	private String usernameOrPhone;

	@NotBlank
	private String password;

    public String getUsernameOrPhone() {
        return usernameOrPhone;
    }

    public void setUsernameOrPhone(String usernameOrPhone) {
        this.usernameOrPhone = usernameOrPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}