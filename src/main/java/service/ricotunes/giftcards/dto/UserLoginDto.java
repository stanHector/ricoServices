package service.ricotunes.giftcards.dto;


/*created by Hector Developers
6-30-2022
*/

import javax.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}